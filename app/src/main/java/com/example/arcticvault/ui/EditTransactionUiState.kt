package com.example.arcticvault.ui

import androidx.annotation.StringRes

data class EditTransactionUiState (
    val id: Int = 0,
    @StringRes
    val icon: Int = 0,
    val transaction: String = "",
    val time: String = "",
    val date: String = "",
    val amount: Double = 0.0,
    val amountFieldWidth: Int = 50
)