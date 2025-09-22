package br.com.mj.shared.mocks

import br.com.mj.shared.data.dto.ImageDto
import br.com.mj.shared.infra.domain.model.Show
import br.com.mj.shared.infra.domain.repository.ShowRepository
import kotlinx.datetime.LocalDate

class ShowRepositoryImplMock : ShowRepository {

    override suspend fun getShows(query: String): List<Show> {
        if (query == "batman") return emptyList()

        return listOf(
            Show(
                id = 1,
                name = "Superman",
                premiered = LocalDate.parse("2016-01-01"),
                image = ImageDto(
                    medium = "http://example.com/superman.jpg",
                    original = "http://example.com/superman.jpg"

                ),
                genres = listOf("Action", "Adventure"),
                summary = "A superhero who fights crime."
            )
        )
    }
}