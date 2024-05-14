package com.example.arcticvault.model

data class ReminderEntryModel(
    val id : Int = 0,
    val title : String = "",
    val desc : String = "",
    val amount: Double = 0.00,
    val date: String = "",
    val repeat: String = "",
    val category: String = "",
    val status: String = ""
)
