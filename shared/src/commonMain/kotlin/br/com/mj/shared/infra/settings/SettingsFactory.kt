package br.com.mj.shared.infra.settings

/**
 * Factory for creating Settings instances
 * This allows for lazy initialization of settings
 */
object SettingsFactory {
    private var _settings: Settings? = null
    
    fun initialize(settings: Settings) {
        _settings = settings
    }
    
    fun create(): Settings {
        return _settings ?: throw IllegalStateException(
            "Settings not initialized. Call SettingsFactory.initialize() first in your Application class."
        )
    }
    
    fun isInitialized(): Boolean {
        return _settings != null
    }
}
