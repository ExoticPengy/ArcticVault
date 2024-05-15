package com.example.arcticvault.ui.theme

import com.example.arcticvault.Data.Category
import com.example.arcticvault.Model.TransactionModel

data class EditTransactionUiState (
    val transaction: TransactionModel = TransactionModel(),
    val categoryList: List<Category> = listOf()
)