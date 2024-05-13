package com.example.arcticvault.ui.theme.theme

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.arcticvault.ArcticVaultApplication
import com.example.arcticvault.ui.TransactionsViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {

        initializer {
            EditGoalsInputViewModel(
                this.createSavedStateHandle(),
                arcticVaultApplication().container.editGoalsRepository
            )
        }

        initializer {
            EditGoalsViewModel(
                this.createSavedStateHandle(),
                arcticVaultApplication().container.editGoalsRepository,
            )
        }

        initializer {
            BudgetingViewModel(
                this.createSavedStateHandle(),
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

    }
}

fun CreationExtras.arcticVaultApplication(): ArcticVaultApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as ArcticVaultApplication)
