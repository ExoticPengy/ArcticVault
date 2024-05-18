package com.example.arcticvault.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arcticvault.data.Reminder
import com.example.arcticvault.data.ReminderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ReminderViewModel(private val reminderRepository: ReminderRepository): ViewModel() {
    private val _uiState = MutableStateFlow(ReminderUiState())
    val uiState: StateFlow<ReminderUiState> = _uiState.asStateFlow()

    private var reminderList: List<Reminder> = listOf()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            reminderRepository.getAllReminderStream().collect {
                updateUiState(it)
            }
        }
    }


    fun updateUiState(reminderList: List<Reminder>) {
        val upcomingReminders = reminderList.filter { it.status == "Upcoming" }
        val completedReminders = reminderList.filter { it.status == "Completed" }
        val lateReminders = reminderList.filter { it.status == "Late" }
        _uiState.value = ReminderUiState(
            upcomingReminders = upcomingReminders,
            completedReminders = completedReminders,
            lateReminders = lateReminders
        )
    }



    data class ReminderUiState(
        val upcomingReminders: List<Reminder> = listOf(),
        val completedReminders: List<Reminder> = listOf(),
        val lateReminders: List<Reminder> = listOf()
    )
    fun getRemindersByStatus(status: String): List<Reminder> {
        return reminderList.filter { it.status == status }
    }

    fun updateReminder(reminder: Reminder) {
        viewModelScope.launch(Dispatchers.IO) {
            reminderRepository.updateReminder(reminder)
        }
    }

}