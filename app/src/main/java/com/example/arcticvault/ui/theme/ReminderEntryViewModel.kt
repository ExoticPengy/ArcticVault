package com.example.arcticvault.ui.theme

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arcticvault.data.Reminder
import com.example.arcticvault.data.ReminderRepository
import com.example.arcticvault.model.ReminderEntryModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale

class ReminderEntryViewModel(savedStateHandle: SavedStateHandle,
                             private val reminderRepository: ReminderRepository): ViewModel() {
    private val _uiState = MutableStateFlow(ReminderEntryUiState())
    val uiState: StateFlow<ReminderEntryUiState> = _uiState.asStateFlow()
    private val reminderId: Int = savedStateHandle[AddReminderDestination.reminderIdArg] ?: -1

    init {
        if (reminderId != -1) {
            viewModelScope.launch(Dispatchers.IO) {
                updateUiState(
                    reminderRepository.getReminderStream(reminderId)
                        .filterNotNull()
                        .first()
                        .reminderToModel()
                )
            }
        }
    }


    fun validateInput(uiState: ReminderEntryUiState): Boolean{
        return with(uiState){
            reminder.id.toString().isNotBlank() &&
                    reminder.title.isNotBlank() &&
                    reminder.desc.isNotBlank() &&
                    reminder.amount.toString().isNotBlank() &&
                    reminder.date.isNotBlank()
                    //reminder.repeat.isNotBlank() &&
                    //reminder.category.isNotBlank()
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

    private fun Reminder.reminderToModel(): ReminderEntryModel = ReminderEntryModel(
        id = id,
        title = title,
        desc = desc,
        amount = amount,
        date = date,
        repeat = repeat,
        category = category,
        status = status
    )

    suspend fun saveReminder(){
        if (validateInput(_uiState.value)){
            if (reminderId == 0){
                reminderRepository.insertReminder(_uiState.value.reminder.reminderToData())
            }
            else
                reminderRepository.updateReminder(_uiState.value.reminder.reminderToData())
        }
    }

    suspend fun deleteReminder(uiState: ReminderEntryUiState) {
        if (validateInput(uiState)) {
            if (reminderId != -1)
                reminderRepository.deleteReminder(_uiState.value.reminder.reminderToData())
        }
    }

    /*fun formatAmount(amount: Double): String {
        return NumberFormat.getCurrencyInstance(Locale("en", "MY")).format(amount)
    }
    fun updateAmount(newAmount: String): Double {
        var doubleAmount = newAmount.replace("RM", "")
        doubleAmount = doubleAmount.replace(",", "")
        return doubleAmount.toDoubleOrNull() ?: 0.0
    }*/

}