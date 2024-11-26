package com.pacovides.viewmodel.todolist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {
    var name = MutableLiveData<String>()
    var description = MutableLiveData<String>()
}