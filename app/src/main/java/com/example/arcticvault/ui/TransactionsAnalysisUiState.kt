package com.example.arcticvault.ui

import com.example.arcticvault.data.Category
import com.example.arcticvault.data.Transaction

data class TransactionsAnalysisUiState(
    val transactionList: List<Transaction> = listOf(),
    val categoryList: List<Category> = listOf()
)
