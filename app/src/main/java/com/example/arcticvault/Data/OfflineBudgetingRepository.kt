package com.example.arcticvault.Data

import kotlinx.coroutines.flow.Flow

class OfflineBudgetingRepository (private val budgetingDao: BudgetingDao):BudgetingRepository
{
    override suspend fun insertBudgeting(budgeting: Budgeting) = budgetingDao.insert(budgeting)

    override suspend fun updateBudgeting(budgeting: Budgeting) = budgetingDao.update(budgeting)

    override suspend fun deleteBudgeting(budgeting: Budgeting) = budgetingDao.delete(budgeting)

    override fun getBudgetingStream(id: Int): Flow<Budgeting?> = budgetingDao.getBudgeting(id)

    override fun getAllBudgetingStream(): Flow<List<Budgeting>> = budgetingDao.getAllBudgeting()
}