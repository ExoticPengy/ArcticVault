package com.example.arcticvault.data

import kotlinx.coroutines.flow.Flow

class OfflineReminderRepository(private val reminderDao: ReminderDao): ReminderRepository {
    override suspend fun insertReminder(reminder: Reminder) = reminderDao.insert(reminder)
    override fun getAllReminderStream(): Flow<List<Reminder>> = reminderDao.getAllReminder()

}