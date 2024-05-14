package com.example.arcticvault.data
import kotlinx.coroutines.flow.Flow


interface ReminderRepository {
    suspend fun insertReminder(reminder: Reminder)
    suspend fun deleteReminder(reminder: Reminder)
    suspend fun updateReminder(reminder: Reminder)
    fun getAllReminderStream(): Flow<List<Reminder>>
    fun getReminderStream(id: Int): Flow<Reminder?>


}