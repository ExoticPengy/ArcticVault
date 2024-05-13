package com.example.arcticvault.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.yml.charts.common.extensions.roundTwoDecimal
import com.example.arcticvault.R
import com.example.arcticvault.data.Transaction
import com.example.arcticvault.data.TransactionsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.text.NumberFormat
import java.time.LocalDate
import java.util.Locale
import kotlin.math.absoluteValue

class TransactionsViewModel(transactionsRepository: TransactionsRepository): ViewModel() {
    private val _uiState = MutableStateFlow(TransactionsUiState())

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val transactionsUiState: StateFlow<TransactionsUiState> =
        transactionsRepository.getAllTransactionsStream().map { TransactionsUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = TransactionsUiState()
            )

    var profitIsPositive by mutableStateOf(false)
    var lossIsPositive by mutableStateOf(false)

    fun updateIconDesc(iconId: Int): Int {
        return if (iconId == R.drawable.expense) {
            R.string.expense_desc
        } else R.string.income_desc
    }

    fun calculateProfit(transactionList: List<Transaction>, getProfit: Boolean): Double {
        val currentDate = LocalDate.now()
        val currentYear = currentDate.year
        var totalAmountThisYear = 0.0
        val filteredTransactionList = mutableListOf<Transaction>()
        for (transaction in transactionList) {
            if (transaction.type == R.string.income) {
                filteredTransactionList.add(transaction)
            }
        }

        for (transaction in filteredTransactionList) {
            var year = transaction.date
            year = year.replaceBeforeLast("/", "")
            year = year.replace("/", "")
            if (year == currentYear.toString()) {
                totalAmountThisYear += transaction.amount
            }
        }

        val lastYear = currentDate.year - 1
        var totalAmountLastYear = 0.0
        for (transaction in transactionList) {
            var year = transaction.date
            year = year.replaceBeforeLast("/", "")
            year = year.replace("/", "")
            if (year == lastYear.toString()) {
                totalAmountLastYear += transaction.amount
            }
        }

        profitIsPositive = if (totalAmountLastYear < totalAmountThisYear) {
            true
        } else
            false

        return if (getProfit)
            totalAmountThisYear
        else
            ((totalAmountLastYear - totalAmountThisYear) / 100.0).absoluteValue.roundTwoDecimal()
    }

    fun calculateLoss(transactionList: List<Transaction>, getLoss: Boolean): Double {
        val currentDate = LocalDate.now()
        val currentYear = currentDate.year
        var totalAmountThisYear = 0.0
        val filteredTransactionList = mutableListOf<Transaction>()
        for (transaction in transactionList) {
            if (transaction.type == R.string.expense) {
                filteredTransactionList.add(transaction)
            }
        }

        for (transaction in filteredTransactionList) {
            var year = transaction.date
            year = year.replaceBeforeLast("/", "")
            year = year.replace("/", "")
            if (year == currentYear.toString()) {
                totalAmountThisYear += transaction.amount
            }
        }

        val lastYear = currentDate.year - 1
        var totalAmountLastYear = 0.0
        for (transaction in transactionList) {
            var year = transaction.date
            year = year.replaceBeforeLast("/", "")
            year = year.replace("/", "")
            if (year == lastYear.toString()) {
                totalAmountLastYear += transaction.amount
            }
        }

        lossIsPositive = if (totalAmountLastYear > totalAmountThisYear) {
            true
        } else
            false

        return if (getLoss)
            totalAmountThisYear
        else
            ((totalAmountLastYear - totalAmountThisYear) / 100.0).absoluteValue.roundTwoDecimal()
    }

    fun positiveOrNegative(check: Boolean): String {
        return if (check) "+" else "-"
    }

    fun changeColor(check: Boolean, isProfit: Boolean): Color {
        return if (isProfit)
            if (check) Color.Green else Color.Red
        else
            if (check) Color.Red else Color.Green
    }

    fun formatAmount(amount: Double): String {
        return NumberFormat.getCurrencyInstance(Locale("en", "MY")).format(amount)
    }

}