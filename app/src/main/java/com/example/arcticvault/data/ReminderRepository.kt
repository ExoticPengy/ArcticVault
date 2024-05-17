package com.example.arcticvault.data
import kotlinx.coroutines.flow.Flow


interface ReminderRepository {
    suspend fun insertReminder(reminder: Reminder)
    fun getAllReminderStream(): Flow<List<Reminder>>


}