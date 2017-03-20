package com.example.watanabear.tasks.presentation.showtask;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.example.watanabear.tasks.domain.model.Task;
import com.example.watanabear.tasks.infra.TasksRepository;

/**
 * Created by watanabear on 2017/03/20.
 */

public class ShowTaskViewModel extends BaseObservable {

    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> description = new ObservableField<>();
    public ObservableField<Boolean> isComplete = new ObservableField<>();

    private TasksRepository repository;
    private ShowTaskNavigator navigator;

    public ShowTaskViewModel(TasksRepository repository, ShowTaskNavigator navigator) {
        this.repository = repository;
        this.navigator = navigator;
    }

    public void start(@NonNull String taskId) {
        repository.getTask(taskId, new TasksRepository.GetTaskCallback() {
            @Override
            public void onTaskLoaded(Task task) {
                title.set(task.title);
                description.set(task.description);
                isComplete.set(task.isComplete);
            }

            @Override
            public void onDataNotAvailable() {
                navigator.onError();
            }
        });
    }

    void startEditTask() {
        navigator.onStartEditTask();
    }
}
