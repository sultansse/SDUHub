package com.softwareit.sduhub.di


//fun provideLocalDataSource(carDao: CarDao, mapper: CarLocalDataMapper): LocalDataSource =
//    LocalDataSourceImpl(carDao, mapper)
//
//fun provideMyRepository(
//    localDataSource: LocalDataSource,
//    mapper: CarDataDomainMapper
//): MyRepository =
//    MyRepositoryImpl(localDataSource, mapper)
//
////usecases
//fun providePopulateDbUseCase(repo: MyRepository): PopulateDbUseCase =
//    PopulateDbUseCaseImpl(repo)
//
//fun provideGetCarsUseCase(repo: MyRepository, mapper: CarDomainUiMapper): GetCarsUseCase =
//    GetCarsUseCaseImpl(repo, mapper)
//
//fun provideGetCarUseCase(repo: MyRepository, mapper: CarDomainUiMapper): GetCarUseCase =
//    GetCarUseCaseImpl(repo, mapper)
//
//fun provideClearDatabaseUseCase(repo: MyRepository): ClearDatabaseUseCase =
//    ClearDatabaseUseCaseImpl(repo)
//
//fun provideGetFavoriteCarsUseCase(
//    repo: MyRepository,
//    mapper: CarDomainUiMapper
//): GetFavoriteCarsUseCase =
//    GetFavoriteCarsUseCaseImpl(repo, mapper)
//
////db
//fun provideCarDAO(appDatabase: AppDatabase) = appDatabase.carDao()
//fun provideAppDatabase(context: Context): AppDatabase {
//    return Room.databaseBuilder(
//        context,
//        AppDatabase::class.java,
//        DATABASE_NAME
//    ).build()
//}
//


//mappers
/*
fun provideLocalDataMapper() : Mapper<CarLocalModel, CarDataModel> = CarLocalDataMapper()
fun provideDataDomainMapper() : Mapper<CarDataModel, CarDomainModel> = CarDataDomainMapper()
fun provideDomainUiMapper() : Mapper<CarDomainModel, CarUiModel> = CarDomainUiMapper()
*/

/*
fun provideLocalDataSource(carDao: CarDao, mapper: Mapper<CarLocalModel, CarDataModel>): LocalDataSource = LocalDataSourceImpl(carDao, mapper)
fun provideMyRepository(localDataSource: LocalDataSource, mapper: Mapper<CarDataModel, CarDomainModel>): MyRepository = MyRepositoryImpl(localDataSource, mapper)
fun provideGetMyDataUseCase(repo: MyRepository, mapper: Mapper<CarDomainModel, CarUiModel>): GetMyDataUseCase = GetMyDataUseCaseImpl(repo, mapper)
*/