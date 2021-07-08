package com.temsipatrin.sampleforinterview.domain.usecases

import com.temsipatrin.sampleforinterview.domain.repo.CharacterRepository
import com.temsipatrin.sampleforinterview.ui.mappers.toPresentationShort
import com.temsipatrin.sampleforinterview.ui.models.CharacterShortUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface GetCharactersShortUseCase {
    suspend fun execute(page: Int): Flow<List<CharacterShortUi>>
}

class GetCharactersShortUseCaseImpl(private val repo: CharacterRepository): GetCharactersShortUseCase{
    override suspend fun execute(page: Int): Flow<List<CharacterShortUi>> {
        return repo.getCharacterListByPage(page).map { listDomain -> listDomain.map { it.toPresentationShort() } }
    }

}