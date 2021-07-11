package com.temsipatrin.sampleforinterview.ui.models

import com.temsipatrin.sampleforinterview.R

data class CharacterShortUi(
    val id: Int,
    val image: String,
    val name: String,
    val status: String,
    val species: String,
    val gender: String
) {
    val statusColor
        get() = when (status) {
            "Alive" -> R.color.green
            "Dead" -> R.color.red
            else -> R.color.gray
        }
}

