package com.example.arcticvault.ui.theme

import com.example.arcticvault.Data.Transaction


data class TransactionsUiState(
    val transactionList: List<Transaction> = listOf()
)
