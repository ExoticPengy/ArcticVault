package com.example.arcticvault.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reminder")
data class Reminder(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val title : String,
    val desc : String,
    val amount: Double,
    val date: String,
    val repeat: String,
    val category: String
){

}
