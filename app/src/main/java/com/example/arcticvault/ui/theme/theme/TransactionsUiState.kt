package com.example.arcticvault.ui

import com.example.arcticvault.Data.Transaction


data class TransactionsUiState(
    val transactionList: List<Transaction> = listOf()
)
