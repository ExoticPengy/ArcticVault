package com.example.arcticvault.data

import android.content.Context

interface AppContainer {
    val transactionsRepository: TransactionsRepository
    val categoryRepository:CategoryRepository
    val editGoalsRepository : EditGoalsRepository
    val budgetingRepository: BudgetingRepository
    val reminderRepository: ReminderRepository
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
    override val budgetingRepository: BudgetingRepository by lazy {
       OfflineBudgetingRepository(ArcticVaultDatabase.getDatabase(context).budgetingDao())
    }
    override val reminderRepository: ReminderRepository by lazy {
        OfflineReminderRepository(ArcticVaultDatabase.getDatabase(context).reminderDao())
    }
}
