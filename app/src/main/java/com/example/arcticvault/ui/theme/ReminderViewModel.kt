package com.example.arcticvault.ui.theme

import androidx.lifecycle.ViewModel
import com.example.arcticvault.data.Reminder
import com.example.arcticvault.data.ReminderRepository
import com.example.arcticvault.model.ReminderEntryModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ReminderViewModel(private val reminderRepository: ReminderRepository): ViewModel() {
    private val _uiState = MutableStateFlow(ReminderUiState())
    val uiState: StateFlow<ReminderUiState> = _uiState.asStateFlow()

    fun getRemindersByStatus(status: String): Flow<List<Reminder>> = reminderRepository.getRemindersByStatus(status)

}