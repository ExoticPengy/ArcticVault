package com.example.arcticvault.ui

import com.example.arcticvault.data.Budgeting
import com.example.arcticvault.model.BudgetingInputModel

data class BudgetingUiState(
    val budgetingList: List<Budgeting> = listOf(),
    val budgeting: BudgetingInputModel = BudgetingInputModel(),
)
