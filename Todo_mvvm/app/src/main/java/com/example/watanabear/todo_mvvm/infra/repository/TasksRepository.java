package com.example.watanabear.todo_mvvm.infra.repository;

import android.support.annotation.NonNull;

import com.example.watanabear.todo_mvvm.domain.model.Task;

import java.util.List;

/**
 * Created by watanabear on 2017/03/18.
 */

public interface TasksRepository {

    interface LoadTasksCallback {

        void onTasksLoaded(List<Task> tasks);

        void onDataNotAvailable();
    }

    interface GetTaskCallback {

        void onTaskLoaded(Task task);

        void onDataNotAvailable();
    }

    void getTasks(@NonNull LoadTasksCallback callback);

    void getTask(@NonNull String taskId, @NonNull GetTaskCallback callback);

    void saveTask(@NonNull Task task);

    void clearCompletedTasks();

    void deleteAllTasks();

    void deleteTask(@NonNull String taskId);


}
