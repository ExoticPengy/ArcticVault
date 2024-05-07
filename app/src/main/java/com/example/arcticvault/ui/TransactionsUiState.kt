package com.example.arcticvault.ui

import com.example.arcticvault.data.Transaction

data class TransactionsUiState(
    val transactionList: List<Transaction> = listOf()
)
