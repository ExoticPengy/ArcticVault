package com.example.arcticvault.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [EditGoals::class, Transaction::class, Category::class, Budgeting::class],
    version = 3,
    exportSchema = false
)
abstract class ArcticVaultDatabase : RoomDatabase() {
    abstract fun editGaolsDao(): EditGaolsDao
    abstract fun transactionDao(): TransactionDao
    abstract fun categoryDao(): CategoryDao
    abstract fun budgetingDao(): BudgetingDao

    companion object {
        @Volatile
        private var Instance: ArcticVaultDatabase? = null

        fun getDatabase(context: Context): ArcticVaultDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    ArcticVaultDatabase::class.java,
                    "arcticvault_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}