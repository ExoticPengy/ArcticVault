package com.example.arcticvault.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.arcticvault.data.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(transaction: Transaction)

    @Update
    suspend fun update(transaction: Transaction)

    @Delete
    suspend fun delete(transaction: Transaction)

    @Query("SELECT * from transactions WHERE id = :id")
    fun getTransaction(id: Int): Flow<Transaction>

    @Query("SELECT * from transactions ORDER BY id DESC")
    fun getAllTransactions(): Flow<List<Transaction>>
}