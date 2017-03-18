package com.example.watanabear.todo_mvvm.presentation.tasks;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.graphics.drawable.Drawable;

import com.example.watanabear.todo_mvvm.R;
import com.example.watanabear.todo_mvvm.domain.model.Task;
import com.example.watanabear.todo_mvvm.infra.repository.TasksRepository;

/**
 * Created by watanabear on 2017/03/19.
 */

public class TasksViewModel {

    // These observable fields will update Views automatically
    public final ObservableList<Task> items = new ObservableArrayList<>();

    public final ObservableBoolean dataLoading = new ObservableBoolean(false);

    public final ObservableField<String> currentFilteringLabel = new ObservableField<>();

    public final ObservableField<String> noTasksLabel = new ObservableField<>();

    public final ObservableField<Drawable> noTaskIconRes = new ObservableField<>();

    public final ObservableBoolean tasksAddViewVisible = new ObservableBoolean();

    // This is a special Observable that will trigger a SnackBarChangedCallback when modified.
    final ObservableField<String> snackbarText = new ObservableField<>();

    private TasksFilterType mCurrentFiltering = TasksFilterType.ALL_TASKS;

    private Context mContext;
    private TasksRepository mTasksRepository;
    private TasksNavigator mNavigator;

    public TasksViewModel(
            TasksRepository repository,
            Context context,
            TasksNavigator navigator) {
        mContext = context.getApplicationContext(); // Force use of Application Context.
        mTasksRepository = repository;
        mNavigator = navigator;

        // Set initial state
        setFiltering(TasksFilterType.ALL_TASKS);
    }

    public void setFiltering(TasksFilterType requestType) {
        mCurrentFiltering = requestType;

        // Depending on the filter type, set the filtering label, icon drawables, etc.
        switch (requestType) {
            case ALL_TASKS:
                currentFilteringLabel.set(mContext.getString(R.string.label_all));
                noTasksLabel.set(mContext.getResources().getString(R.string.no_tasks_all));
                noTaskIconRes.set(mContext.getResources().getDrawable(
                        R.drawable.ic_assignment_turned_in_24dp));
                tasksAddViewVisible.set(true);
                break;
            case ACTIVE_TASKS:
                currentFilteringLabel.set(mContext.getString(R.string.label_active));
                noTasksLabel.set(mContext.getResources().getString(R.string.no_tasks_active));
                noTaskIconRes.set(mContext.getResources().getDrawable(
                        R.drawable.ic_check_circle_24dp));
                tasksAddViewVisible.set(false);
                break;
            case COMPLETED_TASKS:
                currentFilteringLabel.set(mContext.getString(R.string.label_completed));
                noTasksLabel.set(mContext.getResources().getString(R.string.no_tasks_completed));
                noTaskIconRes.set(mContext.getResources().getDrawable(
                        R.drawable.ic_verified_user_24dp));
                tasksAddViewVisible.set(false);
                break;
        }
    }

    public void clearCompletedTasks() {

    }

    public void loadTasks() {

    }

}
