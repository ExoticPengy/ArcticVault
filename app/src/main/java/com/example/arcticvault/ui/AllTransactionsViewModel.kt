package com.example.arcticvault.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arcticvault.R
import com.example.arcticvault.data.Transaction
import com.example.arcticvault.data.TransactionsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.text.NumberFormat
import java.util.Locale

class AllTransactionsViewModel(transactionRepository: TransactionsRepository): ViewModel() {
    private val _uiState = MutableStateFlow(AllTransactionUiState())
    val uiState: StateFlow<AllTransactionUiState> = _uiState.asStateFlow()

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val allTransactionsUiState: StateFlow<AllTransactionUiState> =
        transactionRepository.getAllTransactionsStream().map { AllTransactionUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = AllTransactionUiState()
            )

    var typeFilter by mutableIntStateOf(R.string.income)
    var dateFilter by mutableStateOf("Select a date")
    var titleFilter by mutableStateOf("")
    var showDatePicker by mutableStateOf(false)


    fun changeTypeFilter(type: Int) {
        typeFilter = type
    }

    fun checkTitleFilter(title: String): Boolean {
        if(titleFilter != "") {
            return title == titleFilter
        }
        return true
    }

    fun checkDateFilter(date: String): Boolean {
        if(dateFilter != "Select a date") {
            return date == dateFilter
        }
        return true
    }

    fun updateIconDesc(iconId: Int): Int {
        return if (iconId == R.drawable.expense) {
            R.string.expense_desc
        } else R.string.income_desc
    }

    fun calculateTotal(transactions: List<Transaction>): String {
        var total = 0.0
        for (transaction in transactions) {
            if (transaction.type == typeFilter && checkTitleFilter(transaction.title) && checkDateFilter(transaction.date)) {
                total += transaction.amount
            }
        }
        return formatAmount(total)
    }

    fun formatAmount(amount: Double): String {
        return NumberFormat.getCurrencyInstance(Locale("en", "MY")).format(amount)
    }
}