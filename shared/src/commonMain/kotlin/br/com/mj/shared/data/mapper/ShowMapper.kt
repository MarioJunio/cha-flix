package br.com.mj.shared.data.mapper

import br.com.mj.shared.data.constants.patternHtmlTags
import br.com.mj.shared.data.dto.ShowDto
import br.com.mj.shared.infra.domain.model.Show
import kotlinx.datetime.LocalDate

class ShowMapper {

    fun mapToDomain(showDto: ShowDto): Show = with(showDto) {
        Show(
            id = id,
            name = name,
            premiered = if (premiered.isNullOrEmpty()) null else LocalDate.parse(premiered),
            image = image,
            genres = genres,
            summary = summary?.replace(patternHtmlTags, "")
        )
    }
}