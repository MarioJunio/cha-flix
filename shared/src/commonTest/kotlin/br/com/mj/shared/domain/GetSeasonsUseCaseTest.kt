package br.com.mj.shared.domain

import br.com.mj.shared.mocks.ShowRepositoryImplMock
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GetSeasonsUseCaseTest {

    private val showRepositoryMock = ShowRepositoryImplMock()

    @Test
    fun testSearchSeasonsFromShow() = runTest {
        // Act
        val result = showRepositoryMock.getShows("superman")

        // Assert
        assertEquals("Superman", result.first().name)
        assertEquals(1, result.size)
    }

    @Test
    fun testSearchSeasonsReturningEmpty() = runTest {
        // Act
        val result = showRepositoryMock.getShows("batman")

        // Assert
        assertEquals(0, result.size)
    }
}