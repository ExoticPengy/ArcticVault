package com.example.arcticvault.data

import kotlinx.coroutines.flow.Flow

class OfflineEditGoalsRepository(private val editGaolsDao: EditGaolsDao) : EditGoalsRepository {
    override suspend fun insertEditGoals(editGoals: EditGoals) = editGaolsDao.insert(editGoals)

    override suspend fun updateEditGoals(editGoals: EditGoals) = editGaolsDao.update(editGoals)

    override suspend fun deleteEditGoals(editGoals: EditGoals) = editGaolsDao.delete(editGoals)

    override fun getEditGoalsStream(id: Int): Flow<EditGoals?> = editGaolsDao.getEditGoals(id)

    override fun getAllEditGoalsStream(): Flow<List<EditGoals>> = editGaolsDao.getAllEditGoals()
}