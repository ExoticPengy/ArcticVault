package com.example.arcticvault.Data

import kotlinx.coroutines.flow.Flow

abstract class OfflineBudgetingRepository (private val budgetingDao: BudgetingDao):BudgetingRepository
{
    suspend fun insertEditGoals(budgeting: Budgeting) = budgetingDao.insert(budgeting)

    suspend fun updateEditGoals(budgeting: Budgeting) = budgetingDao.update(budgeting)

    suspend fun deleteEditGoals(budgeting: Budgeting) = budgetingDao.delete(budgeting)

    fun getEditGoalsStream(id: Int): Flow<Budgeting?> = budgetingDao.getBudgeting(id)

    fun getAllEditGoalsStream(): Flow<List<Budgeting>> = budgetingDao.getAllBudgeting()
}