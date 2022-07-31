package com.bdscampos.repositoriogithub

import android.app.Application
import com.bdscampos.repositoriogithub.data.di.DataModule
import com.bdscampos.repositoriogithub.domain.di.DomainModule
import com.bdscampos.repositoriogithub.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
        }

        DataModule.load()
        DomainModule.load()
        PresentationModule.load()
    }
}