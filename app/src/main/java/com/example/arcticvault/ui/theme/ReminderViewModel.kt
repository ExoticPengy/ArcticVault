package com.example.arcticvault.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arcticvault.data.Reminder
import com.example.arcticvault.data.ReminderRepository
import com.example.arcticvault.model.ReminderEntryModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ReminderViewModel(private val reminderRepository: ReminderRepository): ViewModel() {
    private val _uiState = MutableStateFlow(ReminderUiState())
    val uiState: StateFlow<ReminderUiState> = _uiState.asStateFlow()

    private var reminderList: List<Reminder> = listOf()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            reminderRepository.getAllReminderStream().collect {reminders ->
                val upcoming = reminders.filter { it.status == "upcoming" }
                val completed = reminders.filter { it.status == "completed" }
                val late = reminders.filter { it.status == "late" }
                _uiState.value = ReminderUiState(upcoming, completed, late)
            }
        }
    }

    data class ReminderUiState(
        val upcomingReminders: List<Reminder> = listOf(),
        val completedReminders: List<Reminder> = listOf(),
        val lateReminders: List<Reminder> = listOf()
    )

    fun updateUiState(reminderList: List<Reminder>) {
        _uiState.value = ReminderUiState(reminderList)
    }
    fun getRemindersByStatus(status: String): Flow<List<Reminder>> {
        return reminderRepository.getRemindersByStatus(status)
    }

    fun updateReminder(reminder: Reminder) {
        viewModelScope.launch(Dispatchers.IO) {
            reminderRepository.updateReminder(reminder)
        }
    }

}