package com.example.arcticvault.ui

import androidx.lifecycle.ViewModel
import com.example.arcticvault.data.Debt
import com.example.arcticvault.data.DebtRepository
import com.example.arcticvault.model.DebtInputModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DebtEntryViewModel(
    private val debtRepository: DebtRepository
): ViewModel() {
    private val _debtUiState = MutableStateFlow(DebtUiState())
    val debtUiState = _debtUiState.asStateFlow()

    init {

    }

    data class DebtUiState(
        val debtDetails: DebtInputModel = DebtInputModel()
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
        _debtUiState.value = DebtUiState(
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
        debtRepository.insertDebt(_debtUiState.value.debtDetails.debtToData())
    }

    suspend fun updateDebt(id: Int?) {
        if (id != null) {
            //get the specific debt
            debtRepository.getDebtStream(id).collect { debt ->
                if (debt != null) {
                    val updatedDebt = debt.copy(
                        categories = _debtUiState.value.debtDetails.categories,
                        nickname = _debtUiState.value.debtDetails.nickname,
                        currentBalance = _debtUiState.value.debtDetails.currentBalance,
                        annualRate = _debtUiState.value.debtDetails.annualRate,
                        paymentFrequency = _debtUiState.value.debtDetails.paymentFrequency,
                        minPayment = _debtUiState.value.debtDetails.minPayment,
                        remark = _debtUiState.value.debtDetails.remark,
                        date = _debtUiState.value.debtDetails.date
                    )
                    println("Updated Debt details: $updatedDebt")
                    debtRepository.updateDebt(updatedDebt)
                }
            }
        }
    }

    suspend fun deleteDebt(debt: Debt) {
            debtRepository.deleteDebt(debt) //_debtUiState.value.debtDetails.debtToData()
    }

    fun getListOfDebt(): Flow<List<Debt>> {
            return debtRepository.getAllDebtStream()
    }

}
