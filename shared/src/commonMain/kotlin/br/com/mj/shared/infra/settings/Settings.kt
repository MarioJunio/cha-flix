package br.com.mj.shared.infra.settings

/**
 * Custom Settings interface for multiplatform key-value storage
 * Replaces com.russhwolf.settings.Settings with a simpler implementation
 */
interface Settings {
    fun putString(key: String, value: String)
    fun getString(key: String, defaultValue: String = ""): String
    
    fun putInt(key: String, value: Int)
    fun getInt(key: String, defaultValue: Int = 0): Int
    
    fun putLong(key: String, value: Long)
    fun getLong(key: String, defaultValue: Long = 0L): Long
    
    fun putFloat(key: String, value: Float)
    fun getFloat(key: String, defaultValue: Float = 0f): Float
    
    fun putBoolean(key: String, value: Boolean)
    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean
    
    fun remove(key: String)
    fun clear()
    fun hasKey(key: String): Boolean
}
