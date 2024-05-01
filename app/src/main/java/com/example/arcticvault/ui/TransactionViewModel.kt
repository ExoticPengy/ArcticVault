package com.example.arcticvault.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TransactionViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(TransactionUiState())
    val uiState: StateFlow<TransactionUiState> = _uiState.asStateFlow()

    private var incomeAmount: Double = 0.0

    private fun calculateTotalIncome(): Double {
        incomeAmount = 8901.00
        return incomeAmount
    }
}