package com.temsipatrin.sampleforinterview.domain.usecases

import com.temsipatrin.sampleforinterview.domain.models.Info
import com.temsipatrin.sampleforinterview.domain.repo.CharacterRepository
import kotlinx.coroutines.flow.Flow

interface GetPageInfoUseCase {
    suspend fun execute(): Flow<Info>
}

class GetPageInfoUseCaseImpl(private val repo: CharacterRepository) : GetPageInfoUseCase {
    override suspend fun execute(): Flow<Info> {
        return repo.getPagesInfo(FIRST_PAGE)
    }

    companion object {
        private const val FIRST_PAGE = 1
    }
}