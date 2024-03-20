package com.softwareit.sduhub.ui.screens.home_screen.categories.student_clubs_screen

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.softwareit.sduhub.core.BaseFragment

class StudentClubsFragment : BaseFragment() {

    @Composable
    override fun SetContent() {
        StudentClubsScreen()
    }
}

@Composable
fun StudentClubsScreen() {
    val context: Context = LocalContext.current
    // TODO: Implement StudentClubsScreen
    Toast.makeText(context, "STUDENTS CLUBS SCREEN IS OPENED", Toast.LENGTH_LONG).show()
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun StudentClubsScreenPreview() {
    StudentClubsScreen()
}
