package com.softwareit.sduhub.di

import coil.ImageLoader
import com.google.firebase.database.FirebaseDatabase
import com.softwareit.sduhub.data.local.datastore.DataStoreUtil
import com.softwareit.sduhub.data.network.backend.BackendDataSource
import com.softwareit.sduhub.data.network.backend.BackendService
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
import com.softwareit.sduhub.domain.student_usecase.GetStudentUseCase
import com.softwareit.sduhub.ui.screens.faq_screen.FaqDetailsViewModel
import com.softwareit.sduhub.ui.screens.home_screen.HomeScreenViewModel
import com.softwareit.sduhub.ui.screens.home_screen.categories.services.faculties_screen.FacultiesViewModel
import com.softwareit.sduhub.ui.screens.home_screen.categories.services.student_clubs_screen.StudentClubsViewModel
import com.softwareit.sduhub.ui.screens.internship_details_screen.InternshipDetailsViewModel
import com.softwareit.sduhub.ui.screens.news_screen.NewsDetailsViewModel
import com.softwareit.sduhub.ui.screens.note_details_screen.NoteDetailsViewModel
import com.softwareit.sduhub.ui.screens.profile_screen.ProfileScreenViewModel
import com.softwareit.sduhub.ui.screens.resources_screen.ResourceScreenViewModel
import com.softwareit.sduhub.utils.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module {
    single { provideAppDatabase(context = androidContext()) }
    single { provideNoteDao(appDatabase = get()) }

    single { DataStoreUtil(context = androidContext()) }
}


val networkModule = module {
// TODO make better - not so tight relationship
    single { FirebaseDatabase.getInstance() }
    single { FirebaseDataSource(database = get()) }

    single { provideHttpClient(context = androidContext()) }
    single {
        provideRetrofit(
            moshi = get(),
            okHttpClient = get(),
            baseUrl = BASE_URL,
        )
    }
    factory {
        createService<BackendService>(get())
    }
    single { provideMoshi() }
    single { provideCoilOkHttp() }
    single {
         ImageLoader.Builder(androidContext())
            .okHttpClient(get<OkHttpClient>())
            .build()
    }
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
//    FAQ
    factory { GetFaqItemsUseCase(repository = get()) }
//    profile-student
    factory { GetStudentUseCase(repository = get()) }

//    Notes
    factory { GetNotesUseCase(repository = get()) }
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
        NewsDetailsViewModel()
    }
    viewModel {
        InternshipDetailsViewModel(
            getSpecificInternship = get(),
        )
    }
    viewModel {
        ProfileScreenViewModel(
            getStudentUseCase = get(),
        )
    }
    viewModel {
        FaqDetailsViewModel(
            getFaqItemsUseCase = get(),
        )
    }
    viewModel {
        FacultiesViewModel()
    }
    viewModel {
        StudentClubsViewModel()
    }
}

val appModule = listOf(
    databaseModule,
    networkModule,
    repositoryModule,
    useCaseModule,
    viewModelModule,
)