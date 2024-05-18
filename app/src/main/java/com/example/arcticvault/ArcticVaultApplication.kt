package com.example.arcticvault

import android.app.Application
import com.example.arcticvault.Data.AppContainer
import com.example.arcticvault.Data.AppDataContainer

class ArcticVaultApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}