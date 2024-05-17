package com.example.arcticvault.Model

data class BudgetingInputModel(
    val yearlyBudgeting: Double = 0.0,
    var percentageYearlyBudgeting: Double = 0.0,
    val yearlyExpense: Double = 0.0,
    val monthlyBudgeting: Double = 0.0,
    var percentageMonthlyBudgeting: Double = 0.0,
    val monthlyExpense: Double = 0.0,
    val monthlyPercentageSubscription:Double = 0.0,
    val monthlyPercentageSales:Double = 0.0,
    val monthlyPercentageInvest:Double = 0.0,
    val monthlyPercentageOthers:Double = 0.0,
    val yearlyPercentageSales:Double = 0.0,
    val yearlyPercentageInvest:Double = 0.0,
    val yearlyPercentageOthers:Double = 0.0
)
