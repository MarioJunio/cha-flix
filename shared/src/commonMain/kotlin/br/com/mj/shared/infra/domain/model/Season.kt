package br.com.mj.shared.infra.domain.model

data class Season(
    val season: Int,
    val episodes: List<Episode>
)