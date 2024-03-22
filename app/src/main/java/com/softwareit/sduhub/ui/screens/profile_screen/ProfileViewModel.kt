package com.softwareit.sduhub.ui.screens.profile_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softwareit.sduhub.data.network.backend.Student
import com.softwareit.sduhub.data.repository.NetworkRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: NetworkRepository
) : ViewModel() {

    private val _student = MutableStateFlow(
        Student(
            fullname = "Name Surname",
            studentId = 200100100,
            faculty = "Faculty of Engineering",
        )
    )
    val student: StateFlow<Student> = _student

    init {
        viewModelScope.launch {
            val data = repository.getStudent()
            Log.e("TAG", ">>>> ProfileViewModel.kt ->  (27): $data ");
            _student.value = data
        }
    }
}