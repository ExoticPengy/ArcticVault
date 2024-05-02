package com.example.arcticvault.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.room.PrimaryKey
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class EditTransactionViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(EditTransactionUiState())
    val uiState: StateFlow<EditTransactionUiState> = _uiState.asStateFlow()

    var id: Int = 0
    @StringRes var icon: Int = 0
    var transaction: String = ""
    var time: String = ""
    var date: String = ""
    var amount: Double = 0.0

    fun updateIcon(newIcon: Int) {
        icon = newIcon
    }

    fun updateTransaction(newTransaction: String) {
        transaction = newTransaction
    }

    fun updateTime(newTime: String) {
        time = newTime
    }

    fun updateDate(newDate: String) {
        date = newDate
    }

    fun updateAmount(newAmount: String) {
        amount = newAmount.toDouble()
    }

    fun loadTransaction() {

    }

    fun addNewTransaction() {

    }

    fun cancelTransaction() {

    }
}