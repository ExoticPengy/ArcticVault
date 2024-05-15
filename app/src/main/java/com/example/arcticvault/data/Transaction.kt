package com.example.arcticvault.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "transactions", foreignKeys = [
    ForeignKey(entity = Category::class, parentColumns = ["id"], childColumns = ["categoryId"], onDelete = ForeignKey.SET_NULL, onUpdate = ForeignKey.NO_ACTION)
])
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @DrawableRes val icon: Int,
    @StringRes val type: Int,
    val title: String,
    val time: String,
    val date: String,
    val description: String,
    val amount: Double,
    val categoryId: Int?
)
