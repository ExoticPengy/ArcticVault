package com.example.arcticvault.data

import kotlinx.coroutines.flow.Flow

interface EditGoalsRepository {
    suspend fun insertEditGoals(editGoals: EditGoals)

    suspend fun updateEditGoals(editGoals: EditGoals)

    suspend fun deleteEditGoals(editGoals: EditGoals)

    fun getEditGoalsStream(id: Int): Flow<EditGoals?>

    fun getAllEditGoalsStream(): Flow<List<EditGoals>>

}