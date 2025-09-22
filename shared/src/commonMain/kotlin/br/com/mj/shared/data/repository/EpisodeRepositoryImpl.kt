package br.com.mj.shared.data.repository

import br.com.mj.shared.data.mapper.EpisodeMapper
import br.com.mj.shared.data.remote.TvMazeApi
import br.com.mj.shared.infra.domain.model.Season
import br.com.mj.shared.infra.domain.repository.EpisodeRepository
import br.com.mj.shared.infra.domain.repository.PreferencesRepository

class EpisodeRepositoryImpl(
    private val api: TvMazeApi,
    private val mapper: EpisodeMapper,
    private val preferencesRepository: PreferencesRepository
) : EpisodeRepository {

    override suspend fun getSeasons(showId: Int): List<Season> {
        val dto = api.getEpisodes(showId)

        return dto
            .groupBy { it.season }
            .map {
                Season(
                    season = it.key,
                    episodes = it.value.map { episode ->
                        val isWatched = preferencesRepository.isEpisodeWatched(episode.id)

                        mapper.mapToDomain(
                            episode,
                            isWatched
                        )
                    }
                )
            }
    }
}