package com.temsipatrin.sampleforinterview.di

import com.temsipatrin.sampleforinterview.domain.repo.CharacterRepository
import com.temsipatrin.sampleforinterview.domain.repo.SearchCharacterRepository
import com.temsipatrin.sampleforinterview.remote.repo.CharacterRepositoryImpl
import com.temsipatrin.sampleforinterview.remote.repo.SearchCharacterRepositoryImpl
import org.koin.dsl.module

val remoteDataModule = module {
    factory<CharacterRepository> { CharacterRepositoryImpl(api = get()) }
    factory<SearchCharacterRepository> { SearchCharacterRepositoryImpl(api = get()) }
}