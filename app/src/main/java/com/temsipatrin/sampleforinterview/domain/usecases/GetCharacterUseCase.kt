package com.temsipatrin.sampleforinterview.domain.usecases

import com.temsipatrin.sampleforinterview.domain.repo.CharacterRepository
import com.temsipatrin.sampleforinterview.ui.mappers.toPresentation
import com.temsipatrin.sampleforinterview.ui.models.CharacterUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface GetCharactersUseCase {
    suspend fun execute(id: Int): Flow<CharacterUi>
}

class GetCharactersUseCaseImpl(private val repo: CharacterRepository) : GetCharactersUseCase {
    override suspend fun execute(id: Int): Flow<CharacterUi> {
        return repo.getCharacter(id).map { it.toPresentation() }
    }

}