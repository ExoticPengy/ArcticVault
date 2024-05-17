package com.example.arcticvault.Data

import kotlinx.coroutines.flow.Flow

interface TransactionsRepository {
    suspend fun insertTransaction(transaction: Transaction)

    suspend fun updateTransaction(transaction: Transaction)

    suspend fun deleteTransaction(transaction: Transaction)

    fun getTransactionStream(id: Int): Flow<Transaction?>

    fun getAllTransactionsStream(): Flow<List<Transaction>>
}