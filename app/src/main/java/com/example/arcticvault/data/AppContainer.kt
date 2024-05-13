package com.example.arcticvault.data

import android.content.Context

interface AppContainer {
    val reminderRepository: ReminderRepository
}

class AppDataContainer(private val context: Context): AppContainer {

    override val reminderRepository: ReminderRepository by lazy {
        OfflineReminderRepository(ArcticVaultDatabase.getDatabase(context).reminderDao())
    }

    /*override val transactionsRepository: TransactionsRepository by lazy {
        OfflineTransactionsRepository(ArcticVaultDatabase.getDatabase(context).transactionDao())
    }
    override val categoryRepository: CategoryRepository by lazy {
        OfflineCategoryRepository(ArcticVaultDatabase.getDatabase(context).categoryDao())
    }*/
}