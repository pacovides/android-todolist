package com.pacovides.viewmodel.todolist

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pacovides.viewmodel.todolist.databinding.FragmentNewTaskSheetBinding

class NewTaskSheet(private val taskItem: TaskItem? = null) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentNewTaskSheetBinding
    private lateinit var taskViewModel: TaskViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()

        if (taskItem != null) {
            binding.taskTitle.text = getString(R.string.bottom_sheet_task_title_edit_task)
            val editableFactory = Editable.Factory.getInstance()
            binding.taskName.text = editableFactory.newEditable(taskItem.name)
            binding.taskDescription.text = editableFactory.newEditable(taskItem.description)
        } else {
            binding.taskTitle.text = getString(R.string.bottom_sheet_task_title_new_task)
        }

        taskViewModel = ViewModelProvider(activity).get(TaskViewModel::class.java)
        binding.saveButton.setOnClickListener {
            saveAction()
        }
    }

    private fun saveAction() {
        val name = binding.taskName.text.toString()
        val description = binding.taskDescription.text.toString()
        if (taskItem == null) {
            //New task
            val newTask = TaskItem(name, description)
            taskViewModel.addTaskItem(newTask)
        } else {
            taskViewModel.updateTaskItem(taskItem.id, name, description)
        }

        binding.taskName.setText("")
        binding.taskDescription.setText("")
        dismiss()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewTaskSheetBinding.inflate(inflater, container, false)
        return binding.root
    }


}