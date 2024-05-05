package com.example.arcticvault.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.arcticvault.ArcticVaultApplication

object AppViewModelProvider {
    val Factory = viewModelFactory {

        initializer {
            EditTransactionViewModel(
                arcticVaultApplication().container.transactionsRepository
            )
        }
    }
}

fun CreationExtras.arcticVaultApplication(): ArcticVaultApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as ArcticVaultApplication)
