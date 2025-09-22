package br.com.mj.shared.settings

import android.content.Context
import br.com.mj.shared.infra.settings.SettingsFactory

/**
 * Helper class to initialize Settings for Android platform
 */
object AndroidSettingsInitializer {
    
    /**
     * Initialize Settings with Android Context
     * Call this in your Application.onCreate() method
     */
    fun initialize(context: Context) {
        val sharedPreferences = context.getSharedPreferences("cha_flix_prefs", Context.MODE_PRIVATE)
        val settings = AndroidSettings(sharedPreferences)
        SettingsFactory.initialize(settings)
    }
}
