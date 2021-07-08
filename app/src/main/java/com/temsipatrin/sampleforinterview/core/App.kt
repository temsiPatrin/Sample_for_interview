package com.temsipatrin.sampleforinterview.core

import android.app.Application
import com.temsipatrin.sampleforinterview.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            koin.loadModules(listOf(
                apiModule,
                remoteDataModule,
                useCasesModule,
                viewModelModule
            ))
            koin.createRootScope()
        }
    }
}