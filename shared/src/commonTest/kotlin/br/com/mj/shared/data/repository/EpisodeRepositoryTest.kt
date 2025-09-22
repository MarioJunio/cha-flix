package br.com.mj.shared.data.repository

import br.com.mj.shared.data.mapper.EpisodeMapper
import br.com.mj.shared.mocks.PreferenceRepositoryImplMock
import br.com.mj.shared.mocks.TvMazeApiImplMock
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class EpisodeRepositoryTest {

    private val mockApi = TvMazeApiImplMock()
    private val episodeMapper = EpisodeMapper()
    private val preferencesRepository = PreferenceRepositoryImplMock()

    private val episodeRepository =
        EpisodeRepositoryImpl(mockApi, episodeMapper, preferencesRepository)

    @Test
    fun testSearchSeasonsFromShow() = runTest {
        // Act
        val result = episodeRepository.getSeasons(2)

        // Assert
        assertEquals(1, result.size)
        assertEquals(1, mockApi.getEpisodesCallCount)
    }

    @Test
    fun testSearchSeasonsReturningEmpty() = runTest {
        // Act
        val result = episodeRepository.getSeasons(1)

        // Assert
        assertEquals(0, result.size)
        assertEquals(1, mockApi.getEpisodesCallCount)
    }
}