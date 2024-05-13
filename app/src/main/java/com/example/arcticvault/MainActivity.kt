package com.example.arcticvault

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.arcticvault.ui.theme.ABC
import com.example.arcticvault.ui.theme.ArcticVaultApp
import com.example.arcticvault.ui.theme.ArcticVaultTheme
import com.example.arcticvault.ui.theme.Budgeting
import com.example.arcticvault.ui.theme.EditGoalsInput
import com.example.arcticvault.ui.theme.Edit_Goals

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArcticVaultTheme {
                ArcticVaultApp()
            }
        }
    }
}