package com.example.arcticvault.ui

import com.example.arcticvault.model.Transaction

data class EditTransactionUiState (
    val transaction: Transaction = Transaction()
)