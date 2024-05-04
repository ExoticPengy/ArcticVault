package com.example.arcticvault.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.arcticvault.model.Transaction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.NumberFormat
import java.util.Locale

class EditTransactionViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(EditTransactionUiState())
    val uiState: StateFlow<EditTransactionUiState> = _uiState.asStateFlow()

    var showDatePicker by mutableStateOf(false)

    fun updateUiState(transaction: Transaction) {
        _uiState.value = EditTransactionUiState(
            transaction = transaction
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

//    suspend fun saveTransaction() {
//        if (validateInput()) {
//            itemsRepository.insertTransaction()
//        }
//    }

}