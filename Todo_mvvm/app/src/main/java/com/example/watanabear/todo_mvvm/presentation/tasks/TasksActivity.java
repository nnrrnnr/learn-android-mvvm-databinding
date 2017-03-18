package com.example.watanabear.todo_mvvm.presentation.tasks;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.watanabear.todo_mvvm.R;
import com.example.watanabear.todo_mvvm.databinding.ActivityTasksBinding;
import com.example.watanabear.todo_mvvm.domain.model.Task;
import com.example.watanabear.todo_mvvm.infra.repository.TasksRepository;

public class TasksActivity extends AppCompatActivity implements TaskItemNavigator, TasksNavigator{

    public static final String TASKS_VIEWMODEL_TAG = "TASKS_VIEWMODEL_TAG";

    private ActivityTasksBinding tasksBinding;
    private TasksViewModel mViewModel;

    TasksRepository tasksRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tasksBinding = DataBindingUtil.setContentView(this, R.layout.activity_tasks);

        Toolbar toolbar = tasksBinding.toolbar;
        toolbar.inflateMenu(R.menu.filter_tasks);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_add_task);
        fab.setOnClickListener(view -> {
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            Task t = Task.create("sample", "description");
            tasksRepository.saveTask(t);
        });

        mViewModel = new TasksViewModel(
                tasksRepository,
                getApplicationContext(),
                this);

    }


    @Override
    public void addNewTask() {

    }

    @Override
    public void openTaskDetails(String taskId) {

    }
}
