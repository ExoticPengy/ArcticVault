package com.example.arcticvault.Data

import kotlinx.coroutines.flow.Flow

class OfflineTransactionsRepository(private val transactionDao: TransactionDao): TransactionsRepository {

    override suspend fun insertTransaction(transaction: Transaction) = transactionDao.insert(transaction)

    override suspend fun updateTransaction(transaction: Transaction) = transactionDao.update(transaction)

    override suspend fun deleteTransaction(transaction: Transaction) = transactionDao.delete(transaction)

    override fun getTransactionStream(id: Int): Flow<Transaction?> = transactionDao.getTransaction(id)

    override fun getAllTransactionsStream(): Flow<List<Transaction>> = transactionDao.getAllTransactions()
}