package com.temsipatrin.sampleforinterview.domain.repo

import com.temsipatrin.sampleforinterview.domain.models.Character
import com.temsipatrin.sampleforinterview.domain.models.Info
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    suspend fun getCharacterListByPage(page: Int): Flow<List<Character>>

    suspend fun getPagesInfo(page: Int): Flow<Info>

    suspend fun getCharacter(id: Int): Flow<Character>

}