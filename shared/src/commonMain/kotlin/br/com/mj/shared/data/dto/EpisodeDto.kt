package br.com.mj.shared.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class EpisodeDto(
    val id: Int,
    val season: Int,
    val image: ImageDto,
    val number: Int,
    val name: String,
    val runtime: Int,
    val airdate: String,
    val summary: String
)
