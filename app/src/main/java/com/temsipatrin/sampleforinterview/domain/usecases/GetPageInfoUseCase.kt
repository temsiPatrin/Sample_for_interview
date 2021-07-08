package com.temsipatrin.sampleforinterview.domain.usecases

import com.temsipatrin.sampleforinterview.domain.repo.CharacterRepository
import com.temsipatrin.sampleforinterview.ui.mappers.toPresentation
import com.temsipatrin.sampleforinterview.ui.models.PageInfoUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface GetPageInfoUseCase {
    suspend fun execute(): Flow<PageInfoUi>
}

class GetPageInfoUseCaseImpl(private val repo: CharacterRepository): GetPageInfoUseCase{
    override suspend fun execute(): Flow<PageInfoUi> {
        return repo.getPagesInfo(FIRST_PAGE).map { it.toPresentation()}
    }

    companion object{
        const val FIRST_PAGE = 1
    }
}