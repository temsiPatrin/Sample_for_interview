package com.temsipatrin.sampleforinterview.domain.usecases

import com.temsipatrin.sampleforinterview.domain.models.Character
import com.temsipatrin.sampleforinterview.domain.repo.SearchCharacterRepository
import kotlinx.coroutines.flow.Flow

interface SearchCharactersShortUseCase {
    suspend fun execute(page: Int, name: String): Flow<List<Character>>
}

class SearchCharactersShortUseCaseImpl(private val repo: SearchCharacterRepository) :
    SearchCharactersShortUseCase {
    override suspend fun execute(page: Int, name: String): Flow<List<Character>> {
        return repo.getCharacterListByPage(page, name)
    }

}