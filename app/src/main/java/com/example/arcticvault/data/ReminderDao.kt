package com.example.arcticvault.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(reminder: Reminder)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(reminder: Reminder)

    @Delete
    suspend fun delete(reminder: Reminder)

    @Query ("SELECT * FROM reminder ORDER BY id ASC")
    fun getAllReminder(): Flow<List<Reminder>>

    @Query("SELECT * from reminder WHERE id = :id")
    fun getReminder(id: Int): Flow<Reminder>

    @Query("SELECT * FROM reminder WHERE status = :status ORDER BY date ASC")
    fun getRemindersByStatus(status: String): Flow<List<Reminder>>



}