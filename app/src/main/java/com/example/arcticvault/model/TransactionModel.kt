package com.example.arcticvault.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.arcticvault.R

data class TransactionModel(
    val id: Int = 0,
    @DrawableRes val icon: Int = R.drawable.income,
    @StringRes val type: Int = R.string.income,
    val title: String = "",
    val time: String = "",
    val date: String = "",
    val description: String = "",
    val amount: Double = 0.0,
    val categoryId: Int = 1
)
