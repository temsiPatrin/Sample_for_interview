package com.temsipatrin.sampleforinterview.remote.models

data class InfoResponse(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: Any
)