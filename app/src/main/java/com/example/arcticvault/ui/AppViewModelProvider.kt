package com.example.arcticvault.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.arcticvault.ArcticVaultApplication

object AppViewModelProvider {
    val Factory = viewModelFactory {

        initializer {
            TransactionsViewModel(
                arcticVaultApplication().container.transactionsRepository
            )
        }

        initializer {
            AllTransactionsViewModel(
                arcticVaultApplication().container.transactionsRepository,
                arcticVaultApplication().container.categoryRepository
            )
        }

        initializer {
            EditTransactionViewModel(
                this.createSavedStateHandle(),
                arcticVaultApplication().container.transactionsRepository,
                arcticVaultApplication().container.categoryRepository
            )
        }

        initializer {
            TransactionsAnalysisViewModel(
                arcticVaultApplication().container.transactionsRepository,
                arcticVaultApplication().container.categoryRepository
            )
        }

        initializer {
            EditGoalsInputViewModel(
                this.createSavedStateHandle(),
                arcticVaultApplication().container.editGoalsRepository
            )
        }

        initializer {
            EditGoalsViewModel(
                this.createSavedStateHandle(),
                arcticVaultApplication().container.categoryRepository,
                arcticVaultApplication().container.editGoalsRepository,

                )
        }

        initializer {
            BudgetingViewModel(
                this.createSavedStateHandle(),
                arcticVaultApplication().container.categoryRepository,
                arcticVaultApplication().container.budgetingRepository
            )
        }

        initializer {
            BudgetingInputViewModel(
                this.createSavedStateHandle(),
                arcticVaultApplication().container.budgetingRepository
            )
        }

        initializer {
            FinancialGoalsViewModel(
                arcticVaultApplication().container.editGoalsRepository
            )
        }
    }
}

fun CreationExtras.arcticVaultApplication(): ArcticVaultApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as ArcticVaultApplication)
