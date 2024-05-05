package com.example.arcticvault.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.arcticvault.R
import com.example.arcticvault.data.Transaction
import com.example.arcticvault.data.TransactionsRepository
import com.example.arcticvault.model.TransactionModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.NumberFormat
import java.util.Locale

class EditTransactionViewModel(private val transactionsRepository: TransactionsRepository): ViewModel() {
    private val _uiState = MutableStateFlow(EditTransactionUiState())
    val uiState: StateFlow<EditTransactionUiState> = _uiState.asStateFlow()

    var showDatePicker by mutableStateOf(false)
    var showTimePicker by mutableStateOf(false)

    fun updateUiState(transactionModel: TransactionModel) {
        _uiState.value = EditTransactionUiState(
            transaction = transactionModel
        )
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
            transaction.amount.toString().isNotBlank()
        }
    }

    private fun TransactionModel.transactionToData(): Transaction = Transaction(
        id = id,
        icon = icon,
        type = type,
        title = title,
        time = time,
        date = date,
        amount = amount
    )

    suspend fun saveTransaction(uiState: EditTransactionUiState) {
        if (validateInput(uiState)) {
            transactionsRepository.insertTransaction(_uiState.value.transaction.transactionToData())
        }
    }

}