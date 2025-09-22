package br.com.mj.shared.infra.domain.model

import br.com.mj.shared.data.dto.ImageDto
import kotlinx.datetime.LocalDate

data class Episode(
    val id: Int,
    val season: Int,
    val image: ImageDto,
    val number: Int,
    val name: String,
    val runtime: Int,
    val airdate: LocalDate,
    val summary: String,
    val isWatched: Boolean = false
)
