package com.example.arcticvault.ui.theme.theme

import androidx.lifecycle.ViewModel
import com.example.arcticvault.Model.BudgetingInputModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.NumberFormat
import java.util.Locale

class BudgetingViewModel(): ViewModel() {
    private val _uiState = MutableStateFlow(BudgetingUiState())
    val uiState: StateFlow<BudgetingUiState> = _uiState.asStateFlow()

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

}