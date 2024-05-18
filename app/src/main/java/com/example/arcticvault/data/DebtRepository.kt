package com.example.arcticvault.data

import kotlinx.coroutines.flow.Flow

interface DebtRepository {
    suspend fun insertDebt(debt: Debt)

    suspend fun updateDebt(debt: Debt)

    suspend fun deleteDebt(debt: Debt)

    fun getDebtStream(id: Int): Flow<Debt?>

    fun getAllDebtStream(): Flow<List<Debt>>
}