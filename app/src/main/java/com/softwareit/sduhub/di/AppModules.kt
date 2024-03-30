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
import com.softwareit.sduhub.domain.importtant_info_usecase.GetImportantInfoUseCase
import com.softwareit.sduhub.domain.internship_usecase.GetInternshipsUseCase
import com.softwareit.sduhub.domain.internship_usecase.GetSpecificInternshipUseCase
import com.softwareit.sduhub.domain.news_usecase.GetNewsUseCase
import com.softwareit.sduhub.domain.notes_usecase.DeleteNoteUseCase
import com.softwareit.sduhub.domain.notes_usecase.DeleteNotesUseCase
import com.softwareit.sduhub.domain.notes_usecase.GetNoteUseCase
import com.softwareit.sduhub.domain.notes_usecase.GetNotesUseCase
import com.softwareit.sduhub.domain.notes_usecase.UpsertNoteUseCase
import com.softwareit.sduhub.ui.screens.home_screen.HomeScreenViewModel
import com.softwareit.sduhub.ui.screens.home_screen.edit_note_screen.EditNoteViewModel
import com.softwareit.sduhub.ui.screens.profile_screen.ProfileViewModel
import com.softwareit.sduhub.ui.screens.resources_screen.ResourceScreenViewModel
import com.softwareit.sduhub.ui.screens.resources_screen.internship_screen.InternshipScreenViewModel
import com.softwareit.sduhub.ui.screens.resources_screen.news_screen.NewsScreenViewModel
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

    single { provideHttpClient(context = androidContext()) }
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
//    News
    factory { GetNewsUseCase(repository = get()) }
//    Internships
    factory { GetInternshipsUseCase(repository = get()) }
    factory { GetSpecificInternshipUseCase(repository = get()) }

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
            getNotes = get(),
            upsertNote = get(),
            deleteNotes = get(),
            deleteNote = get(),
            getImportantInfo = get(),
        )
    }
    viewModel {
        EditNoteViewModel(
            getNote = get(),
            upsertNote = get(),
            deleteNote = get(),
        )
    }
    viewModel {
        ResourceScreenViewModel(
            router = get(),
            getNewsUseCase = get(),
            getInternshipsUseCase = get(),
        )
    }
    viewModel {
        NewsScreenViewModel(
            router = get(),
        )
    }
    viewModel {
        InternshipScreenViewModel(
            router = get(),
            getSpecificInternship = get(),
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