package br.com.mj.shared.settings

import br.com.mj.shared.infra.settings.SettingsFactory
import platform.Foundation.NSUserDefaults

/**
 * Helper class to initialize Settings for iOS platform
 */
object IosSettingsInitializer {
    
    /**
     * Initialize Settings for iOS platform
     * Call this in your iOS app initialization
     */
    fun initialize() {
        val settings = IosSettings(NSUserDefaults.standardUserDefaults)
        SettingsFactory.initialize(settings)
    }
}
