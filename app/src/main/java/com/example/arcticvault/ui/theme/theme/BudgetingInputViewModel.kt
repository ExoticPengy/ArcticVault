package com.example.arcticvault.ui.theme.theme

import androidx.lifecycle.ViewModel
import com.example.arcticvault.Data.BudgetingRepository
import com.example.arcticvault.Data.EditGoalsRepository
import com.example.arcticvault.Model.BudgetingInputModel
import com.example.arcticvault.Model.EditGoalsInputModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.NumberFormat
import java.util.Locale

class BudgetingInputViewModel(private val budgetingRepository: BudgetingRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(BudgetingUiState())
    val uiState: StateFlow<BudgetingUiState> = _uiState.asStateFlow()

    fun updateUiState(budgetingInputModel: BudgetingInputModel) {
        _uiState.value = BudgetingUiState(
            budgeting = budgetingInputModel
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
}