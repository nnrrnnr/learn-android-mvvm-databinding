package com.example.watanabear.tasks.presentation.tasks;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.example.watanabear.tasks.domain.model.Task;
import com.example.watanabear.tasks.infra.TasksRepository;

import java.util.List;

/**
 * Created by watanabear on 2017/03/20.
 */

public class TasksViewModel extends BaseObservable {

    public final ObservableList<Task> items = new ObservableArrayList<>();

    private TasksRepository repository;
    private TasksNavigator navigator;

    public TasksViewModel(TasksRepository repository, TasksNavigator navigator) {
        this.navigator = navigator;
        this.repository = repository;
    }

    public void start() {
        loadTasks();
    }

    @Bindable
    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void loadTasks() {
        repository.getTasks(new TasksRepository.LoadTasksCallback() {
            @Override
            public void onTasksLoaded(List<Task> tasks) {
                items.clear();
                items.addAll(tasks);
                notifyChange();
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    void addNewTask(){
        navigator.addNewTask();
    }

}
