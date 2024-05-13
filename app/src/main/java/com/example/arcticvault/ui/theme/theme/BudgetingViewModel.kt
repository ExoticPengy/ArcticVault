package com.example.arcticvault.ui.theme.theme

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arcticvault.Data.BudgetingRepository
import com.example.arcticvault.Data.EditGoals
import com.example.arcticvault.Data.EditGoalsRepository
import com.example.arcticvault.Data.Transaction
import com.example.arcticvault.Model.BudgetingInputModel
import com.example.arcticvault.R
import com.example.arcticvault.ui.theme.BudgetingDestination
import com.example.arcticvault.ui.theme.EditGoalsDestination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.text.NumberFormat
import java.time.LocalDate
import java.time.Month
import java.util.Locale

class BudgetingViewModel(
    savedStateHandle: SavedStateHandle,
    private val budgetingRepository: BudgetingRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(BudgetingUiState())
    val uiState: StateFlow<BudgetingUiState> = _uiState.asStateFlow()
    val numberChanges: Int? = savedStateHandle[BudgetingDestination.budgetIdArg]

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }


    val budgetingUiState: StateFlow<BudgetingUiState> =
        budgetingRepository.getAllBudgetingStream().map { BudgetingUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = BudgetingUiState()
            )

    fun updateUiState(budgetingModel: BudgetingInputModel) {
        _uiState.value = BudgetingUiState(
            budgeting = budgetingModel
        )
    }

    fun updateAmount(newAmount: String): Double {
        var doubleAmount = newAmount.replace("RM", "")
        doubleAmount = doubleAmount.replace(",", "")
        return doubleAmount.toDoubleOrNull() ?: 0.0
    }

    fun formatAmount(amount: Double): String {
        return NumberFormat.getCurrencyInstance(Locale("en", "MY")).format(amount)
    }

    fun calculateTotal(budgeting: List<Transaction>): String {
        var total = 0.0
        for (transaction in budgeting) {
            if (transaction.type == R.string.expense) {
                total += transaction.amount
            }
        }
        return formatAmount(total)
    }

    fun calculateTotalAmount(budgeting: List<Transaction>): Double {
        var total = 0.0
        for (transaction in budgeting) {
            if (transaction.type == R.string.expense) {
                total += transaction.amount
            }
        }
        return total
    }

    fun calculateExpense(budgeting: List<Transaction>): Double {
        val localMonth = getLocalMonth().toUpperCase()
        var total = 0.0
        for (transaction in budgeting) {
            if (isSameMonth(transaction.date, localMonth)) {
                total += transaction.amount
            }
        }
        return total
    }

    fun getLocalMonth(): String {
        return Month.values()[LocalDate.now().monthValue - 1].toString()
    }

    fun isSameMonth(transactionMonth: String, localMonth: String): Boolean {
        return transactionMonth.equals(localMonth, ignoreCase = true)
    }
}