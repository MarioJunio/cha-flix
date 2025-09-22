package com.jetbrains.kmpapp

import br.com.mj.shared.di.sharedModule
import br.com.mj.shared.settings.IosSettingsInitializer
import com.jetbrains.kmpapp.di.viewModelModule
import org.koin.core.context.startKoin

/**
 * iOS-specific Koin initialization for the Compose app
 * This function can be called from Swift
 */
fun doInitKoin() {
    // Initialize iOS Settings
    IosSettingsInitializer.initialize()
    
    // Start Koin with all modules
    startKoin {
        modules(sharedModule, viewModelModule)
    }
}
