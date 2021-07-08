package com.temsipatrin.sampleforinterview.remote.mappers

import com.temsipatrin.sampleforinterview.domain.models.Location
import com.temsipatrin.sampleforinterview.domain.models.Origin
import com.temsipatrin.sampleforinterview.remote.models.CharacterResponse
import com.temsipatrin.sampleforinterview.domain.models.Character
import com.temsipatrin.sampleforinterview.domain.models.Info
import com.temsipatrin.sampleforinterview.remote.models.InfoResponse
import com.temsipatrin.sampleforinterview.remote.models.LocationResponse
import com.temsipatrin.sampleforinterview.remote.models.OriginResponse


internal fun CharacterResponse.toDomain(): Character {
    return Character(
        created = created,
        episode = episode,
        gender = gender,
        id = id,
        image = image,
        location = location.toDomain(),
        name = name,
        origin = origin.toDomain(),
        species = species,
        status = status,
        type = type,
        url = url
    )
}

internal fun LocationResponse.toDomain(): Location {
    return Location(name, url)
}

internal fun OriginResponse.toDomain(): Origin {
    return Origin(name, url)
}

internal fun InfoResponse.toDomain(): Info{
    return Info(count, pages)
}