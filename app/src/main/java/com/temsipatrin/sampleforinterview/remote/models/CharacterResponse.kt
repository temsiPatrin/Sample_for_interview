package com.temsipatrin.sampleforinterview.remote.models

data class CharacterResponse(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: LocationResponse,
    val name: String,
    val origin: OriginResponse,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)

data class LocationResponse(
    val name: String,
    val url: String
)

data class OriginResponse(
    val name: String,
    val url: String
)