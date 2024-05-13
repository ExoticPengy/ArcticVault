package com.example.arcticvault.Data

import android.content.Context

interface AppContainer {
    val transactionsRepository: TransactionsRepository
    val categoryRepository:CategoryRepository
    val editGoalsRepository : EditGoalsRepository
}

class AppDataContainer(private val context: Context): AppContainer {
    override val editGoalsRepository : EditGoalsRepository by lazy {
        OfflineEditGoalsRepository(ArcticVaultDatabase.getDatabase(context).editGaolsDao())
    }
    override val transactionsRepository: TransactionsRepository by lazy {
        OfflineTransactionsRepository(ArcticVaultDatabase.getDatabase(context).transactionDao())
    }
    override val categoryRepository: CategoryRepository by lazy {
        OfflineCategoryRepository(ArcticVaultDatabase.getDatabase(context).categoryDao())
    }
}
