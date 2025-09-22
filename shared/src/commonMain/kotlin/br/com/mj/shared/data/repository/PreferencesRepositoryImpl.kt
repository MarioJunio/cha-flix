package br.com.mj.shared.data.repository

import br.com.mj.shared.infra.domain.repository.PreferencesRepository
import br.com.mj.shared.infra.settings.Settings

class PreferencesRepositoryImpl(private val settings: Settings) : PreferencesRepository {

    // Legacy methods for backward compatibility
    override fun markEpisodeWatched(episodeId: Int, watched: Boolean) =
        settings.putBoolean(episodeId.toString(), watched)

    override fun isEpisodeWatched(episodeId: Int): Boolean =
        settings.getBoolean(episodeId.toString(), false)
}