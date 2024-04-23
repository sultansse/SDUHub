package com.softwareit.sduhub.ui.screens.home_screen.categories.services.student_clubs_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.github.terrakok.modo.LocalContainerScreen
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.StackScreen
import com.github.terrakok.modo.stack.back
import com.softwareit.sduhub.R
import com.softwareit.sduhub.ui.theme.colorSduDarkGray
import kotlinx.parcelize.Parcelize

val studentClubs = listOf(
    StudentClub(
        name = "SDU IT Club",
        shortDescription = "This club is for students who are interested in IT",
        longDescription = "This club could help you to improve your programming skills, and furthermore you can participate in hackathons",
        imageResId = R.drawable.img_library,
    ),
    StudentClub(
        name = "Vision Club",
        shortDescription = "This club for women, who want to be successful in their life",
        longDescription = "Club members are very friendly and helpful, they will help you to achieve your goals",
        imageResId = R.drawable.img_library,
    ),
    StudentClub(
        name = "Music Club",
        shortDescription = "Music club is a place where you can learn how to play musical instruments. The club members are very friendly and helpful",
        longDescription = "Music club is a place where you can learn how to play musical instruments. The club members are very friendly and helpful. Music club is a place where you can learn how to play musical instruments. The club members are very friendly and helpful",
        imageResId = R.drawable.img_library,
    ),
    StudentClub(
        name = "Mountain kings",
        shortDescription = "This club is for students who are interested in hiking, and climbing mountains",
        longDescription = "Club members are very friendly and helpful, they will help you to achieve your goals, and furthermore you can participate in hiking trips",
        imageResId = R.drawable.img_library,
    ),
    StudentClub(
        name = "IQ club",
        shortDescription = "This club is for students who are interested in IQ tests",
        longDescription = "Club members are very friendly and helpful, they will help you to achieve your goals, and furthermore you can participate in IQ tests",
        imageResId = R.drawable.img_library,
    ),
    StudentClub(
        name = "Puzzle club",
        shortDescription = "This club is for students who are interested in puzzles",
        longDescription = "Club members are very friendly and helpful, they will help you to achieve your goals, and furthermore you can participate in puzzle contests",
        imageResId = R.drawable.img_library,
    ),
)

data class StudentClub(
    val name: String,
    val shortDescription : String,
    val longDescription: String,
    val imageResId: Int,
)

@Parcelize
class StudentClubsScreenClass(
    override val screenKey: ScreenKey = generateScreenKey(),
) : Screen {

    @[Composable ExperimentalMaterial3Api]
    override fun Content() {
        val parent = LocalContainerScreen.current
        val parentScreen = parent as StackScreen

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Student clubs") },
                    navigationIcon = {
                        IconButton(onClick = { parentScreen.back() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }
        ) {
            Box(modifier = Modifier.padding(it)) {
                StudentClubsScreen()
            }
        }
    }

    @Composable
    fun StudentClubsScreen() {
        LazyColumn {
            items(studentClubs.size, key = { studentClubs[it].name }) { index ->
                StudentClubItem(
                    name = studentClubs[index].name,
                    shortDescription = studentClubs[index].shortDescription,
                    longDescription = studentClubs[index].longDescription,
                    imageResId = studentClubs[index].imageResId,
                )
            }
        }
    }

    @Composable
    fun StudentClubItem(
        name: String,
        shortDescription: String,
        longDescription: String,
        imageResId: Int,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clickable {
//                    todo navigate to student club details
                }
                .border(width = 1.dp, color = colorSduDarkGray, shape = RoundedCornerShape(8.dp))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(imageResId),
                    contentDescription = "Student club image",
                    modifier = Modifier.size(72.dp)
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Text(
                        text = name,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = shortDescription,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}