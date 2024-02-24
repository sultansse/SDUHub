package com.softwareit.sduhub.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.google.firebase.database.FirebaseDatabase
import com.softwareit.sduhub.data.network.firebase.ImportantInfoDao
import com.softwareit.sduhub.data.repository.NetworkRepository
import com.softwareit.sduhub.data.repository.NetworkRepositoryImpl
import com.softwareit.sduhub.data.repository.NotesRepository
import com.softwareit.sduhub.data.repository.NotesRepositoryImpl
import com.softwareit.sduhub.domain.DeleteNoteUseCase
import com.softwareit.sduhub.domain.DeleteNotesUseCase
import com.softwareit.sduhub.domain.GetImportantInfoUseCase
import com.softwareit.sduhub.domain.GetNoteUseCase
import com.softwareit.sduhub.domain.GetNotesUseCase
import com.softwareit.sduhub.domain.UpsertNoteUseCase
import com.softwareit.sduhub.ui.screens.home_screen.HomeScreenViewModel
import com.softwareit.sduhub.ui.screens.home_screen.edit_note_screen.EditNoteViewModel
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
    single { provideNoteDao(appDatabase = get()) }
}

val networkModule = module {
    single { FirebaseDatabase.getInstance() }
    single { ImportantInfoDao(database = get()) }
}

val repositoryModule = module {
    single<NotesRepository> { NotesRepositoryImpl(localDataSource = get()) }
    single<NetworkRepository> { NetworkRepositoryImpl(networkDataSource = get()) }
}

val useCaseModule = module {
    factory { GetImportantInfoUseCase(repository = get()) }

    factory { GetNotesUseCase(repository = get()) }
    factory { GetNoteUseCase(repository = get()) }
    factory { UpsertNoteUseCase(repository = get()) }
    factory { DeleteNoteUseCase(repository = get()) }
    factory { DeleteNotesUseCase(repository = get()) }

}

val viewModelModule = module {
    viewModel {
        HomeScreenViewModel(
            router = get(),
            getNotes = get(),
            upsertNote = get(),
            deleteNotes = get(),
            getImportantInfo = get(),
        )
    }
    viewModel {
        EditNoteViewModel(
            router = get(),
            getNote = get(),
            upsertNote = get(),
            deleteNote = get(),
        )
    }
}

val appModule = listOf(
    navigationModule,
    databaseModule,
    networkModule,
    repositoryModule,
    useCaseModule,
    viewModelModule
)