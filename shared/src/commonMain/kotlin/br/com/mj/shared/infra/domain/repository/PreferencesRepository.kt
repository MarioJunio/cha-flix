package br.com.mj.shared.infra.domain.repository

interface PreferencesRepository {
    fun markEpisodeWatched(episodeId: Int, watched: Boolean)
    fun isEpisodeWatched(episodeId: Int): Boolean
}