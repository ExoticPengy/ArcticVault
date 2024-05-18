package com.example.arcticvault.Data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface DebtDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(debt: Debt)

    @Update
    suspend fun update(debt: Debt)

    @Delete
    suspend fun delete(debt: Debt)

    @Query("SELECT * from debt WHERE id = :id")
    fun getDebt(id: Int): Flow<Debt>

    @Query("SELECT * from debt ORDER BY id ASC")
    fun getAllDebts(): Flow<List<Debt>>

//    fun getTest()
}
