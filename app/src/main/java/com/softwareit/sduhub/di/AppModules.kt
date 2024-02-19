package com.softwareit.sduhub.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.softwareit.sduhub.data.repository.NotesRepository
import com.softwareit.sduhub.data.repository.NotesRepositoryImpl
import com.softwareit.sduhub.domain.AddNoteUseCase
import com.softwareit.sduhub.domain.GetNotesUseCase
import com.softwareit.sduhub.ui.screens.home_screen.HomeScreenViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val navigationModule = module {

    single<Cicerone<Router>> { Cicerone.create() }

    single { get<Cicerone<Router>>().router }

    single { get<Cicerone<Router>>().getNavigatorHolder() }
}

val databaseModule = module {
    single { provideAppDatabase(context = androidContext()) }
    single { provideCarDAO(appDatabase = get()) }
}

val repositoryModule = module {
    single<NotesRepository> { NotesRepositoryImpl(localDataSource = get()) }
}

val useCaseModule = module {
    factory { GetNotesUseCase(repository = get()) }
    factory { AddNoteUseCase(repository = get()) }
}

val viewModelModule = module {
    viewModel {
        HomeScreenViewModel(
            router = get(),
            getNotes = get(),
            addNote = get(),
        )
    }
}

val appModule = listOf(
    navigationModule,
    databaseModule,
    repositoryModule,
    useCaseModule,
    viewModelModule
)