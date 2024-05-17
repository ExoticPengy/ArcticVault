package com.example.arcticvault.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.yml.charts.common.model.Point
import co.yml.charts.ui.barchart.models.BarData
import co.yml.charts.ui.barchart.models.GroupBar
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
) : ViewModel() {
    private val _uiState = MutableStateFlow(TransactionsAnalysisUiState())

    val uiState: StateFlow<TransactionsAnalysisUiState> = _uiState.asStateFlow()

    private var transactionsList: List<Transaction> = listOf()
    private var categoryList: List<Category> = listOf()
    private var groupBarList: List<GroupBar> = listOf()

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

    private val selectedCategoryList = mutableListOf<Int>()
    var selection by mutableStateOf(0)
    var yearOnly by mutableStateOf(false)
    var date1YearExpand by mutableStateOf(false)
    var date1Year by mutableStateOf("Year")
    var date1MonthExpand by mutableStateOf(false)
    var date1Month by mutableStateOf("Mon")
    var date2YearExpand by mutableStateOf(false)
    var date2Year by mutableStateOf("Year")
    var date2MonthExpand by mutableStateOf(false)
    var date2Month by mutableStateOf("Mon")
    var maxRange by mutableIntStateOf(0)

    fun updateUiState(
        transactionsList: List<Transaction>,
        categoryList: List<Category>,
        selectedCategoryList: List<Int>,
        groupBarList: List<GroupBar>,
        maxRange: Int
    ) {
        _uiState.value = TransactionsAnalysisUiState(
            transactionsList, categoryList, selectedCategoryList, groupBarList, maxRange
        )
    }

    fun getMonthBackground(): Int {
        return if (yearOnly) R.drawable.datebackgroundinactive else R.drawable.datebackgroundactive
    }

    fun changeSelection(categoryId: Int) {
        if (!selectedCategoryList.contains(categoryId))
            selectedCategoryList.add(element = categoryId)
        else
            selectedCategoryList.remove(element = categoryId)
        updateUiState(transactionsList, categoryList, selectedCategoryList, groupBarList, maxRange)
    }

    fun updateBarchartDataList() {
        val transactionByCategory = filterTransactionByCategory(filterTransactionByType())
        val transactionByDate1 = filterByDate1(transactionByCategory)
        val transactionByDate2 = filterByDate2(transactionByCategory)
        groupBarList = makeGroupedBarchartList(transactionByDate1, transactionByDate2)
        updateUiState(transactionsList, categoryList, selectedCategoryList, groupBarList, maxRange)
    }

    private fun filterTransactionByType(): List<Transaction> {
        val transactionListByType = mutableListOf<Transaction>()
        for (transaction in transactionsList) {
            if (transaction.type == selection) {
                transactionListByType.add(transaction)
            }
        }
        return transactionListByType
    }

    private fun filterTransactionByCategory(transactionListByType: List<Transaction>): List<List<Transaction>> {
        val transactionListByCategory = mutableListOf<List<Transaction>>()
        for (i in 0 until selectedCategoryList.size) {
            val filteredTransactionsList = mutableListOf<Transaction>()
            for (transaction in transactionListByType) {
                if (transaction.categoryId == selectedCategoryList[i]) {
                    filteredTransactionsList.add(transaction)
                }
            }
            transactionListByCategory.add(filteredTransactionsList)
        }
        return transactionListByCategory
    }

    private fun filterByDate1(transactionListByCategory: List<List<Transaction>>): List<List<Transaction>> {
        val filteredByDate1 = mutableListOf<List<Transaction>>()
        for (transactionLists in transactionListByCategory) {
            val date1List = mutableListOf<Transaction>()
            for (transaction in transactionLists) {
                var year = transaction.date
                var month = transaction.date
                year = year.replaceBeforeLast("/", "")
                year = year.replace("/", "")
                if (!yearOnly) {
                    month = month.replaceBefore("/", "")
                    month = month.replaceAfterLast("/", "")
                    month = month.replace("/", "")
                    month = monthName(month)
                }
                if (yearOnly && year == date1Year) {
                    date1List.add(transaction)
                } else if (year == date1Year && month == date1Month) {
                    date1List.add(transaction)
                }
            }
            filteredByDate1.add(date1List)
        }
        return filteredByDate1
    }

    private fun filterByDate2(transactionListByCategory: List<List<Transaction>>): List<List<Transaction>> {
        val filteredByDate2 = mutableListOf<List<Transaction>>()
        for (transactionLists in transactionListByCategory) {
            val date2List = mutableListOf<Transaction>()
            for (transaction in transactionLists) {
                var year = transaction.date
                var month = transaction.date
                year = year.replaceBeforeLast("/", "")
                year = year.replace("/", "")
                if (!yearOnly) {
                    month = month.replaceBefore("/", "")
                    month = month.replaceAfterLast("/", "")
                    month = month.replace("/", "")
                    month = monthName(month)
                }
                if (yearOnly && year == date2Year) {
                    date2List.add(transaction)
                } else if (year == date2Year && month == date2Month) {
                    date2List.add(transaction)
                }
            }
            filteredByDate2.add(date2List)
        }
        return filteredByDate2
    }

    private fun makeGroupedBarchartList(
        date1Data: List<List<Transaction>>,
        date2Data: List<List<Transaction>>
    ): List<GroupBar> {
        val totalTransactionAmountList = mutableListOf<Double>()
        for (list in date1Data) {
            var totalTransactionAmount = 0.0
            for (data in list) {
                totalTransactionAmount += data.amount
            }
            totalTransactionAmountList.add(totalTransactionAmount)
        }
        val totalTransactionAmountList2 = mutableListOf<Double>()
        for (list in date2Data) {
            var totalTransactionAmount2 = 0.0
            for (data in list) {
                totalTransactionAmount2 += data.amount
            }
            totalTransactionAmountList2.add(totalTransactionAmount2)
        }
        findMaxRange(totalTransactionAmountList, totalTransactionAmountList2)

        val groupBarList = mutableListOf<GroupBar>()
        for (i in 0 until selectedCategoryList.size) {
            val barList = mutableListOf<BarData>()
            for (j in 0 until 2) {
                val barValue =
                    if (j == 0) totalTransactionAmountList[i] else totalTransactionAmountList2[i]
                var label = ""
                for (category in categoryList) {
                    if (selectedCategoryList[i] == category.id)
                        label = category.title
                }
                barList.add(
                    BarData(
                        Point(
                            i.toFloat(),
                            barValue.toInt().toFloat()
                        ),
                        label = label,
                        description = "Bar at $i with label $label has value ${
                            String.format(
                                "%.2f", barValue
                            )
                        }"
                    )
                )
            }
            groupBarList.add(GroupBar(i.toString(), barList))
        }
        return groupBarList
    }

    private fun findMaxRange(amountList1: List<Double>, amountList2: List<Double>) {
        var highest = 0
        for (amount in amountList1) {
            if (amount.toInt() > highest) {
                highest = amount.toInt()
            }
        }
        for (amount in amountList2) {
            if (amount.toInt() > highest) {
                highest = amount.toInt()
            }
        }
        maxRange = highest
    }

    fun findYearOptions(): List<String> {
        val yearList = mutableListOf<String>()
        for (transaction in transactionsList) {
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
        for (transaction in transactionsList) {
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