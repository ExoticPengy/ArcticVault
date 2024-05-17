package com.example.arcticvault.ui.theme.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arcticvault.Data.EditGoals
import com.example.arcticvault.Data.EditGoalsRepository
import com.example.arcticvault.Model.EditGoalsInputModel
import com.example.arcticvault.ui.theme.EditGoalsDestination
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale

class EditGoalsInputViewModel(
    savedStateHandle: SavedStateHandle,
    private val editGoalsRepository : EditGoalsRepository
) : ViewModel(){

    private val _uiState = MutableStateFlow(EditGoalsInputUiState())
    val uiState: StateFlow<EditGoalsInputUiState> = _uiState.asStateFlow()

    val goalId: Int? = savedStateHandle[EditGoalsDestination.goalIdArg]

    var showDatePicker by mutableStateOf(false)

    init {
        if (goalId != null) {
            viewModelScope.launch {
                updateUiState(editGoalsRepository.getEditGoalsStream(goalId.plus(1))
                        .filterNotNull()
                        .first()
                        .editGoalsToModel()
                )
            }
        }
    }

    fun updateUiState(editGoalsInputModel: EditGoalsInputModel) {
        _uiState.value = EditGoalsInputUiState(
            editGoalsInput = editGoalsInputModel
        )
    }

    fun updateAmount(newAmount: String): Double {
        var doubleAmount = newAmount.replace("RM", "")
        doubleAmount = doubleAmount.replace(",", "")
        return doubleAmount.toDoubleOrNull() ?: 0.0
    }
    fun formatAmount(amount: Double): String {
        return NumberFormat.getCurrencyInstance(Locale("en", "MY")).format(amount)
    }

    private fun validateInput(uiState: EditGoalsInputUiState): Boolean {
        return with(uiState.editGoalsInput) {
            id.toString().isNotBlank() &&
            title.isNotBlank() &&
            amount.toString().isNotBlank() &&
            milestones.toString().isNotBlank() &&
            startDate.isNotBlank() &&
            endDate.isNotBlank() &&
            amountGetDivided.toString().isNotBlank() &&
            dateOfDivided.isNotBlank()
        }
    }

    private fun EditGoalsInputModel.editGoalsToData(): EditGoals = EditGoals(
        id = id,
        title = title,
        amount = amount,
        milestones = milestones,
        startDate = startDate,
        endDate = endDate,
        amountGetDivided = amountGetDivided,
        dateOfDivided = dateOfDivided
    )

    private fun EditGoals.editGoalsToModel(): EditGoalsInputModel = EditGoalsInputModel(
        id = id,
        title = title,
        amount = amount,
        milestones = milestones,
        startDate = startDate,
        endDate = endDate,
        amountGetDivided = amountGetDivided,
        dateOfDivided = dateOfDivided
    )


    suspend fun saveEditGoals(editGoalsInputModel: EditGoalsInputModel) {
        if (validateInput(_uiState.value)) { // Pass uiState parameter to validateInput
            when (goalId) {
                0 -> editGoalsRepository.updateEditGoals(editGoalsInputModel.editGoalsToData())
                1 -> editGoalsRepository.updateEditGoals(editGoalsInputModel.editGoalsToData())
                2 -> editGoalsRepository.updateEditGoals(editGoalsInputModel.editGoalsToData())
                else -> editGoalsRepository.insertEditGoals(editGoalsInputModel.editGoalsToData())
            }
        }
    }

    suspend fun updateEditGoals(editGoalsInputModel: EditGoalsInputModel) {
        if (validateInput(_uiState.value)) { // Pass uiState parameter to validateInput
            editGoalsRepository.updateEditGoals(editGoalsInputModel.editGoalsToData())
        }
    }


}