package com.example.arcticvault.ui

import com.example.arcticvault.data.Transaction

data class AllTransactionUiState(
    val transactionList: List<Transaction> = listOf()
)
