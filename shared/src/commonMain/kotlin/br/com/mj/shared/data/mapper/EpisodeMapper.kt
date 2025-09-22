package br.com.mj.shared.data.mapper

import br.com.mj.shared.data.constants.patternHtmlTags
import br.com.mj.shared.data.dto.EpisodeDto
import br.com.mj.shared.infra.domain.model.Episode
import kotlinx.datetime.LocalDate

class EpisodeMapper {

    fun mapToDomain(episodeDto: EpisodeDto, isWatched: Boolean): Episode {
        return with(episodeDto) {
            Episode(
                id = id,
                season = season,
                image = image,
                number = number,
                name = name,
                runtime = runtime,
                airdate = LocalDate.parse(airdate),
                summary = summary.replace(patternHtmlTags, ""),
                isWatched = isWatched
            )
        }
    }
}