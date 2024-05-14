package com.example.arcticvault.ui.theme

import androidx.lifecycle.ViewModel
import com.example.arcticvault.data.Reminder
import com.example.arcticvault.data.ReminderRepository
import com.example.arcticvault.model.ReminderEntryModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ReminderEntryViewModel(private val reminderRepository: ReminderRepository): ViewModel() {
    private val _uiState = MutableStateFlow(ReminderEntryUiState())
    val uiState: StateFlow<ReminderEntryUiState> = _uiState.asStateFlow()


    fun validateInput(uiState: ReminderEntryUiState): Boolean{
        return with(uiState){
            reminder.id.toString().isNotBlank() &&
                    reminder.title.isNotBlank() &&
                    reminder.desc.isNotBlank() &&
                    reminder.amount.toString().isNotBlank() &&
                    reminder.date.isNotBlank() &&
                    reminder.repeat.isNotBlank() &&
                    reminder.category.isNotBlank()
        }
    }

    fun updateUiState(reminderEntryModel: ReminderEntryModel){
        _uiState.value = ReminderEntryUiState(
            reminder = reminderEntryModel
        )
    }

    private fun ReminderEntryModel.reminderToData(): Reminder = Reminder(
        id = id,
        title = title,
        desc = desc,
        amount = amount,
        date = date,
        repeat = repeat,
        category = category,
        status = status
    )

    private fun ReminderEntryModel.reminderToModel(): Reminder = Reminder(
        id = id,
        title = title,
        desc = desc,
        amount = amount,
        date = date,
        repeat = repeat,
        category = category,
        status = status
    )

    /*suspend fun saveReminder(reminderEntryModel: ReminderEntryModel){
        if (validateInput(_uiState.value)){
            when (reminderId)
        }
    }*/

}