package com.temsipatrin.sampleforinterview.remote.repo

import com.temsipatrin.sampleforinterview.domain.models.Character
import com.temsipatrin.sampleforinterview.domain.models.Info
import com.temsipatrin.sampleforinterview.domain.repo.SearchCharacterRepository
import com.temsipatrin.sampleforinterview.remote.api.RickAndMortyApiService
import com.temsipatrin.sampleforinterview.remote.mappers.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SearchCharacterRepositoryImpl(private val api: RickAndMortyApiService) :
    SearchCharacterRepository {

    override suspend fun getCharacterListByPage(page: Int, name: String): Flow<List<Character>> =
        flow {
            val listCharacter = api.getCharactersByName(page, name).results
                .map { it.toDomain() }
            emit(listCharacter)
        }.flowOn(Dispatchers.IO)

    override suspend fun getPagesInfo(page: Int, name: String): Flow<Info> =
        flow {
            val pagesInfo = api.getCharactersByName(page, name).info.toDomain()
            emit(pagesInfo)
        }.flowOn(Dispatchers.IO)
}