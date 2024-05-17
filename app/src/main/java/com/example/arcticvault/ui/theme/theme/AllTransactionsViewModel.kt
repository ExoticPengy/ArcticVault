package com.example.arcticvault.ui.theme.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arcticvault.R
import com.example.arcticvault.Data.Category
import com.example.arcticvault.Data.CategoryRepository
import com.example.arcticvault.Data.Transaction
import com.example.arcticvault.Data.TransactionsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale

class AllTransactionsViewModel(
    transactionRepository: TransactionsRepository,
    categoryRepository: CategoryRepository

): ViewModel() {
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

    init {
        viewModelScope.launch {
            categoryRepository.getAllCategoriesStream().collect {
                categoryList = it
            }
        }
    }

    var categoryList: List<Category> = listOf()
    var typeFilter by mutableIntStateOf(R.string.income)
    var dateFilter by mutableStateOf("Select a date")
    var titleFilter by mutableStateOf("")
    var showDatePicker by mutableStateOf(false)
    var showFilterDialog by mutableStateOf(false)
    var selectedCategoryId by mutableIntStateOf(0)
    var selectedCategoryFilter by mutableIntStateOf(0)

    fun changeTypeFilter(type: Int) {
        typeFilter = type
    }

    fun checkTitleFilter(title: String): Boolean {
        if(titleFilter != "") {
            return title.contains(titleFilter, ignoreCase = true)
        }
        return true
    }

    fun checkDateFilter(date: String): Boolean {
        if(dateFilter != "Select a date") {
            return date == dateFilter
        }
        return true
    }

    fun checkCategoryFilter(categoryId: Int?): Boolean {
        if(selectedCategoryFilter != 0) {
            return categoryId == selectedCategoryFilter
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