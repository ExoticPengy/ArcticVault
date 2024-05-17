package com.example.arcticvault.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arcticvault.R
import com.example.arcticvault.data.Category
import com.example.arcticvault.data.CategoryRepository
import com.example.arcticvault.data.Transaction
import com.example.arcticvault.data.TransactionsRepository
import com.example.arcticvault.model.CategoryModel
import com.example.arcticvault.model.TransactionModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale

class AllTransactionsViewModel(
    private val transactionRepository: TransactionsRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {
    private val firebaseDb = Firebase.firestore
    private val categoryRef = firebaseDb.collection("category")
    private val transactionRef = firebaseDb.collection("transaction")
    private val onlineTransactionList = mutableListOf<Transaction>()
    private val onlineCategoryList = mutableListOf<Category>()

    init {
        categoryRef.get().addOnSuccessListener { documents ->
            for (document in documents) {
                onlineCategoryList.add(document.toObject<CategoryModel>().categoryToData())
            }
        }

        transactionRef.get().addOnSuccessListener { documents ->
            for (document in documents) {
                onlineTransactionList.add(document.toObject<TransactionModel>().transactionToData())
            }
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val allTransactionsUiState: StateFlow<AllTransactionUiState> =
        transactionRepository.getAllTransactionsStream().map { AllTransactionUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = AllTransactionUiState()
            )

    init {
        viewModelScope.launch {
            categoryRepository.getAllCategoriesStream().collect {
                categoryList = it
            }
        }
    }

    var categoryList: List<Category> = listOf()
    var typeFilter by mutableIntStateOf(R.string.income)
    var dateFilter by mutableStateOf("Select a date")
    var titleFilter by mutableStateOf("")
    var showDatePicker by mutableStateOf(false)
    var showFilterDialog by mutableStateOf(false)
    var selectedCategoryId by mutableIntStateOf(0)
    var selectedCategoryFilter by mutableIntStateOf(0)

    fun changeTypeFilter(type: Int) {
        typeFilter = type
    }

    fun checkTitleFilter(title: String): Boolean {
        if (titleFilter != "") {
            return title.contains(titleFilter, ignoreCase = true)
        }
        return true
    }

    fun checkDateFilter(date: String): Boolean {
        if (dateFilter != "Select a date") {
            return date == dateFilter
        }
        return true
    }

    fun checkCategoryFilter(categoryId: Int?): Boolean {
        if (selectedCategoryFilter != 0) {
            return categoryId == selectedCategoryFilter
        }
        return true
    }

    fun updateIconDesc(iconId: Int): Int {
        return if (iconId == R.drawable.expense) {
            R.string.expense_desc
        } else R.string.income_desc
    }

    fun calculateTotal(transactions: List<Transaction>): String {
        var total = 0.0
        for (transaction in transactions) {
            if (transaction.type == typeFilter && checkTitleFilter(transaction.title) && checkDateFilter(
                    transaction.date
                )
            ) {
                total += transaction.amount
            }
        }
        return formatAmount(total)
    }

    fun formatAmount(amount: Double): String {
        return NumberFormat.getCurrencyInstance(Locale("en", "MY")).format(amount)
    }

    private fun CategoryModel.categoryToData(): Category = Category(
        id = id,
        title = title,
        color = color,
        inUse = inUse
    )

    private fun TransactionModel.transactionToData(): Transaction = Transaction(
        id = id,
        icon = icon,
        type = type,
        title = title,
        time = time,
        date = date,
        description = description,
        amount = amount,
        categoryId = categoryId
    )

    fun onSyncClicked(categoryList: List<Category>, transactionList: List<Transaction>) {
        for (category in categoryList) {
            var exists = false
            for (onlineCategory in onlineCategoryList) {
                if (
                    onlineCategory.title == category.title &&
                    onlineCategory.color == category.color &&
                    onlineCategory.id == category.id
                )
                    exists = true
            }
            if (!exists)
                categoryRef.add(category.copy(inUse = 0))
        }

        for (transaction in transactionList) {
            var exists = false
            for (onlineTransaction in onlineTransactionList) {
                if (onlineTransaction == transaction)
                    exists = true
            }
            if (!exists)
                transactionRef.add(transaction.copy(categoryId = null))
        }

        for (onlineCategory in onlineCategoryList) {
            var exists = false
            for (category in categoryList) {
                if (
                    onlineCategory.title == category.title &&
                    onlineCategory.color == category.color &&
                    onlineCategory.id == category.id
                )
                    exists = true
            }
            if (!exists) {
                viewModelScope.launch {
                    addCategory(onlineCategory.copy(inUse = 0))
                }
            }
        }

        for (onlineTransaction in onlineTransactionList) {
            var exists = false
            for (transaction in transactionList) {
                if (onlineTransaction == transaction)
                    exists = true
            }
            if (!exists) {
                viewModelScope.launch {
                    addTransaction(onlineTransaction.copy(categoryId = null))
                }
            }
        }
    }

    private suspend fun addCategory(category: Category) {
        categoryRepository.insertCategory(category)
    }

    private suspend fun addTransaction(transaction: Transaction) {
        transactionRepository.insertTransaction(transaction)
    }
}