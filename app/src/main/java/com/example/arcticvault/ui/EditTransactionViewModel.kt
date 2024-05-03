package com.example.arcticvault.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class EditTransactionViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(EditTransactionUiState())
    val uiState: StateFlow<EditTransactionUiState> = _uiState.asStateFlow()

    fun updateIcon(newIcon: Int) {
        _uiState.value = EditTransactionUiState(icon = newIcon)
    }

    fun updateTransaction(newTransaction: String) {
        _uiState.value = EditTransactionUiState(transaction = newTransaction)
    }

    fun updateTime(newTime: String) {
        _uiState.value = EditTransactionUiState(time = newTime)
    }

    fun updateDate(newDate: String) {
        _uiState.value = EditTransactionUiState(date = newDate)
    }

    fun updateAmount(newAmount: String) {
        val amountLength: Int = newAmount.length
        val amountFieldLength = if (50 + (11 * amountLength) >= 230) {
            230
        } else {
            50 + (11 * amountLength)
        }
        _uiState.value = EditTransactionUiState(amount = newAmount.toDouble(), amountFieldWidth = amountFieldLength)
    }

    fun loadTransaction() {

    }

    fun addNewTransaction() {

    }

    fun cancelTransaction() {

    }

}