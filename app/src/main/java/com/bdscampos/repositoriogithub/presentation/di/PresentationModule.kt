package com.bdscampos.repositoriogithub.presentation.di

import androidx.lifecycle.ViewModel
import com.bdscampos.repositoriogithub.domain.ListUserRepositoriesUseCase
import com.bdscampos.repositoriogithub.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object PresentationModule {

    fun load() {
        loadKoinModules(viewModelModule())
    }

    private fun viewModelModule(): Module {
        return module {
            viewModel { MainViewModel(get()) }
        }
    }
}