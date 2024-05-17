package com.example.arcticvault.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "editGoals")
data class EditGoals(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String = "",
    val amount: Double = 0.0,
    val milestones: Int = 0,
    val startDate: String = "",
    val endDate: String = "",
    val amountGetDivided: Double = 0.0,
    val dateOfDivided: String = ""
)