package br.com.mj.shared.infra.domain.usecase

import br.com.mj.shared.infra.domain.repository.PreferencesRepository

interface MarkEpisodeWatchedUseCase {
    operator fun invoke(episodeID: Int, watched: Boolean)
}

class MarkEpisodeWatchedUseCaseImpl(
    private val preferencesRepository: PreferencesRepository
) : MarkEpisodeWatchedUseCase {
    override operator fun invoke(episodeID: Int, watched: Boolean) =
        preferencesRepository.markEpisodeWatched(episodeID, watched)
}