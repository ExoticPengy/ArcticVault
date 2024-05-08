package com.example.arcticvault.model

import androidx.compose.ui.graphics.Color

data class CategoryModel(
    val id: Int = 0,
    val title: String = "",
    val color: Long = Color.White.value.toLong(),
)
