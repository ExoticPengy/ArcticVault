package com.example.arcticvault.Data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BudgetingDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(budgeting: Budgeting)

    @Update
    suspend fun update(budgeting: Budgeting)

    @Delete
    suspend fun delete(budgeting: Budgeting)

    @Query("SELECT * from budgeting WHERE id = :id")
    fun getBudgeting(id: Int): Flow<Budgeting>

    @Query("SELECT * from budgeting ORDER BY id ASC")
    fun getAllBudgeting(): Flow<List<Budgeting>>
}