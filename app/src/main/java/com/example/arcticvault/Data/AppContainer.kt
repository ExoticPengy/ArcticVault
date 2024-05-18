package com.example.arcticvault.Data

import android.content.Context

interface AppContainer {
    val transactionsRepository: TransactionsRepository
    val categoryRepository:CategoryRepository
    val editGoalsRepository : EditGoalsRepository
    val budgetingRepository: BudgetingRepository
    val debtRepository: DebtRepository
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
    override val debtRepository: DebtRepository by lazy {
        OfflineDebtRepository(ArcticVaultDatabase.getDatabase(context).debtDao())
    }
}
