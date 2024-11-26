package com.pacovides.viewmodel.todolist

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.pacovides.viewmodel.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var taskMainView: ActivityMainBinding
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        taskMainView = ActivityMainBinding.inflate(layoutInflater)
        setContentView(taskMainView.root)
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        taskMainView.newTaskButton.setOnClickListener {
            NewTaskSheet().show(supportFragmentManager, "NewTaskSheet")
        }

//        taskViewModel.name.observe(this) {
//            taskMainView.taskName.text = String.format("Name: %s", it)
//        }
//
//        taskViewModel.description.observe(this) {
//            taskMainView.taskDescription.text = String.format("Is a %s", it)
//        }

        ViewCompat.setOnApplyWindowInsetsListener(taskMainView.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}