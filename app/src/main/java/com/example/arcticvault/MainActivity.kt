package com.example.arcticvault

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.arcticvault.ui.theme.ArcticVaultApp
import com.example.arcticvault.ui.theme.ArcticVaultTheme
import com.example.arcticvault.ui.theme.Budgeting
import com.example.arcticvault.ui.theme.Debt
import com.example.arcticvault.ui.theme.DebtDetails
import com.example.arcticvault.ui.theme.EditGoalsInput
import com.example.arcticvault.ui.theme.Edit_Goals
import com.example.arcticvault.ui.theme.LoginRegisterScreen
import com.example.arcticvault.ui.theme.LoginScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArcticVaultTheme {
                ArcticVaultApp()
//                Debt()
            }
        }
    }
}