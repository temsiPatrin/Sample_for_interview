package com.temsipatrin.sampleforinterview.domain.usecases

import com.temsipatrin.sampleforinterview.domain.models.Character
import com.temsipatrin.sampleforinterview.domain.repo.CharacterRepository
import kotlinx.coroutines.flow.Flow

interface GetCharactersUseCase {
    suspend fun execute(id: Int): Flow<Character>
}

class GetCharactersUseCaseImpl(private val repo: CharacterRepository) : GetCharactersUseCase {
    override suspend fun execute(id: Int): Flow<Character> {
        return repo.getCharacter(id)
    }

}