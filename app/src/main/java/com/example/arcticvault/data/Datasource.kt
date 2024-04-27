package com.example.arcticvault.data

import com.example.arcticvault.R
import com.example.arcticvault.model.Transaction

class Datasource() {
    fun loadTransactions(): List<Transaction> {
        return listOf<Transaction>(
            Transaction(R.drawable.expense, "Facility Repairs", "02.15 PM", "March 8", 1000.0),
            Transaction(R.drawable.income, "Investors", "02.15 PM", "March 8", 3000.0),
            Transaction(R.drawable.income, "Sales", "02.15 PM", "March 8", 70.0),
            Transaction(R.drawable.expense, "Facility Repairs", "02.15 PM", "March 8", 1000.0),
            Transaction(R.drawable.income, "Investors", "02.15 PM", "March 8", 3000.0),
            Transaction(R.drawable.income, "Sales", "02.15 PM", "March 8", 70.0),
            Transaction(R.drawable.expense, "Facility Repairs", "02.15 PM", "March 8", 1000.0),
            Transaction(R.drawable.income, "Investors", "02.15 PM", "March 8", 3000.0),
            Transaction(R.drawable.income, "Sales", "02.15 PM", "March 8", 70.0),
            Transaction(R.drawable.expense, "Facility Repairs", "02.15 PM", "March 8", 1000.0),
            Transaction(R.drawable.income, "Investors", "02.15 PM", "March 8", 3000.0),
            Transaction(R.drawable.income, "Sales", "02.15 PM", "March 8", 70.0)
        )
    }
}