package com.example.arcticvault.data

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @DrawableRes val icon: Int,
    val transaction: String,
    val time: String,
    val date: String,
    val amount: Double
)
