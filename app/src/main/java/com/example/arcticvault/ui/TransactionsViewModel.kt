package com.example.arcticvault.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arcticvault.R
import com.example.arcticvault.data.TransactionsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.text.NumberFormat
import java.util.Locale

class TransactionsViewModel(transactionRepository: TransactionsRepository): ViewModel() {
    private val _uiState = MutableStateFlow(TransactionsUiState())

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val transactionsUiState: StateFlow<TransactionsUiState> =
        transactionRepository.getAllTransactionsStream().map { TransactionsUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = TransactionsUiState()
            )

    fun updateIconDesc(iconId: Int): Int {
        return if (iconId == R.drawable.expense) {
            R.string.expense_desc
        } else R.string.income_desc
    }

    fun formatAmount(amount: Double): String {
        return NumberFormat.getCurrencyInstance(Locale("en", "MY")).format(amount)
    }

}