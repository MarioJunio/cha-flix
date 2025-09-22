package br.com.mj.shared.mocks

import br.com.mj.shared.infra.domain.repository.PreferencesRepository

class PreferenceRepositoryImplMock : PreferencesRepository {

    override fun markEpisodeWatched(episodeId: Int, watched: Boolean) {
        // mock implementation
    }

    override fun isEpisodeWatched(episodeId: Int): Boolean {
        return true
    }
}