package com.example.arcticvault.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "budgeting")
data class Budgeting(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val yearlyBudgeting: Double = 0.0,
)
