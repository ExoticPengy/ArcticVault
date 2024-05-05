package com.example.arcticvault

import android.app.Application
import com.example.arcticvault.data.AppContainer
import com.example.arcticvault.data.AppDataContainer

class ArcticVaultApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
