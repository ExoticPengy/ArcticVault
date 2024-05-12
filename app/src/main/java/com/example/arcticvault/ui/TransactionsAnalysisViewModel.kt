package com.example.arcticvault.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arcticvault.R
import com.example.arcticvault.data.Category
import com.example.arcticvault.data.CategoryRepository
import com.example.arcticvault.data.Transaction
import com.example.arcticvault.data.TransactionsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TransactionsAnalysisViewModel(
    private val transactionRepository: TransactionsRepository,
    private val categoryRepository: CategoryRepository
    ): ViewModel() {
    private val _uiState = MutableStateFlow(TransactionsAnalysisUiState())

    val uiState: StateFlow<TransactionsAnalysisUiState> = _uiState.asStateFlow()

    var transactionsList: List<Transaction> = listOf()
    var categoryList: List<Category> = listOf()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            transactionRepository.getAllTransactionsStream().collect {
                transactionsList = it
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            categoryRepository.getAllCategoriesStream().collect {
                categoryList = it
            }
        }
    }

    val selectedCategoryList = mutableListOf<Int>()
    var selection by mutableStateOf("Income")
    var yearOnly by mutableStateOf(false)
    var date1YearExpand by mutableStateOf(false)
    var date1Year by mutableStateOf("Year")
    var date1MonthExpand by mutableStateOf(false)
    var date1Month by mutableStateOf("Mon")
    var date2YearExpand by mutableStateOf(false)
    var date2Year by mutableStateOf("Year")
    var date2MonthExpand by mutableStateOf(false)
    var date2Month by mutableStateOf("Mon")

    fun updateUiState(transactionsList: List<Transaction>, categoryList: List<Category>, selectedCategoryList: List<Int>) {
        _uiState.value = TransactionsAnalysisUiState(
            transactionsList, categoryList, selectedCategoryList)
    }

    fun getMonthBackground(): Int {
        return if (yearOnly) R.drawable.datebackgroundinactive else R.drawable.datebackgroundactive
    }

    fun changeSelection(categoryId: Int) {
        if (!selectedCategoryList.contains(categoryId))
            selectedCategoryList.add(element = categoryId)
        else
            selectedCategoryList.remove(element = categoryId)
        updateUiState(transactionsList, categoryList, selectedCategoryList)
    }

    fun filterTransactionByCategory() {
        val transactionListByCategory = mutableListOf<List<Transaction>>()
        for (i in 0 until selectedCategoryList.size) {
            val filteredTransactionsList = mutableListOf<Transaction>()
            for (transaction in transactionsList) {
                if (transaction.categoryId == selectedCategoryList[i]) {
                    filteredTransactionsList.add(transaction)
                }
            }
            transactionListByCategory.add(filteredTransactionsList)
        }
    }

    private fun filterByDate1(transactionListByCategory: List<List<Transaction>>) {
        val filteredByDate1 = mutableListOf<List<Transaction>>()
       for (transactionLists in transactionListByCategory) {
           val date1List = mutableListOf<Transaction>()
           for (transaction in transactionLists) {
               var year = transaction.date
               var month = transaction.date
               year = year.replaceBeforeLast("/", "")
               year = year.replace("/", "")
               if(!yearOnly) {
                   month = month.replaceBefore("/", "")
                   month = month.replaceAfterLast("/", "")
                   month = month.replace("/", "")
                   month = monthName(month)
               }
               if(yearOnly && year == date1Year) {
                   date1List.add(transaction)
               }
               else if(year == date1Year && month == date1Month) {
                   date1List.add(transaction)
               }
           }
           filteredByDate1.add(date1List)
       }
    }

    private fun filterByDate2(transactionListByCategory: List<List<Transaction>>) {
        val filteredByDate2 = mutableListOf<List<Transaction>>()
        for (transactionLists in transactionListByCategory) {
            val date2List = mutableListOf<Transaction>()
            for (transaction in transactionLists) {
                var year = transaction.date
                var month = transaction.date
                year = year.replaceBeforeLast("/", "")
                year = year.replace("/", "")
                if(!yearOnly) {
                    month = month.replaceBefore("/", "")
                    month = month.replaceAfterLast("/", "")
                    month = month.replace("/", "")
                    month = monthName(month)
                }
                if(yearOnly && year == date1Year) {
                    date2List.add(transaction)
                }
                else if(year == date1Year && month == date1Month) {
                    date2List.add(transaction)
                }
            }
            filteredByDate2.add(date2List)
        }
    }

    fun findMaxRange(): Double {
        var highest = 0.0
        for (transaction in transactionsList) {
            if (transaction.amount > highest)
                highest = transaction.amount
        }
        return highest
    }

    fun findYearOptions(): List<String> {
        val yearList = mutableListOf<String>()
        for(transaction in transactionsList) {
            var date = transaction.date
            date = date.replaceBeforeLast("/", "")
            date = date.replace("/", "")
            if (!yearList.contains(date) && date != "Date") {
                yearList.add(date)
            }
        }
        return yearList
    }

    fun findMonthOptions(): List<String> {
        val monthList = mutableListOf<String>()
        for(transaction in transactionsList) {
            var date = transaction.date
            date = date.replaceBefore("/", "")
            date = date.replaceAfterLast("/", "")
            date = date.replace("/", "")
            date = monthName(date)
            if (!monthList.contains(date) && date != "Date") {
                monthList.add(date)
            }
        }
        return monthList
    }

    private fun monthName(month: String): String {
        return when (month) {
            "01" -> "JAN"
            "02" -> "FEB"
            "03" -> "MAR"
            "04" -> "APR"
            "05" -> "MAY"
            "06" -> "JUN"
            "07" -> "JUL"
            "08" -> "AUG"
            "09" -> "SEP"
            "10" -> "OCT"
            "11" -> "NOV"
            "12" -> "DEC"
            else -> "Date"
        }
    }
}