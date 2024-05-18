package com.example.arcticvault.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface EditGaolsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(editGoals: EditGoals)

    @Update
    suspend fun update(editGoals: EditGoals)

    @Delete
    suspend fun delete(editGoals: EditGoals)

    @Query("SELECT * from editGoals WHERE id = :id")
    fun getEditGoals(id: Int): Flow<EditGoals>

    @Query("SELECT * from editGoals ORDER BY id ASC")
    fun getAllEditGoals(): Flow<List<EditGoals>>
}