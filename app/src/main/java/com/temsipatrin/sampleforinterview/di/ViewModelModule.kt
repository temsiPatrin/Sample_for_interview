package com.temsipatrin.sampleforinterview.di

import com.temsipatrin.sampleforinterview.viewmodels.CharacterInfoViewModel
import com.temsipatrin.sampleforinterview.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get(),get()) }
    viewModel { (id: Int) -> CharacterInfoViewModel(id,get()) }
}