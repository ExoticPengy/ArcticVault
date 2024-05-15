package com.example.arcticvault.ui.theme

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.arcticvault.Data.Debt
import com.example.arcticvault.Data.DebtRepository
import com.example.arcticvault.model.DebtInputModel
import kotlinx.coroutines.flow.MutableStateFlow

class DebtEntryViewModel(savedStateHandle: SavedStateHandle,
                             private val debtRepository: DebtRepository): ViewModel() {
    private val debtId: Int = savedStateHandle.get<Int>(DebtDestination.route) ?: -1
    private val debtUiState = MutableStateFlow(DebtUiState())

    data class DebtUiState(
        val debtDetails: DebtInputModel = DebtInputModel(),
        val isEntryValid: Boolean = false
    )

    fun validateInput(uiState: DebtUiState): Boolean {
        return with(uiState.debtDetails) {
            id.toString().isNotBlank() &&
                    categories.isNotBlank() &&
                    nickname.isNotBlank() &&
                    currentBalance.toString().isNotBlank() &&
                    annualRate.toString().isNotBlank() &&
                    minPayment.toString().isNotBlank() &&
                    remark.isNotBlank()
        }
    }

    fun updateUiState(debtInputModel: DebtInputModel) {
        debtUiState.value = DebtUiState(
            debtDetails = debtInputModel
        )
    }

    private fun DebtInputModel.debtToData(): Debt = Debt(
        id = id,
        categories = categories,
        nickname = nickname,
        currentBalance = currentBalance,
        annualRate = annualRate,
        paymentFrequency = paymentFrequency,
        minPayment = minPayment,
        remark = remark,
        date = date
    )

    suspend fun saveDebt() {
        if (validateInput(debtUiState.value)) {
            debtRepository.insertDebt(debtUiState.value.debtDetails.debtToData())
        }
    }

    suspend fun deleteDebt() {
        if (debtId != -1 && validateInput(debtUiState.value)) {
            debtRepository.deleteDebt(debtUiState.value.debtDetails.debtToData())
        }
    }
}
