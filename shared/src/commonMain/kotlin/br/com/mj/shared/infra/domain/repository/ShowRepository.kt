package br.com.mj.shared.infra.domain.repository

import br.com.mj.shared.infra.domain.model.Show

interface ShowRepository {
    suspend fun getShows(query: String): List<Show>
}