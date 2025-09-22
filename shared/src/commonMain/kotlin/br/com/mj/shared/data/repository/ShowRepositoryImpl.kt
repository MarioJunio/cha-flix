package br.com.mj.shared.data.repository

import br.com.mj.shared.data.mapper.ShowMapper
import br.com.mj.shared.data.remote.TvMazeApi
import br.com.mj.shared.infra.domain.model.Show
import br.com.mj.shared.infra.domain.repository.ShowRepository

class ShowRepositoryImpl(
    private val api: TvMazeApi,
    private val mapper: ShowMapper,
) : ShowRepository {
    override suspend fun getShows(query: String): List<Show> {
        val dto = api.getShows(query)

        return dto.map {
            mapper.mapToDomain(it.show)
        }
    }

}