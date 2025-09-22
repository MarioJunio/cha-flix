package com.jetbrains.kmpapp

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.jetbrains.kmpapp.screens.shows.ShowsScreen

@Composable
fun App() {
    // Koin should be initialized in platform-specific code (ShowsApp.kt for Android, iOS app init for iOS)
    // Not here to avoid duplicate initialization

    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()
    ) {
        Surface {
            Navigator(ShowsScreen)
        }
    }
}
