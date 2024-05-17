package com.example.arcticvault.data

import com.example.arcticvault.data.Budgeting
import kotlinx.coroutines.flow.Flow

interface BudgetingRepository {

    suspend fun insertBudgeting(budgeting: Budgeting)

    suspend fun updateBudgeting(budgeting: Budgeting)

    suspend fun deleteBudgeting(budgeting: Budgeting)

    fun getBudgetingStream(id: Int): Flow<Budgeting?>

    fun getAllBudgetingStream(): Flow<List<Budgeting>>
}