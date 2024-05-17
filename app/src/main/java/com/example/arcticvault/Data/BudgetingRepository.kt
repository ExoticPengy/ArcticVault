package com.example.arcticvault.Data

import kotlinx.coroutines.flow.Flow

interface BudgetingRepository {

    suspend fun insertBudgeting(budgeting: Budgeting)

    suspend fun updateBudgeting(budgeting: Budgeting)

    suspend fun deleteBudgeting(budgeting: Budgeting)

    fun getBudgetingStream(id: Int): Flow<Budgeting?>

    fun getAllBudgetingStream(): Flow<List<Budgeting>>
}