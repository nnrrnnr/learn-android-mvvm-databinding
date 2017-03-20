package com.example.watanabear.tasks.presentation.showtask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.watanabear.tasks.R;
import com.example.watanabear.tasks.presentation.ViewModelHolder;
import com.example.watanabear.tasks.infra.TasksRepositoryImpl;
import com.example.watanabear.tasks.presentation.ActivityUtils;
import com.example.watanabear.tasks.presentation.edittask.EditTaskActivity;

public class ShowTaskActivity extends AppCompatActivity implements ShowTaskNavigator {

    public static final String TASK_ID = "TASK_ID";
    public static final String SHOW_TASK_VIEWMODEL_TAG = "TASKS_VIEWMODEL_TAG";

    private ShowTaskActivityFragment fragment;
    private ShowTaskViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragment    = findOrCreateFragment();
        viewModel   = findOrCreateViewModel();
        //ViewとViewModelをリンクさせる
        fragment.setViewModel(viewModel);
    }

    private ShowTaskActivityFragment findOrCreateFragment() {
        String taskId = getIntent().getStringExtra(TASK_ID);
        ShowTaskActivityFragment fragment = (ShowTaskActivityFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);
        if (fragment == null) {
            fragment = ShowTaskActivityFragment.newInstance(taskId);
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.contentFrame);
            return fragment;
        } else {
            return fragment;
        }
    }

    private ShowTaskViewModel findOrCreateViewModel() {

        @SuppressWarnings("unchecked")
        ViewModelHolder<ShowTaskViewModel> holder =
                (ViewModelHolder<ShowTaskViewModel>) getSupportFragmentManager()
                .findFragmentByTag(SHOW_TASK_VIEWMODEL_TAG);

        if (holder != null && holder.getViewmodel() != null) {
            return holder.getViewmodel();
        } else {
            ShowTaskViewModel viewModel = new ShowTaskViewModel(
                    new TasksRepositoryImpl(), this);
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    ViewModelHolder.createContainer(viewModel),
                    SHOW_TASK_VIEWMODEL_TAG);
            return viewModel;
        }
    }

    @Override
    public void onTaskDeleted() {
        finish();
    }

    @Override
    public void onStartEditTask() {
        Intent intent = new Intent(this, EditTaskActivity.class);
        intent.putExtra(TASK_ID, getIntent().getStringExtra(TASK_ID));
        startActivity(intent);
    }

    @Override
    public void onError() {
        Toast.makeText(this, "Task Not Found...", Toast.LENGTH_LONG).show();
    }
}
