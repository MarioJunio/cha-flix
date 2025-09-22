package br.com.mj.shared.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ImageDto(
    val medium: String,
    val original: String
)
