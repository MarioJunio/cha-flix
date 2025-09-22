package br.com.mj.shared.settings

import br.com.mj.shared.infra.settings.Settings
import platform.Foundation.NSUserDefaults

class IosSettings(
    private val userDefaults: NSUserDefaults
) : Settings {
    
    override fun putString(key: String, value: String) {
        userDefaults.setObject(value, key)
    }
    
    override fun getString(key: String, defaultValue: String): String {
        return userDefaults.stringForKey(key) ?: defaultValue
    }
    
    override fun putInt(key: String, value: Int) {
        userDefaults.setInteger(value.toLong(), key)
    }
    
    override fun getInt(key: String, defaultValue: Int): Int {
        return userDefaults.integerForKey(key).toInt()
    }
    
    override fun putLong(key: String, value: Long) {
        userDefaults.setObject(value, key)
    }
    
    override fun getLong(key: String, defaultValue: Long): Long {
        val obj = userDefaults.objectForKey(key)
        return when (obj) {
            is Long -> obj
            is Number -> obj.toLong()
            else -> defaultValue
        }
    }
    
    override fun putFloat(key: String, value: Float) {
        userDefaults.setFloat(value, key)
    }
    
    override fun getFloat(key: String, defaultValue: Float): Float {
        return userDefaults.floatForKey(key)
    }
    
    override fun putBoolean(key: String, value: Boolean) {
        userDefaults.setBool(value, key)
    }
    
    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return userDefaults.boolForKey(key)
    }
    
    override fun remove(key: String) {
        userDefaults.removeObjectForKey(key)
    }
    
    override fun clear() {
        val domain = userDefaults.persistentDomainForName("cha_flix_prefs")
        domain?.keys?.forEach { key ->
            userDefaults.removeObjectForKey(key as String)
        }
    }
    
    override fun hasKey(key: String): Boolean {
        return userDefaults.objectForKey(key) != null
    }
}
