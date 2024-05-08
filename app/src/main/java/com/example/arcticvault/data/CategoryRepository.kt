package com.example.arcticvault.data

import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun insertCategory(category: Category)

    suspend fun updateCategory(category: Category)

    suspend fun deleteCategory(category: Category)

    fun getCategoryStream(id: Int): Flow<Category?>

    fun getAllCategoriesStream(): Flow<List<Category>>
}