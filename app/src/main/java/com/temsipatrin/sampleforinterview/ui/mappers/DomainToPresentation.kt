package com.temsipatrin.sampleforinterview.ui.mappers

import com.temsipatrin.sampleforinterview.domain.models.Character
import com.temsipatrin.sampleforinterview.domain.models.Info
import com.temsipatrin.sampleforinterview.ui.models.CharacterShortUi
import com.temsipatrin.sampleforinterview.ui.models.CharacterUi
import com.temsipatrin.sampleforinterview.ui.models.PageInfoUi


internal fun Character.toPresentation(): CharacterUi {
    return CharacterUi(
        gender = gender,
        id = id,
        image = image,
        location = location.name,
        name = name,
        origin = origin.name,
        species = "$species, $type",
        status = status,
    )
}
internal fun Character.toPresentationShort(): CharacterShortUi {
    return CharacterShortUi(
        gender = gender,
        id = id,
        image = image,
        name = name,
        species = species,
        status = status
    )
}
internal fun Info.toPresentation(): PageInfoUi{
    return PageInfoUi(count, pages)
}
