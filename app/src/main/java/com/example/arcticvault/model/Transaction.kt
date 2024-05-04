package com.example.arcticvault.model

import androidx.annotation.DrawableRes
import com.example.arcticvault.R

data class Transaction(
    val id: Int = 0,
    @DrawableRes val icon: Int = R.drawable.income,
    val type: String = "Income",
    val title: String = "",
    val time: String = "",
    val date: String = "",
    val amount: Double = 0.0
)
