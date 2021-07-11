package com.temsipatrin.sampleforinterview.domain.usecases

import com.temsipatrin.sampleforinterview.domain.models.Character
import com.temsipatrin.sampleforinterview.domain.repo.CharacterRepository
import kotlinx.coroutines.flow.Flow

interface GetCharactersShortUseCase {
    suspend fun execute(page: Int): Flow<List<Character>>
}

class GetCharactersShortUseCaseImpl(private val repo: CharacterRepository) :
    GetCharactersShortUseCase {
    override suspend fun execute(page: Int): Flow<List<Character>> {
        return repo.getCharacterListByPage(page)
    }

}