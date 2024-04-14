<h1>SDUHub android app</h1>
SDUHub is app for SDU university, with mod of cons.
It has: 

- Informational daily stories

- Note Taking feature

- Important info from university

- Interesting podcasts, meetups and conferences

- News about education and universities

- Internships

- Indoor map of SDU

- Community in telegram

- FAQ

- Centerized information system: gmail, moodle, mysdu, sdukz, library

- ordering food from sdu

- ai chat bot

<h2>Technical stack:</h2>
Jetpack Compose, kotlin, Modo (navigation), Coroutines, Flow, DataStore preferences, Retrofit, Koin (DI)


The project itself has Clean architecture with Data, Presentation and Domain layers

Data layer is responsible:
- for getting data from Firebase / from my KTOR backend service
- Repository is responsible: for single source of truth
- Datasource is responsible: for mapping data from ApiModel() to ModelDTO()
Data layer has also some core functions to use as apiCall, which are located in core or in utils/common

Domain layer is responsible:
- for creating model from different sources
- for additional operations with model as filtering, mapping etc
- for converting ModelDTO POJO's to ModelDIO() 
- it has own models which looks like ModelDTO()

Presentation layer is responsible for:
- For showing ui
- it should be kept simple and stupid as it can be
- we apply MVI architectural pattern, which relias with compose perfectly
- each screen/part of screen has its own ScreenClass(Modo) and ViewModel and Contract between them
- it has own models, which looks like: @Stable ModelDIO()

in Git history you can find that firstly used Cicerone for navigation, lately it was mgrated to Modo.
Compose has great optimization, Good MVI architecture, which's provided by Yusuff's clean architecture pattern



