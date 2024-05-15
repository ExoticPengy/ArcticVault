package com.example.arcticvault.Data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "debt")
data class Debt(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val categories: String,
    val nickname: String,
    val currentBalance: Double = 0.0,
    val annualRate: Int = 0,
    val paymentFrequency: String,
    val minPayment: Double = 0.0,
    val remark: String,
    val date: String
)
