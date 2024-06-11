package com.softwareit.sduhub.ui.screens.home_screen.categories.services.alumni_screen

import com.softwareit.sduhub.core.base.BaseViewModel
import com.softwareit.sduhub.ui.model.FacultyDIO

class AlumniViewModel :
    BaseViewModel<AlumniContract.Event, AlumniContract.State, AlumniContract.Effect>() {

    override fun setInitialState(): AlumniContract.State {
        return AlumniContract.State
    }

    override fun handleEvent(event: AlumniContract.Event) {
        when (event) {
            is AlumniContract.Event.EmptyEffect -> {
                setEffect { AlumniContract.Effect.Idle }
            }

            is AlumniContract.Event.OnAlumniDetailsClick -> {
                setEffect { AlumniContract.Effect.AlumniBottomSheet(event.alumni) }
            }
        }
    }
}


data class AlumniDTO(
    val id: Int,
    val fullname: String,
    val image: Int,
    val birthPlace: FacultyDIO?,
    val position: String,
    val language: String?,
    val profession: String,
    val education: String,
    val workPlace: String?,
    val workExperience: String?,
    val awards: String?,
    val quotes: String?,
)
