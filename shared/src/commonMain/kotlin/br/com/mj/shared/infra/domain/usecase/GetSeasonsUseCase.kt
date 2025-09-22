package br.com.mj.shared.infra.domain.usecase

import br.com.mj.shared.infra.domain.model.Season
import br.com.mj.shared.infra.domain.repository.EpisodeRepository

interface GetSeasonsUseCase {
    suspend operator fun invoke(showId: Int): List<Season>
}

class GetSeasonsUseCaseImpl(
    private val episodeRepository: EpisodeRepository,
) : GetSeasonsUseCase {

    override suspend operator fun invoke(showId: Int): List<Season> =
        episodeRepository.getSeasons(showId)
}