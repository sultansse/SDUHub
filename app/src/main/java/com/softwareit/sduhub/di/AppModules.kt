package com.softwareit.sduhub.di

import org.koin.core.module.Module


//val databaseModule = module {
//    single { provideAppDatabase(context = androidContext()) }
//    single { provideCarDAO(appDatabase = get()) }
//}
//
//val mapperModule = module {
//    factory { CarLocalDataMapper() }
//    factory { CarDataDomainMapper() }
//    factory { CarDomainUiMapper() }
//}
//
//val dataSourceModule = module {
//    factory<LocalDataSource> { provideLocalDataSource(carDao = get(), mapper = get()) }
//}
//
//val repositoryModule = module {
//    single<MyRepository> { provideMyRepository(localDataSource = get(), mapper = get()) }
//}
//
//val useCaseModule = module {
//    factory<PopulateDbUseCase> { providePopulateDbUseCase(repo = get()) }
//    factory<GetCarsUseCase> { provideGetCarsUseCase(repo = get(), mapper = get()) }
//    factory<GetCarUseCase> { provideGetCarUseCase(repo = get(), mapper = get()) }
//    factory<ClearDatabaseUseCase> { provideClearDatabaseUseCase(repo = get()) }
//    factory<GetFavoriteCarsUseCase> { provideGetFavoriteCarsUseCase(repo = get(), mapper = get()) }
//}
//
//
//val otherClassesModule = module {
//    factory { DetailsComposer() }
//}
//
//val viewModelModule = module {
//    viewModel {
//        HomeScreenViewModel(
//            router = get(),
//        )
//    }
//}
//    viewModel {
//        HomeViewModel(
//            getCars = get(),
//        )
//    }
//    viewModel {
//        DetailsViewModel(
//            itemComposer = get(),
//            getCar = get(),
//        )
//    }
//    viewModel {
//        ProfileViewModel(
//            populateDatabase = get(),
//            clearDatabase = get(),
//        )
//    }
//    viewModel {
//        FavoritesViewModel(
//            getFavoriteCars = get(),
//        )
//    }
//}

//val navigationModule = module {
//
//    single<Cicerone<Router>> { Cicerone.create() }
//
//    single { get<Cicerone<Router>>().router }
//
//    single { get<Cicerone<Router>>().getNavigatorHolder() }
//}

//val fragmentModule = module {
//    factory { HomeFragment() }
//    factory { DetailsFragment(text = get()) }
//
//
//}

val appModule = emptyList<Module>(
//    otherClassesModule,
//    mapperModule,
//    databaseModule,
//    dataSourceModule,
//    repositoryModule,
//    useCaseModule,
//    viewModelModule
)