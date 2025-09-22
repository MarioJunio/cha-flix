package br.com.mj.shared.data.remote

import br.com.mj.shared.data.dto.EpisodeDto
import br.com.mj.shared.data.dto.ShowResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

interface TvMazeApi {
    suspend fun getShows(query: String): List<ShowResponseDto>
    suspend fun getEpisodes(showId: Int): List<EpisodeDto>
}

class TvMazeApiImpl(
    private val client: HttpClient
) : TvMazeApi {
    override suspend fun getShows(query: String): List<ShowResponseDto> {
        return client.get("/search/shows") {
            url {
                parameters.append("q", query)
            }
        }.body()
    }

    override suspend fun getEpisodes(showId: Int): List<EpisodeDto> {
        return client.get("/shows/$showId/episodes").body()
    }
}