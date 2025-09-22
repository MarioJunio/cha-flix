package br.com.mj.shared.infra.domain.repository

import br.com.mj.shared.infra.domain.model.Season

interface EpisodeRepository {
    suspend fun getSeasons(showId: Int): List<Season>
}