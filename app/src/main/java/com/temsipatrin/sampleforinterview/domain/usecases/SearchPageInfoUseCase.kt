package com.temsipatrin.sampleforinterview.domain.usecases

import com.temsipatrin.sampleforinterview.domain.models.Info
import com.temsipatrin.sampleforinterview.domain.repo.SearchCharacterRepository
import kotlinx.coroutines.flow.Flow

interface SearchPageInfoUseCase {
    suspend fun execute(name: String): Flow<Info>
}

class SearchPageInfoUseCaseImpl(private val repo: SearchCharacterRepository) :
    SearchPageInfoUseCase {
    override suspend fun execute(name: String): Flow<Info> {
        return repo.getPagesInfo(FIRST_PAGE, name)
    }

    companion object {
        private const val FIRST_PAGE = 1
    }
}