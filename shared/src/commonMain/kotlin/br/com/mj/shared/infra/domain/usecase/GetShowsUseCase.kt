package br.com.mj.shared.infra.domain.usecase

import br.com.mj.shared.infra.domain.model.Show
import br.com.mj.shared.infra.domain.repository.ShowRepository

interface GetShowsUseCase {
    suspend operator fun invoke(query: String): List<Show>
}

class GetShowsUseCaseImpl(
    private val showRepository: ShowRepository
) : GetShowsUseCase {
    override suspend operator fun invoke(query: String) = showRepository.getShows(query)
}