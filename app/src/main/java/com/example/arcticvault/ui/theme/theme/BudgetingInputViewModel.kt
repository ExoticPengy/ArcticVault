package com.example.arcticvault.ui.theme.theme

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arcticvault.Data.Budgeting
import com.example.arcticvault.Data.BudgetingRepository
import com.example.arcticvault.Model.BudgetingInputModel
import com.example.arcticvault.ui.theme.BudgetingDestination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale

class BudgetingInputViewModel(
    savedStateHandle: SavedStateHandle,
    private val budgetingRepository: BudgetingRepository,

) : ViewModel() {
    private val _uiState = MutableStateFlow(BudgetingUiState())
    val uiState: StateFlow<BudgetingUiState> = _uiState.asStateFlow()

    val budgetID: Int? = savedStateHandle[BudgetingDestination.budgetIdArg]
    fun updateUiState(budgetingInputModel: BudgetingInputModel) {
        _uiState.value = BudgetingUiState(
            budgeting = budgetingInputModel
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

    private fun BudgetingInputModel.budgetToData(): Budgeting = Budgeting(
        id = id,
        yearlyBudgeting = yearlyBudgeting,
    )

    private fun Budgeting.budgetToModel(): BudgetingInputModel = BudgetingInputModel(
        id = id,
        yearlyBudgeting = yearlyBudgeting,
    )
    private fun validateInput(uiState: BudgetingUiState): Boolean {
        return with(uiState.budgeting) {
            id.toString().isNotBlank() &&
            yearlyBudgeting.toString().isNotBlank()
        }
    }
    suspend fun saveEditGoals(budgetingInputModel: BudgetingInputModel) {
        if (validateInput(_uiState.value)) { // Pass uiState parameter to validateInput
            when (budgetID) {
                0 -> budgetingRepository.updateBudgeting(budgetingInputModel.budgetToData())
                else -> budgetingRepository.insertBudgeting(budgetingInputModel.budgetToData())
            }
        }
    }
}