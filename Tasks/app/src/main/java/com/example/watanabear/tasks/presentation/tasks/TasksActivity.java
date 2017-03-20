package com.example.watanabear.tasks.presentation.tasks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.watanabear.tasks.R;
import com.example.watanabear.tasks.infra.TasksRepositoryImpl;
import com.example.watanabear.tasks.presentation.ActivityUtils;
import com.example.watanabear.tasks.presentation.ViewModelHolder;

public class TasksActivity extends AppCompatActivity implements TasksNavigator{

    public static final String TASKS_VIEWMODEL_TAG = "tasks_viewmodel";

    private TasksViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TasksFragment fragment = findOrCreateFragment();
        viewModel = findOrCreateViewModel();
        fragment.setViewModel(viewModel);

    }

    private TasksFragment findOrCreateFragment() {
        TasksFragment fragment = (TasksFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (fragment == null) {
            fragment = new TasksFragment();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment, R.id.contentFrame);
        }
        return fragment;
    }

    private TasksViewModel findOrCreateViewModel() {

        @SuppressWarnings("unchecked")
        ViewModelHolder<TasksViewModel> holder =
                (ViewModelHolder<TasksViewModel>) getSupportFragmentManager()
                        .findFragmentByTag(TASKS_VIEWMODEL_TAG);
        if (holder != null && holder.getViewmodel() != null) {
            return holder.getViewmodel();
        } else {
            TasksViewModel viewModel = new TasksViewModel(new TasksRepositoryImpl(),
                    this);
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    ViewModelHolder.createContainer(viewModel),
                    TASKS_VIEWMODEL_TAG);
            return viewModel;
        }
    }


    @Override
    public void addNewTask() {

    }
}
