package com.temsipatrin.sampleforinterview.di

import com.temsipatrin.sampleforinterview.domain.repo.CharacterRepository
import com.temsipatrin.sampleforinterview.domain.usecases.GetCharactersShortUseCase
import com.temsipatrin.sampleforinterview.domain.usecases.GetCharactersShortUseCaseImpl
import com.temsipatrin.sampleforinterview.domain.usecases.GetPageInfoUseCase
import com.temsipatrin.sampleforinterview.domain.usecases.GetPageInfoUseCaseImpl
import org.koin.dsl.module

val useCasesModule = module {
    fun provideGetCharactersShortUseCase(repo: CharacterRepository): GetCharactersShortUseCase {
        return GetCharactersShortUseCaseImpl(repo)
    }
    fun provideGetPageInfoUseCase(repo: CharacterRepository): GetPageInfoUseCase {
        return GetPageInfoUseCaseImpl(repo)
    }

    factory { provideGetCharactersShortUseCase(get()) }
    factory { provideGetPageInfoUseCase(get()) }
}

