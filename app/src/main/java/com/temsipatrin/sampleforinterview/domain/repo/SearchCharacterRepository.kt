package com.temsipatrin.sampleforinterview.domain.repo

import com.temsipatrin.sampleforinterview.domain.models.Character
import com.temsipatrin.sampleforinterview.domain.models.Info
import kotlinx.coroutines.flow.Flow

interface SearchCharacterRepository {

    suspend fun getCharacterListByPage(page: Int, name: String): Flow<List<Character>>

    suspend fun getPagesInfo(page: Int, name: String): Flow<Info>
}