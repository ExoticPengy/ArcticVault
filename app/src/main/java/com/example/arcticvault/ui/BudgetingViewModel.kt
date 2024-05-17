package com.example.arcticvault.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arcticvault.data.BudgetingRepository
import com.example.arcticvault.data.Category
import com.example.arcticvault.data.CategoryRepository
import com.example.arcticvault.data.Transaction
import com.example.arcticvault.model.BudgetingInputModel
import com.example.arcticvault.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class BudgetingViewModel(
    savedStateHandle: SavedStateHandle,
    categoryRepository: CategoryRepository,
    private val budgetingRepository: BudgetingRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(BudgetingUiState())
    val uiState: StateFlow<BudgetingUiState> = _uiState.asStateFlow()
    val numberChanges: Int? = savedStateHandle[BudgetingDestination.budgetIdArg]

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }


    val budgetingUiState: StateFlow<BudgetingUiState> =
        budgetingRepository.getAllBudgetingStream().map { BudgetingUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = BudgetingUiState()
            )

    init {
        viewModelScope.launch {
            categoryRepository.getAllCategoriesStream().collect {
                categoryList = it
            }
        }
    }

    var categoryList: List<Category> = listOf()


    fun updateUiState(budgetingModel: BudgetingInputModel) {
        _uiState.value = BudgetingUiState(
            budgeting = budgetingModel
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

    fun calculateTotal(budgeting: List<Transaction>): String {
        var total = 0.0
        for (transaction in budgeting) {
            if (transaction.type == R.string.expense) {
                total += transaction.amount
            }
        }
        return formatAmount(total)
    }

    fun calculateExpenseYear(budgeting: List<Transaction>): Double {
        val currentMonth = getLocalYear()
        var total = 0.0
        for (transaction in budgeting) {
            val transactionMonth = getYearFromDateString(transaction.date)
            if (transactionMonth == currentMonth && transaction.type == R.string.expense) {
                total += transaction.amount
            }
        }
        return total
    }

    fun calculateExpenseYearCategory(budgeting: List<Transaction>, categories: Category?): Double {
        val currentMonth = getLocalYear()
        var total = 0.0
        for (transaction in budgeting) {
            val transactionMonth = getYearFromDateString(transaction.date)
            if (transactionMonth == currentMonth && transaction.type == R.string.expense && transaction.categoryId == categories?.id) {
                total += transaction.amount
            }
        }
        return total
    }

    fun getLocalYear(): String {
        val currentDateTime = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy")
        return currentDateTime.format(formatter)
    }

    fun getYearFromDateString(dateString: String): String {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val date = LocalDate.parse(dateString, formatter)
        return String.format("%04d", date.year)
    }

    fun calculateExpenseMonthAndYear(budgeting: List<Transaction>): Double {
        val currentMonth = getLocalMonthAndYear()
        var total = 0.0
        for (transaction in budgeting) {
            val transactionMonth = getMonthAndYearFromDateString(transaction.date)
            if (transactionMonth == currentMonth && transaction.type == R.string.expense) {
                total += transaction.amount
            }
        }
        return total
    }

    fun calculateExpenseMonthAndYearCategory(
        budgeting: List<Transaction>,
        categories: Category?
    ): Double {
        val currentMonth = getLocalMonthAndYear()
        var total = 0.0

        for (transaction in budgeting) {
            val transactionMonth = getMonthAndYearFromDateString(transaction.date)

            if (transactionMonth == currentMonth && transaction.type == R.string.expense && transaction.categoryId == categories?.id) {
                total += transaction.amount
            }
        }
        return total
    }


    fun getLocalMonthAndYear(): String {
        val currentDateTime = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("MM/yyyy")
        return currentDateTime.format(formatter)
    }

    fun getMonthAndYearFromDateString(dateString: String): String {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val date = LocalDate.parse(dateString, formatter)
        return String.format("%02d/%04d", date.monthValue, date.year)
    }


}