package com.example.watanabear.tasks.presentation.tasks;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;

import com.example.watanabear.tasks.domain.model.Task;
import com.example.watanabear.tasks.infra.TasksRepository;

/**
 * Created by watanabear on 2017/03/20.
 */

public class TaskItemViewModel extends BaseObservable {

    public final ObservableField<String> title = new ObservableField<>();

    public final ObservableField<String> description = new ObservableField<>();

    private final ObservableField<Task> mTaskObservable = new ObservableField<>();

    private final TasksRepository mTasksRepository;
    private final TaskItemNavigator navigator;

    public TaskItemViewModel(TasksRepository repository, TaskItemNavigator navigator) {
        this.mTasksRepository = repository;
        this.navigator = navigator;
    }

    @Bindable
    public boolean getCompleted() {
        return mTaskObservable.get().isCompleted();
    }

    // This could be an observable, but we save a call to Task.getTitleForList() if not needed.
    @Bindable
    public String getTitleForList() {
        if (mTaskObservable.get() == null) {
            return "No data";
        }
        return mTaskObservable.get().getTitleForList();
    }

    public void taskClicked() {
        String taskId = mTaskObservable.get().id;
        if (taskId == null) {
            // Click happened before task was loaded, no-op.
            return;
        }
        navigator.onTaskClicked(taskId);
    }



}
