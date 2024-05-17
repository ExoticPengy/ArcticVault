package com.example.arcticvault

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.arcticvault.ui.theme.ArcticVaultTheme


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

