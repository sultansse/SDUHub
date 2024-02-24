package com.softwareit.sduhub.ui.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.softwareit.sduhub.ui.screens.MapFragment
import com.softwareit.sduhub.ui.screens.NewsFragment
import com.softwareit.sduhub.ui.screens.ProfileFragment
import com.softwareit.sduhub.ui.screens.home_screen.HomeFragment
import com.softwareit.sduhub.ui.screens.home_screen.categories.sdukz_screen.SduKzFragment
import com.softwareit.sduhub.ui.screens.home_screen.edit_note_screen.EditNoteFragment

object NavigationScreens {

    object Home {
        fun home() = FragmentScreen { HomeFragment() }
        fun editNote(noteId: Int) = FragmentScreen { EditNoteFragment(noteId) }

        object Categories {
            fun sdukz() = FragmentScreen { SduKzFragment() }
        }
    }

    fun profile() = FragmentScreen { ProfileFragment() }
    fun news() = FragmentScreen { NewsFragment() }
    fun map() = FragmentScreen { MapFragment() }
}