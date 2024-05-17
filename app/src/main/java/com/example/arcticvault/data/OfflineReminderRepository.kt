package com.example.arcticvault.data

import kotlinx.coroutines.flow.Flow

class OfflineReminderRepository(private val reminderDao: ReminderDao) : ReminderRepository {
    override suspend fun insertReminder(reminder: Reminder) = reminderDao.insert(reminder)
    override suspend fun deleteReminder(reminder: Reminder) = reminderDao.delete(reminder)
    override suspend fun updateReminder(reminder: Reminder) = reminderDao.update(reminder)

    override fun getAllReminderStream(): Flow<List<Reminder>> = reminderDao.getAllReminder()
    override fun getReminderStream(id: Int): Flow<Reminder?> = reminderDao.getReminder(id)
    override fun getRemindersByStatus(status: String): Flow<List<Reminder>> =
        reminderDao.getRemindersByStatus(status)

}