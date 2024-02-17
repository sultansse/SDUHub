package com.softwareit.sduhub.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.softwareit.sduhub.presentation.screens.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module


val navigationModule = module {

    single<Cicerone<Router>> { Cicerone.create() }

    single { get<Cicerone<Router>>().router }

    single { get<Cicerone<Router>>().getNavigatorHolder() }
}

val viewModelModule = module {
    viewModel {
        HomeScreenViewModel(
            router = get(),
        )
    }
}



val appModule = listOf<Module>(
    navigationModule,
//    otherClassesModule,
//    mapperModule,
//    databaseModule,
//    dataSourceModule,
//    repositoryModule,
//    useCaseModule,
    viewModelModule
)