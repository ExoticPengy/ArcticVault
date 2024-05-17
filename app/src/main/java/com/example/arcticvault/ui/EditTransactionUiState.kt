package com.example.arcticvault.ui

import com.example.arcticvault.data.Category
import com.example.arcticvault.model.TransactionModel

data class EditTransactionUiState(
    val transaction: TransactionModel = TransactionModel(),
    val categoryList: List<Category> = listOf()
)