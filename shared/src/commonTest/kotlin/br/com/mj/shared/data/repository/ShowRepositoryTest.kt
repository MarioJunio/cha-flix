package br.com.mj.shared.data.repository

import br.com.mj.shared.data.mapper.ShowMapper
import br.com.mj.shared.mocks.TvMazeApiImplMock
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ShowRepositoryTest {

    private val mockApi = TvMazeApiImplMock()

    private val mapper = ShowMapper()
    private val repository = ShowRepositoryImpl(mockApi, mapper)

    @Test
    fun testSearchShows() = runTest {
        // Act
        val result = repository.getShows("superman")

        // Assert
        assertEquals("Superman", result.first().name)
        assertEquals(1, mockApi.getShowsCallCount)
        assertEquals("superman", mockApi.lastQuery)
    }

    @Test
    fun testSearchShowReturnEmpty() = runTest {
        // Act
        val result = repository.getShows("batman")

        // Assert
        assertEquals(0, result.size)
        assertEquals(1, mockApi.getShowsCallCount)
        assertEquals("batman", mockApi.lastQuery)
    }
}