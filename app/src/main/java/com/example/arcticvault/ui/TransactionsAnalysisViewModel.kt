package com.example.arcticvault.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arcticvault.data.Category
import com.example.arcticvault.data.CategoryRepository
import com.example.arcticvault.data.Transaction
import com.example.arcticvault.data.TransactionsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TransactionsAnalysisViewModel(
    private val transactionRepository: TransactionsRepository,
    private val categoryRepository: CategoryRepository
    ): ViewModel() {
    private val _uiState = MutableStateFlow(TransactionsAnalysisUiState())

    val uiState: StateFlow<TransactionsAnalysisUiState> = _uiState.asStateFlow()

    var transactionsList: List<Transaction> = listOf()
    var categoryList: List<Category> = listOf()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            transactionRepository.getAllTransactionsStream().collect {
                transactionsList = it
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            categoryRepository.getAllCategoriesStream().collect {
                categoryList = it
            }
        }
    }

    fun updateUiState(transactionsList: List<Transaction>, categoryList: List<Category>) {
        _uiState.value = TransactionsAnalysisUiState(
            transactionsList, categoryList)
    }

    fun findMaxRange(): Double {
        var highest = 0.0
        for (transaction in transactionsList) {
            if (transaction.amount > highest)
                highest = transaction.amount
        }
        return highest
    }
}