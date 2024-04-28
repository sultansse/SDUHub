package com.softwareit.sduhub.ui.screens.home_screen.categories.services.student_clubs_screen

import com.softwareit.sduhub.R
import com.softwareit.sduhub.core.base.BaseViewModel
import com.softwareit.sduhub.ui.model.StudentClubDIO

class StudentClubsViewModel : BaseViewModel<StudentClubsContract.Event, StudentClubsContract.State, StudentClubsContract.Effect>() {

    override fun setInitialState(): StudentClubsContract.State {
        return StudentClubsContract.State
    }

    override fun handleEvent(event: StudentClubsContract.Event) {
        when (event) {
            is StudentClubsContract.Event.EmptyEffect -> {
                setEffect { StudentClubsContract.Effect.Idle }
            }

            is StudentClubsContract.Event.OnStudentClubClick -> {
                setEffect { StudentClubsContract.Effect.StudentClubDialog(event.studentClub) }
            }

            is StudentClubsContract.Event.OnApplyToClub -> {
                setEffect { StudentClubsContract.Effect.ApplyGoogleForms(event.clubName) }
            }
        }
    }
}


val studentClubs = listOf(
    StudentClubDIO(
        name = "SDU IT Club",
        shortDescription = "This club is for students who are interested in IT",
        longDescription = "This club could help you to improve your programming skills, and furthermore you can participate in hackathons",
        imageResId = R.drawable.img_library,
    ),
    StudentClubDIO(
        name = "Vision Club",
        shortDescription = "This club for women, who want to be successful in their life",
        longDescription = "Club members are very friendly and helpful, they will help you to achieve your goals",
        imageResId = R.drawable.img_library,
    ),
    StudentClubDIO(
        name = "Music Club",
        shortDescription = "Music club is a place where you can learn how to play musical instruments. The club members are very friendly and helpful",
        longDescription = "Music club is a place where you can learn how to play musical instruments. The club members are very friendly and helpful. Music club is a place where you can learn how to play musical instruments. The club members are very friendly and helpful",
        imageResId = R.drawable.img_library,
    ),
    StudentClubDIO(
        name = "Mountain kings",
        shortDescription = "This club is for students who are interested in hiking, and climbing mountains",
        longDescription = "Club members are very friendly and helpful, they will help you to achieve your goals, and furthermore you can participate in hiking trips",
        imageResId = R.drawable.img_library,
    ),
    StudentClubDIO(
        name = "IQ club",
        shortDescription = "This club is for students who are interested in IQ tests",
        longDescription = "Club members are very friendly and helpful, they will help you to achieve your goals, and furthermore you can participate in IQ tests",
        imageResId = R.drawable.img_library,
    ),
    StudentClubDIO(
        name = "Puzzle club",
        shortDescription = "This club is for students who are interested in puzzles",
        longDescription = "Club members are very friendly and helpful, they will help you to achieve your goals, and furthermore you can participate in puzzle contests",
        imageResId = R.drawable.img_library,
    ),
)