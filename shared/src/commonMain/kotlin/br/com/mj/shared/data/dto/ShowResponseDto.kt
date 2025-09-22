package br.com.mj.shared.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ShowResponseDto(
    val show: ShowDto
)

@Serializable
data class ShowDto(
    val id: Int,
    val name: String,
    val premiered: String? = null,
    val image: ImageDto? = null,
    val genres: List<String> = emptyList(),
    val summary: String? = null
)