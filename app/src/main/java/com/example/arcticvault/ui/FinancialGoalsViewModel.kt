package com.example.arcticvault.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arcticvault.data.EditGoals
import com.example.arcticvault.data.EditGoalsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.text.NumberFormat
import java.util.Locale

class FinancialGoalsViewModel(
    editGoalsRepository: EditGoalsRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(FinancialGoalsUiState())

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val financialGoalsUiState: StateFlow<FinancialGoalsUiState> =
        editGoalsRepository.getAllEditGoalsStream().map { FinancialGoalsUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = FinancialGoalsUiState()
            )

    fun formatAmount(amount: Double): String {
        return NumberFormat.getCurrencyInstance(Locale("en", "MY")).format(amount)
    }

    fun calculateTotal(editGaols: List<EditGoals>): Int {
        var total = 0
        for (editGoals in editGaols) {
            total += editGoals.milestones
        }
        return total
    }
}
