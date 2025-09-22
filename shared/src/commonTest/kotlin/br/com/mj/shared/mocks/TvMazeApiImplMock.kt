package br.com.mj.shared.mocks

import br.com.mj.shared.data.dto.EpisodeDto
import br.com.mj.shared.data.dto.ImageDto
import br.com.mj.shared.data.dto.ShowDto
import br.com.mj.shared.data.dto.ShowResponseDto
import br.com.mj.shared.data.remote.TvMazeApi

class TvMazeApiImplMock : TvMazeApi {
    var getShowsCallCount = 0
    var getEpisodesCallCount = 0

    var lastQuery: String? = null
    var showIdSearch: Int? = null

    override suspend fun getShows(query: String): List<ShowResponseDto> {
        getShowsCallCount++
        lastQuery = query

        if (query == "batman") return emptyList()

        return listOf(
            ShowResponseDto(
                show = ShowDto(
                    id = 1,
                    name = "Superman",
                    premiered = "2016-01-01",
                    image = ImageDto(
                        "https://example.com/superman.jpg", "https://example.com/superman.jpg"
                    ),
                    genres = listOf("Action", "Adventure"),
                    summary = "A superhero who fights crime."
                ),
            )
        )
    }

    override suspend fun getEpisodes(showId: Int): List<EpisodeDto> {
        getEpisodesCallCount++
        showIdSearch = showId

        if (showId == 1) return emptyList()

        return listOf(
            EpisodeDto(
                id = 1,
                name = "Episode 1",
                season = 1,
                number = 1,
                summary = "Episode 1 summary",
                runtime = 15,
                airdate = "2023-01-01",
                image = ImageDto(
                    medium = "https://example.com/episode.jpg",
                    original = "https://example.com/episode.jpg"
                )
            )
        )
    }
}