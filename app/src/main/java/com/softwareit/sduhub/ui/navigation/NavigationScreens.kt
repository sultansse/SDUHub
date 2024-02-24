package com.softwareit.sduhub.ui.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.softwareit.sduhub.ui.screens.CategoryFragment
import com.softwareit.sduhub.ui.screens.MapFragment
import com.softwareit.sduhub.ui.screens.NewsFragment
import com.softwareit.sduhub.ui.screens.ProfileFragment
import com.softwareit.sduhub.ui.screens.home_screen.HomeFragment
import com.softwareit.sduhub.ui.screens.home_screen.edit_note_screen.EditNoteFragment

object NavigationScreens {
    //    object Home : FragmentScreen("home") { override fun createFragment() = HomeFragment() }
    object Home {
        fun home() = FragmentScreen { HomeFragment() }
        fun editNote(noteId: Int) = FragmentScreen { EditNoteFragment(noteId) }
    }

    fun category() = FragmentScreen { CategoryFragment() }
    fun profile() = FragmentScreen { ProfileFragment() }
    fun news() = FragmentScreen { NewsFragment() }
    fun map() = FragmentScreen { MapFragment() }
}