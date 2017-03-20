package com.example.watanabear.tasks.presentation.tasks;

import android.databinding.BaseObservable;
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

    public TaskItemViewModel(TasksRepository repository) {
        this.mTasksRepository = repository;
    }



}
