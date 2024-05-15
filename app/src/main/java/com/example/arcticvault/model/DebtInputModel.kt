package com.example.arcticvault.model

import java.util.Date

data class DebtInputModel(
    var id: Int = 0,
    var categories: String = "",
    var nickname: String = "",
    var currentBalance: Double = 0.0,
    var annualRate: Int = 0,
    var paymentFrequency: String = "",
    var minPayment: Double = 0.0,
    var remark: String = "",
    var date: String = ""
)
