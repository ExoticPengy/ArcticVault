package com.example.arcticvault.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arcticvault.EditTransactionDestination
import com.example.arcticvault.R
import com.example.arcticvault.data.Category
import com.example.arcticvault.data.CategoryRepository
import com.example.arcticvault.data.Transaction
import com.example.arcticvault.data.TransactionsRepository
import com.example.arcticvault.model.CategoryModel
import com.example.arcticvault.model.TransactionModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale

class EditTransactionViewModel(
    savedStateHandle: SavedStateHandle,
    private val transactionsRepository: TransactionsRepository,
    private val categoryRepository: CategoryRepository
): ViewModel() {
    private val transactionId: Int = savedStateHandle[EditTransactionDestination.transactionIdArg] ?: -1

    private val _uiState = MutableStateFlow(EditTransactionUiState())

    val uiState: StateFlow<EditTransactionUiState> = _uiState.asStateFlow()

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    init {
        if (transactionId != -1) {
            viewModelScope.launch {
                updateUiState(
                    transactionsRepository.getTransactionStream(transactionId)
                        .filterNotNull()
                        .first()
                        .transactionToModel()
                )
            }
        }
    }

    var showCreateCategory by mutableStateOf(false)

    var showColorPicker by mutableStateOf(false)
    var colorPicked by mutableLongStateOf(Color.White.value.toLong())

    var showDatePicker by mutableStateOf(false)

    var showTimePicker by mutableStateOf(false)

    private var firstCheck by mutableStateOf(true)

    fun updateUiState(transactionModel: TransactionModel) {
        _uiState.value = EditTransactionUiState(
            transaction = transactionModel
        )
    }

    fun checkType(transactionModel: TransactionModel, isExpense: Boolean, isIncome: Boolean){
        var transaction = transactionModel
        if (isExpense && firstCheck) {
            transaction = transactionModel.copy(icon = R.drawable.expense, type = R.string.expense, time = "Time", date = "Date")
            firstCheck = false
        }
        else if (isIncome && firstCheck) {
            transaction = transactionModel.copy(icon = R.drawable.income, type = R.string.income, time = "Time", date = "Date")
            firstCheck = false
        }
        updateUiState(transaction)
    }

    fun updateIconDesc(iconId: Int): Int {
        return if (iconId == R.drawable.expense) {
            R.string.expense_desc
        } else R.string.income_desc
    }

    fun updateAmount(newAmount: String): Double {
        var doubleAmount = newAmount.replace("RM", "")
        doubleAmount = doubleAmount.replace(",", "")
        return doubleAmount.toDoubleOrNull() ?: 0.0
    }

    fun formatAmount(amount: Double): String {
        return NumberFormat.getCurrencyInstance(Locale("en", "MY")).format(amount)
    }

    private fun validateInput(uiState: EditTransactionUiState): Boolean {
        return with(uiState) {
            transaction.id.toString().isNotBlank() &&
            transaction.icon.toString().isNotBlank() &&
            transaction.type.toString().isNotBlank() &&
            transaction.title.isNotBlank() &&
            transaction.time.isNotBlank() &&
            transaction.date.isNotBlank() &&
            transaction.amount != 0.00 &&
            transaction.categoryId.toString().isNotBlank()
        }
    }

    private fun TransactionModel.transactionToData(): Transaction = Transaction(
        id = id,
        icon = icon,
        type = type,
        title = title,
        time = time,
        date = date,
        description = description,
        amount = amount,
        categoryId = categoryId
    )

    private fun Category.categoryToData(): Category = Category(
        id = id,
        title = title,
        color = color
    )

    private fun Transaction.transactionToModel(): TransactionModel = TransactionModel(
        id = id,
        icon = icon,
        type = type,
        title = title,
        time = time,
        date = date,
        description = description,
        amount = amount,
        categoryId = categoryId
    )

    private fun Category.categoryToModel(): CategoryModel = CategoryModel(
        id = id,
        title = title,
        color = color
    )

    suspend fun deleteTransaction(uiState: EditTransactionUiState) {
        if (validateInput(uiState)) {
            if (transactionId != -1)
                transactionsRepository.deleteTransaction(_uiState.value.transaction.transactionToData())
        }
    }

    suspend fun saveTransaction(uiState: EditTransactionUiState) {
        if (validateInput(uiState)) {
            if (transactionId != -1)
                transactionsRepository.updateTransaction(_uiState.value.transaction.transactionToData())
            else transactionsRepository.insertTransaction(_uiState.value.transaction.transactionToData())
        }
    }

}