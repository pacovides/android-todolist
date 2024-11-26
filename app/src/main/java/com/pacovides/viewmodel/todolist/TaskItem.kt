package com.pacovides.viewmodel.todolist

import java.time.LocalDate
import java.util.UUID

data class TaskItem(
    var name: String,
    var description: String,
    var dueDate: LocalDate? = null,
    var completedDate: LocalDate? = null,
    var id : UUID = UUID.randomUUID(),
) {

}