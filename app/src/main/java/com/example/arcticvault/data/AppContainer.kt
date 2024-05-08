package com.example.arcticvault.data

import android.content.Context

interface AppContainer {
    val transactionsRepository: TransactionsRepository
    val categoryRepository: CategoryRepository
}

class AppDataContainer(private val context: Context): AppContainer {
    override val transactionsRepository: TransactionsRepository by lazy {
        OfflineTransactionsRepository(ArcticVaultDatabase.getDatabase(context).transactionDao())
    }
    override val categoryRepository: CategoryRepository by lazy {
        OfflineCategoryRepository(ArcticVaultDatabase.getDatabase(context).categoryDao())
    }
}