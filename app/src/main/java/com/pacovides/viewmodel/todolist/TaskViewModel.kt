package com.pacovides.viewmodel.todolist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.util.UUID

class TaskViewModel : ViewModel() {
    var taskItems = MutableLiveData<MutableList<TaskItem>>()

    init {
        taskItems.value = mutableListOf()
    }

    fun addTaskItem(newTask: TaskItem) {
        val list = taskItems.value
        list?.add(newTask)
        taskItems.postValue(list)
    }

    fun updateTaskItem(id: UUID, name: String, desc: String, dueDate: LocalDate?) {
        val list = taskItems.value
        val task = getTask(id)

        task.name = name
        task.description = desc
        task.dueDate = dueDate
        taskItems.postValue(list)
    }

    private fun getTask(id: UUID): TaskItem {
        val list = taskItems.value
        val task: TaskItem? = list?.find { it.id == id }
        return if (task != null) {
            task
        } else {
            //log error nicely?
            System.err.println("Invalid task id $id")
            TaskItem("???", "???")
        }
    }

    fun setCompleted(taskItem: TaskItem) {
        val list = taskItems.value
        val task = getTask(taskItem.id)
        if (task.completedDate == null) {
            task.completedDate = LocalDate.now()
        }

        taskItems.postValue(list)
    }
}