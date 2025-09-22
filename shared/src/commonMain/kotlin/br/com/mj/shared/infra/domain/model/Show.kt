package br.com.mj.shared.infra.domain.model

import br.com.mj.shared.data.dto.ImageDto
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class Show(
    val id: Int,
    val name: String,
    val premiered: LocalDate? = null,
    val image: ImageDto? = null,
    val genres: List<String> = emptyList(),
    val summary: String? = null
)