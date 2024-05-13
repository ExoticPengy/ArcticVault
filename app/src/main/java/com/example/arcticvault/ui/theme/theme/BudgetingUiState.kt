package com.example.arcticvault.ui.theme.theme

import com.example.arcticvault.Data.Budgeting
import com.example.arcticvault.Data.EditGoals
import com.example.arcticvault.Model.BudgetingInputModel

data class BudgetingUiState (
    val budgetingList: List<Budgeting> = listOf(),
    val budgeting: BudgetingInputModel = BudgetingInputModel(),
)
