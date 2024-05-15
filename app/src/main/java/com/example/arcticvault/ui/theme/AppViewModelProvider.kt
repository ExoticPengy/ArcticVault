package com.example.arcticvault.ui.theme

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.arcticvault.ArcticVaultApplication

object AppViewModelProvider {
    val Factory = viewModelFactory {

        initializer {
            ReminderEntryViewModel(
                this.createSavedStateHandle(),
                arcticVaultApplication().container.reminderRepository
            )
        }


        /*initializer {
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
        }*/
    }
}

fun CreationExtras.arcticVaultApplication(): ArcticVaultApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as ArcticVaultApplication)