package com.example.arcticvault.Data

import kotlinx.coroutines.flow.Flow

class OfflineDebtRepository(private val debtDao: DebtDao) : DebtRepository {
    override suspend fun insertDebt(debt: Debt) = debtDao.insert(debt)

    override suspend fun updateDebt(debt: Debt) = debtDao.update(debt)

    override suspend fun deleteDebt(debt: Debt) = debtDao.delete(debt)

    override fun getDebtStream(id: Int): Flow<Debt?> = debtDao.getDebt(id)

    override fun getAllDebtStream(): Flow<List<Debt>> = debtDao.getAllDebts()
}