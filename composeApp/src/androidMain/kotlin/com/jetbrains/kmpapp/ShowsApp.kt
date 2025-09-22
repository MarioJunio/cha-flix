package com.jetbrains.kmpapp

import android.app.Application
import br.com.mj.shared.di.sharedModule
import br.com.mj.shared.settings.AndroidSettingsInitializer
import com.jetbrains.kmpapp.di.viewModelModule
import org.koin.core.context.startKoin

class ShowsApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // Initialize Settings BEFORE starting Koin
        AndroidSettingsInitializer.initialize(this)

        // Start Koin with all modules
        startKoin {
            modules(sharedModule, viewModelModule)
        }
    }
}
