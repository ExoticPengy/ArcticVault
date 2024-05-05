package com.example.arcticvault.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @DrawableRes val icon: Int,
    @StringRes val type: Int,
    val title: String,
    val time: String,
    val date: String,
    val amount: Double
)
