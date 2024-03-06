package com.softwareit.sduhub.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.softwareit.sduhub.ui.screens.MapFragment
import com.softwareit.sduhub.ui.screens.NewsFragment
import com.softwareit.sduhub.ui.screens.home_screen.HomeFragment
import com.softwareit.sduhub.ui.screens.home_screen.categories.ai_dos_screen.AiDosFragment
import com.softwareit.sduhub.ui.screens.home_screen.categories.moodle_screen.MoodleFragment
import com.softwareit.sduhub.ui.screens.home_screen.categories.my_sdu_screen.MySduFragment
import com.softwareit.sduhub.ui.screens.home_screen.categories.sdu_library_screen.SduLibraryFragment
import com.softwareit.sduhub.ui.screens.home_screen.categories.sdukz_screen.SduKzFragment
import com.softwareit.sduhub.ui.screens.home_screen.categories.student_clubs_screen.StudentClubsFragment
import com.softwareit.sduhub.ui.screens.home_screen.edit_note_screen.EditNoteFragment
import com.softwareit.sduhub.ui.screens.profile_screen.ProfileFragment

object NavigationScreens {

    object Home {
        fun home() = FragmentScreen { HomeFragment() }
        fun editNote(noteId: Int) = FragmentScreen { EditNoteFragment(noteId) }

        object Categories {
            fun aiAssistant() = FragmentScreen { AiDosFragment() }
            fun mysdu() = FragmentScreen { MySduFragment() }
            fun sdukz() = FragmentScreen { SduKzFragment() }
            fun studentClubs() = FragmentScreen { StudentClubsFragment() }
            fun moodle() = FragmentScreen { MoodleFragment() }
            fun sduLibrary() = FragmentScreen { SduLibraryFragment() }
        }
    }

    fun profile() = FragmentScreen { ProfileFragment() }
    fun news() = FragmentScreen { NewsFragment() }
    fun map() = FragmentScreen { MapFragment() }
}