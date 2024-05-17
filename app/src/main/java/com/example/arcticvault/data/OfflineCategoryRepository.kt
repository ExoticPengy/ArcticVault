package com.example.arcticvault.data

import kotlinx.coroutines.flow.Flow

class OfflineCategoryRepository(private val categoryDao: CategoryDao) : CategoryRepository {
    override suspend fun insertCategory(category: Category) = categoryDao.insert(category)

    override suspend fun updateCategory(category: Category) = categoryDao.update(category)

    override suspend fun deleteCategory(category: Category) = categoryDao.delete(category)

    override fun getCategoryStream(id: Int): Flow<Category?> = categoryDao.getCategory(id)

    override fun getAllCategoriesStream(): Flow<List<Category>> = categoryDao.getAllCategories()
}