package com.example.arcticvault.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [EditGoals::class, Transaction::class, Category::class, Budgeting::class, Reminder::class, Debt::class], version = 1, exportSchema = false)
abstract class ArcticVaultDatabase: RoomDatabase() {
    abstract fun editGaolsDao(): EditGaolsDao
    abstract fun transactionDao(): TransactionDao
    abstract fun categoryDao(): CategoryDao
    abstract fun budgetingDao(): BudgetingDao
    abstract fun reminderDao(): ReminderDao
    abstract fun debtDao(): DebtDao

    companion object {
        @Volatile
        private var Instance: ArcticVaultDatabase? = null

        fun getDatabase(context: Context): ArcticVaultDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, ArcticVaultDatabase::class.java, "arcticvault_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}