package com.softwareit.sduhub.di

import com.google.firebase.database.FirebaseDatabase
import com.softwareit.sduhub.data.network.backend.BackendDataSource
import com.softwareit.sduhub.data.network.firebase.FirebaseDataSource
import com.softwareit.sduhub.data.repository.FaqRepository
import com.softwareit.sduhub.data.repository.FaqRepositoryImpl
import com.softwareit.sduhub.data.repository.NetworkRepository
import com.softwareit.sduhub.data.repository.NetworkRepositoryImpl
import com.softwareit.sduhub.data.repository.NotesRepository
import com.softwareit.sduhub.data.repository.NotesRepositoryImpl
import com.softwareit.sduhub.domain.faq_usecase.GetFaqItemsUseCase
import com.softwareit.sduhub.domain.important_info_usecase.GetImportantInfoUseCase
import com.softwareit.sduhub.domain.internship_usecase.GetInternshipsUseCase
import com.softwareit.sduhub.domain.internship_usecase.GetSpecificInternshipUseCase
import com.softwareit.sduhub.domain.news_usecase.GetNewsUseCase
import com.softwareit.sduhub.domain.notes_usecase.DeleteNoteUseCase
import com.softwareit.sduhub.domain.notes_usecase.DeleteNotesUseCase
import com.softwareit.sduhub.domain.notes_usecase.GetNotesUseCase
import com.softwareit.sduhub.domain.notes_usecase.UpsertNoteUseCase
import com.softwareit.sduhub.ui.screens.home_screen.HomeScreenViewModel
import com.softwareit.sduhub.ui.screens.home_screen.note_details_screen.NoteDetailsViewModel
import com.softwareit.sduhub.ui.screens.profile_screen.ProfileViewModel
import com.softwareit.sduhub.ui.screens.profile_screen.faq_screen.FaqScreenViewModel
import com.softwareit.sduhub.ui.screens.resources_screen.ResourceScreenViewModel
import com.softwareit.sduhub.ui.screens.resources_screen.internship_details_screen.InternshipDetailsViewModel
import com.softwareit.sduhub.ui.screens.resources_screen.news_screen.NewsScreenViewModel
import com.softwareit.sduhub.utils.datastore.DataStoreUtil
import com.softwareit.sduhub.utils.datastore.ThemeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module {
    single { provideAppDatabase(context = androidContext()) }
    single { provideFaqDao(appDatabase = get())}
    single { provideNoteDao(appDatabase = get()) }

    single { DataStoreUtil(context = androidContext()) }
}


val networkModule = module {
// TODO make better - not so tight relationship
    single { FirebaseDatabase.getInstance() }
    single { FirebaseDataSource(database = get()) }

    single { provideHttpClient(context = androidContext()) }
    single { provideRetrofit(get()) }
    single { provideBackendService(get()) }
}


val repositoryModule = module {
    single<NotesRepository> {
        NotesRepositoryImpl(
            noteDao = get(),
        )
    }

    single<NetworkRepository> {
        NetworkRepositoryImpl(
            firebaseDataSource = get(),
            backendDataSource = get(),
        )
    }

    single<FaqRepository> {
        FaqRepositoryImpl(
            faqDao = get(),
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
//    FAQ
    factory { GetFaqItemsUseCase(repository = get()) }

//    Notes
    factory { GetNotesUseCase(repository = get()) }
    factory { UpsertNoteUseCase(repository = get()) }
    factory { DeleteNoteUseCase(repository = get()) }
    factory { DeleteNotesUseCase(repository = get()) }
}


val viewModelModule = module {
    viewModel {
        ThemeViewModel()
    }
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
        NoteDetailsViewModel(
            notesRepository = get(),
        )
    }
    viewModel {
        ResourceScreenViewModel(
            getNewsUseCase = get(),
            getInternshipsUseCase = get(),
        )
    }
    viewModel {
        NewsScreenViewModel()
    }
    viewModel {
        InternshipDetailsViewModel(
            getSpecificInternship = get(),
        )
    }
    viewModel {
        ProfileViewModel(
            repository = get()
        )
    }
    viewModel {
        FaqScreenViewModel(
            getFaqItems = get(),
        )
    }
}

val appModule = listOf(
    databaseModule,
    networkModule,
    repositoryModule,
    useCaseModule,
    viewModelModule,
)