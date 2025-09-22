package com.jetbrains.kmpapp.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.mj.shared.infra.domain.model.Season
import br.com.mj.shared.infra.domain.usecase.GetSeasonsUseCase
import br.com.mj.shared.infra.domain.usecase.MarkEpisodeWatchedUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for the detail screen
 */
class DetailViewModel(
    private val getSeasonsUseCase: GetSeasonsUseCase,
    private val markEpisodeWatchedUseCase: MarkEpisodeWatchedUseCase
) : ViewModel() {

    // mutable state flow for the seasons
    private val _uiState = MutableStateFlow<UiState?>(UiState())

    // expose the seasons state as a read-only state flow
    val uiState = _uiState.asStateFlow()

    // get seasons for the episode
    fun getSeasons(episodeId: Int) {
        _uiState.value = UiState(isLoading = true)

        viewModelScope.launch {
            try {
                _uiState.value = UiState(
                    isLoading = false,
                    seasons = getSeasonsUseCase(episodeId)
                )
            } catch (ex: Exception) {
                _uiState.value = UiState(isLoading = false, error = ex.message)
            }
        }
    }

    // mark an episode as watched
    fun markAsWatched(episodeId: Int, watched: Boolean) {
        viewModelScope.launch {
            markEpisodeWatchedUseCase(episodeId, watched)
        }
    }
}

/**
 * Represents the state of the UI
 */
data class UiState(
    val seasons: List<Season>? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)