package com.temsipatrin.sampleforinterview.remote.repo

import com.temsipatrin.sampleforinterview.domain.models.Character
import com.temsipatrin.sampleforinterview.domain.models.Info
import com.temsipatrin.sampleforinterview.domain.repo.CharacterRepository
import com.temsipatrin.sampleforinterview.remote.api.RickAndMortyApiService
import com.temsipatrin.sampleforinterview.remote.mappers.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CharacterRepositoryImpl(private val api: RickAndMortyApiService) : CharacterRepository {

    override suspend fun getCharacterListByPage(page: Int): Flow<List<Character>> =
        flow {
            val listCharacter = api.getListCharacters(page).results
                .map { it.toDomain() }
            emit(listCharacter)
        }.flowOn(Dispatchers.IO)

    override suspend fun getPagesInfo(page: Int): Flow<Info> =
        flow {
            val pagesInfo = api.getListCharacters(page).info.toDomain()
            emit(pagesInfo)
        }.flowOn(Dispatchers.IO)

    override suspend fun getCharacter(id: Int): Flow<Character> =
        flow {
            val character = api.getCharacterById(id).toDomain()
            emit(character)
        }.flowOn(Dispatchers.IO)
}