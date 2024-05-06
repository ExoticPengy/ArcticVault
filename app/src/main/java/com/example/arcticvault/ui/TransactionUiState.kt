package com.example.arcticvault.ui

import com.example.arcticvault.data.Transaction

data class TransactionUiState(
    val transactionList: List<Transaction> = listOf()
)
