package com.example.arcticvault.ui.theme.theme

import androidx.compose.material3.Text
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arcticvault.Data.Budgeting
import com.example.arcticvault.Data.BudgetingRepository
import com.example.arcticvault.Model.BudgetingInputModel
import com.example.arcticvault.ui.theme.BudgetingDestination
import com.google.firebase.Firebase
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.Dispatchers
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
    private val firebaseDb = Firebase.firestore
    private val budgetingRef = firebaseDb.collection("budgeting")
    val budgetingList = mutableListOf<Budgeting>()

    init {
        budgetingRef.get().addOnSuccessListener{documents ->
            for(document in documents){
                budgetingList.add(document.toObject<BudgetingInputModel>().budgetToData())
            }
        }
    }


    private val _uiState = MutableStateFlow(BudgetingUiState())
    val uiState: StateFlow<BudgetingUiState> = _uiState.asStateFlow()

    val budgetID: Int = savedStateHandle[BudgetingDestination.budgetIdArg] ?: 1

    init {
        viewModelScope.launch {
            updateUiState(
                budgetingRepository.getBudgetingStream(budgetID)
                    .filterNotNull()
                    .first()
                    .budgetToModel()
            )
        }
    }

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

    fun validateInput(uiState: BudgetingUiState): Boolean {
        return with(uiState.budgeting) {
            id.toString().isNotBlank() &&
                    yearlyBudgeting != 0.00
        }
    }

    suspend fun saveBudgeting(budgetingInputModel: BudgetingInputModel) {
        val uiState = _uiState.value
        if (validateInput(uiState)) {
            val budgetingData = budgetingInputModel.budgetToData()
            if (budgetID == budgetingData.id) {
                budgetingRepository.updateBudgeting(budgetingData)
            } else
                budgetingRepository.insertBudgeting(budgetingData)
                budgetingRef.add(budgetingData)
        }
    }

}