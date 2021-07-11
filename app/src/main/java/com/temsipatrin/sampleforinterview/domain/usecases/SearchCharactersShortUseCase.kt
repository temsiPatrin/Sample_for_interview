package com.temsipatrin.sampleforinterview.domain.usecases

import com.temsipatrin.sampleforinterview.domain.repo.SearchCharacterRepository
import com.temsipatrin.sampleforinterview.ui.mappers.toPresentationShort
import com.temsipatrin.sampleforinterview.ui.models.CharacterShortUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface SearchCharactersShortUseCase {
    suspend fun execute(page: Int, name: String): Flow<List<CharacterShortUi>>
}

class SearchCharactersShortUseCaseImpl(private val repo: SearchCharacterRepository) :
    SearchCharactersShortUseCase {
    override suspend fun execute(page: Int, name: String): Flow<List<CharacterShortUi>> {
        return repo.getCharacterListByPage(page, name)
            .map { listDomain -> listDomain.map { it.toPresentationShort() } }
    }

}