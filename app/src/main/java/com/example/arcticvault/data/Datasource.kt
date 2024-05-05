package com.example.arcticvault.data

import com.example.arcticvault.R
import com.example.arcticvault.model.TransactionModel

class Datasource() {
    fun loadTransactions(): List<TransactionModel> {
        return listOf<TransactionModel>(
            TransactionModel(0, R.drawable.expense, R.string.income, "Facility Repairs", "02.15 PM", "March 8", 1000.0),
            TransactionModel(1, R.drawable.income,  R.string.income, "Investors", "02.15 PM", "March 8", 3000.0),
            TransactionModel(2, R.drawable.income,  R.string.income, "Sales", "02.15 PM", "March 8", 70.0),
            TransactionModel(3, R.drawable.expense,  R.string.income, "Facility Repairs", "02.15 PM", "March 8", 1000.0),
            TransactionModel(4, R.drawable.income,  R.string.income, "Investors", "02.15 PM", "March 8", 3000.0),
            TransactionModel(5, R.drawable.income,  R.string.income, "Sales", "02.15 PM", "March 8", 70.0),
            TransactionModel(6, R.drawable.expense,  R.string.income, "Facility Repairs", "02.15 PM", "March 8", 1000.0),
            TransactionModel(7, R.drawable.income,  R.string.income, "Investors", "02.15 PM", "March 8", 3000.0),
            TransactionModel(8, R.drawable.income,  R.string.income, "Sales", "02.15 PM", "March 8", 70.0),
            TransactionModel(9, R.drawable.expense,  R.string.income, "Facility Repairs", "02.15 PM", "March 8", 1000.0),
            TransactionModel(10, R.drawable.income,  R.string.income, "Investors", "02.15 PM", "March 8", 3000.0),
            TransactionModel(11, R.drawable.income,  R.string.income, "Sales", "02.15 PM", "March 8", 70.0)
        )
    }
}