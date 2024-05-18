package com.example.arcticvault.ui.theme

import android.util.Log
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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ReminderEntryViewModel(savedStateHandle: SavedStateHandle,
                             private val reminderRepository: ReminderRepository): ViewModel() {
    private val _uiState = MutableStateFlow(ReminderEntryUiState())
    val uiState: StateFlow<ReminderEntryUiState> = _uiState.asStateFlow()
    private val reminderId: Int = savedStateHandle[AddReminderDestination.reminderIdArg] ?: -1

    private var reminderList: List<Reminder> = listOf()

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
        viewModelScope.launch(Dispatchers.IO) {
            reminderRepository.getAllReminderStream().collect{
                reminderList = it
            }
        }
    }

    fun validateAmount(amount: Double): Boolean {
        return amount > 0
    }


    fun validateInput(uiState: ReminderEntryUiState): String{
        return with(uiState.reminder){
            when{
                title.isBlank() -> "Title is required"
                desc.isBlank() -> "Description is required"
                amount <= 0 -> "Amount must be greater than zero"
                date.isBlank() -> "Date is required"
                repeat.isBlank() -> "Repeat frequency is required"
                else -> ""
            }
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



    private fun getNextDate(currentDate: String, repeat: String): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val calendar = Calendar.getInstance().apply {
            time = dateFormat.parse(currentDate)!!
        }

        when (repeat) {
            "Weekly" -> calendar.add(Calendar.DAY_OF_YEAR, 7)
            "Monthly" -> calendar.add(Calendar.MONTH, 1)
            "Yearly" -> calendar.add(Calendar.YEAR, 1)
        }

        return dateFormat.format(calendar.time)
    }

    fun resetUiState() {
        _uiState.value = _uiState.value.copy(
            reminder = ReminderEntryModel(
                id = 0,
                title = "",
                desc = "",
                amount = 0.0,
                date = "",
                repeat = "",
                status = ""
            )
        )
    }


    suspend fun saveReminder(): String {
        val validationMessage = validateInput(_uiState.value)
        return if (validationMessage.isEmpty() && validateAmount(_uiState.value.reminder.amount)) {
            val reminderData = _uiState.value.reminder.reminderToData()
            val repeatFrequency = _uiState.value.reminder.repeat
            Log.d("ReminderViewModel", "Saving reminder")

            if (reminderId == 0) {
                reminderRepository.insertReminder(reminderData)
                Log.d("ReminderViewModel", "Saved")
            } else {
                reminderRepository.updateReminder(reminderData)
            }
            if (repeatFrequency != "Once") {
                var nextDate = getNextDate(reminderData.date, repeatFrequency)
                repeat(4) {
                    val repeatedReminder = reminderData.copy(date = nextDate)
                    reminderRepository.insertReminder(repeatedReminder)
                    nextDate = getNextDate(nextDate, repeatFrequency)
                }
            }

            ""
        } else {
            validationMessage
        }
    }

    suspend fun deleteReminder(reminder: Reminder) {
        reminderRepository.deleteReminder(reminder)
    }



}