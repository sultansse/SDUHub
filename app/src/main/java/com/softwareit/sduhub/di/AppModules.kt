package com.softwareit.sduhub.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.google.firebase.database.FirebaseDatabase
import com.softwareit.sduhub.data.network.backend.BackendDataSource
import com.softwareit.sduhub.data.network.firebase.FirebaseDataSource
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
import com.softwareit.sduhub.ui.screens.news_screen.NewsScreenViewModel
import com.softwareit.sduhub.ui.screens.profile_screen.ProfileViewModel
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
// TODO make better - not so tight relationship
    single { FirebaseDatabase.getInstance() }
    single { FirebaseDataSource(database = get()) }

    single { provideHttpClient() }
    single { provideRetrofit(get()) }
    single { provideService(get()) }
}


val repositoryModule = module {
    single<NotesRepository> {
        NotesRepositoryImpl(noteDao = get())
    }

    single<NetworkRepository> {
        NetworkRepositoryImpl(
            firebaseDataSource = get(),
            backendDataSource = get(),
        )
    }

    single {
        BackendDataSource(
            backendApi = get(),
        )
    }
}


val useCaseModule = module {
//    ImportantInfo
    factory { GetImportantInfoUseCase(repository = get()) }

//    Notes
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
            deleteNote = get(),
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
    viewModel {
        NewsScreenViewModel(

        )
    }
    viewModel {
        ProfileViewModel(
//            TODO actually its better to have usecases instead of directly repository
            repository = get()
        )
    }
}

val appModule = listOf(
    navigationModule,
    databaseModule,
    networkModule,
    repositoryModule,
    useCaseModule,
    viewModelModule,
)