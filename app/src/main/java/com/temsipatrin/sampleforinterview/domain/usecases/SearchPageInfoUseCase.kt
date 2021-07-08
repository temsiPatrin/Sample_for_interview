package com.temsipatrin.sampleforinterview.domain.usecases

import com.temsipatrin.sampleforinterview.domain.repo.SearchCharacterRepository
import com.temsipatrin.sampleforinterview.ui.mappers.toPresentation
import com.temsipatrin.sampleforinterview.ui.models.PageInfoUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface SearchPageInfoUseCase{
    suspend fun execute(name: String): Flow<PageInfoUi>
}

class SearchPageInfoUseCaseImpl(private val repo: SearchCharacterRepository): SearchPageInfoUseCase{
    override suspend fun execute(name: String): Flow<PageInfoUi> {
        return repo.getPagesInfo(FIRST_PAGE, name).map { it.toPresentation()}
    }

    companion object{
        const val FIRST_PAGE = 1
    }
}