package com.temsipatrin.sampleforinterview.di

import com.temsipatrin.sampleforinterview.domain.repo.CharacterRepository
import com.temsipatrin.sampleforinterview.domain.usecases.*
import org.koin.dsl.module

val useCasesModule = module {

    fun provideGetCharactersShortUseCase(repo: CharacterRepository): GetCharactersShortUseCase {
        return GetCharactersShortUseCaseImpl(repo)
    }

    fun provideGetPageInfoUseCase(repo: CharacterRepository): GetPageInfoUseCase {
        return GetPageInfoUseCaseImpl(repo)
    }

    fun provideGetCharactersInfoUseCase(repo: CharacterRepository): GetCharactersUseCase {
        return GetCharactersUseCaseImpl(repo)
    }

    factory { provideGetCharactersShortUseCase(get()) }
    factory { provideGetPageInfoUseCase(get()) }
    factory { provideGetCharactersInfoUseCase(get()) }
}

