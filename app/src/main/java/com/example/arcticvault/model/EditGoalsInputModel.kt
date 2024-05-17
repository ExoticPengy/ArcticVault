package com.example.arcticvault.Model

data class EditGoalsInputModel(
    val id: Int = 0,
    val title: String = "",
    val amount: Double = 0.0,
    var milestones: Int = 1,
    val startDate: String = "",
    val endDate: String = "",
    val amountGetDivided: Double = 0.0,
    val dateOfDivided: String = ""
)
