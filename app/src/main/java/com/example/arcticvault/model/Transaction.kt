package com.example.arcticvault.model

import androidx.annotation.DrawableRes

data class Transaction(
    @DrawableRes val icon: Int,
    val transaction: String,
    val time: String,
    val date: String,
    val amount: Double
)
