package com.jetbrains.kmpapp.screens.shows

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.mj.shared.infra.domain.model.Show
import br.com.mj.shared.infra.domain.usecase.GetShowsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for the shows screen
 */
class ListViewModel(private val getShowsUseCase: GetShowsUseCase) : ViewModel() {

    // mutable state flow for the shows
    private val _shows = MutableStateFlow(UiShowsState(isLoading = false, data = null))

    // expose the shows state as a read-only state flow
    val shows = _shows.asStateFlow()

    // search and submitted text states
    val searchText = mutableStateOf("")
    val submittedText = mutableStateOf("")

    // search for shows if text is not empty
    fun search() {
        if (submittedText.value.isEmpty()) return

        _shows.value = UiShowsState(isLoading = true, data = null)

        viewModelScope.launch {
            try {
                val result = getShowsUseCase(submittedText.value)

                _shows.value = UiShowsState(isLoading = false, data = result)
            } catch (_: Exception) {
                _shows.value = UiShowsState(
                    isLoading = false, data = null, error = "Something went wrong"
                )
            }
        }
    }
}

/**
 * Represents the state of the UI
 */
data class UiShowsState(
    val error: String? = null, val isLoading: Boolean, val data: List<Show>?
)
