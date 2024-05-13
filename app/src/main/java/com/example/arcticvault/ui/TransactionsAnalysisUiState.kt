package com.example.arcticvault.ui

import co.yml.charts.ui.barchart.models.GroupBar
import com.example.arcticvault.data.Category
import com.example.arcticvault.data.Transaction

data class TransactionsAnalysisUiState(
    val transactionList: List<Transaction> = listOf(),
    val categoryList: List<Category> = listOf(),
    val selectionList: List<Int> = listOf(),
    val groupBarChartDataList: List<GroupBar> = listOf(),
    val maxRange: Int = 0
)
