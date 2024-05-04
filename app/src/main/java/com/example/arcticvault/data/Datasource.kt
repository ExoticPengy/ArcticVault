package com.example.arcticvault.data

import com.example.arcticvault.R
import com.example.arcticvault.model.Transaction

class Datasource() {
    fun loadTransactions(): List<Transaction> {
        return listOf<Transaction>(
            Transaction(0, R.drawable.expense, "Income", "Facility Repairs", "02.15 PM", "March 8", 1000.0),
            Transaction(1, R.drawable.income, "Income", "Investors", "02.15 PM", "March 8", 3000.0),
            Transaction(2, R.drawable.income, "Income", "Sales", "02.15 PM", "March 8", 70.0),
            Transaction(3, R.drawable.expense, "Income", "Facility Repairs", "02.15 PM", "March 8", 1000.0),
            Transaction(4, R.drawable.income, "Income", "Investors", "02.15 PM", "March 8", 3000.0),
            Transaction(5, R.drawable.income, "Income", "Sales", "02.15 PM", "March 8", 70.0),
            Transaction(6, R.drawable.expense, "Income", "Facility Repairs", "02.15 PM", "March 8", 1000.0),
            Transaction(7, R.drawable.income, "Income", "Investors", "02.15 PM", "March 8", 3000.0),
            Transaction(8, R.drawable.income, "Income", "Sales", "02.15 PM", "March 8", 70.0),
            Transaction(9, R.drawable.expense, "Income", "Facility Repairs", "02.15 PM", "March 8", 1000.0),
            Transaction(10, R.drawable.income, "Income", "Investors", "02.15 PM", "March 8", 3000.0),
            Transaction(11, R.drawable.income, "Income", "Sales", "02.15 PM", "March 8", 70.0)
        )
    }
}