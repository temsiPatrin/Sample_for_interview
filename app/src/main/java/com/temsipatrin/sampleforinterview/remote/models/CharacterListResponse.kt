package com.temsipatrin.sampleforinterview.remote.models

data class CharacterListResponse(
    val info: InfoResponse,
    val results: List<CharacterResponse>
)
