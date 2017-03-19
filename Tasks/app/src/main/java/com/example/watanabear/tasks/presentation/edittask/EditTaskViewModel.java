package com.example.watanabear.tasks.presentation.edittask;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.support.annotation.Nullable;

import com.example.watanabear.tasks.domain.model.Task;
import com.example.watanabear.tasks.infra.TasksRepository;
import com.google.common.base.Strings;

/**
 * Created by watanabear on 2017/03/19.
 */

public class EditTaskViewModel extends BaseObservable {

    private boolean mIsNewTask;

    @Nullable
    private String mTaskId;

    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> description = new ObservableField<>();
    public ObservableField<Boolean> isComplete = new ObservableField<>();

    private TasksRepository tasksRepository;

    private EditTaskNavigator editTaskNavigator;

    EditTaskViewModel(TasksRepository tasksRepository, EditTaskNavigator editTaskNavigator) {
        this.tasksRepository = tasksRepository;
        this.editTaskNavigator = editTaskNavigator;
    }

    void start(String taskId) {
        if (taskId != null) {
            mTaskId = taskId;
            tasksRepository.getTask(taskId, new TasksRepository.GetTaskCallback() {
                @Override
                public void onTaskLoaded(Task task) {
                    mIsNewTask = false;
                    title.set(task.title);
                    description.set(task.description);
                    isComplete.set(task.isComplete);
                }

                @Override
                public void onDataNotAvailable() {
                    mIsNewTask = true;
                }
            });
        } else {
            mIsNewTask = true;
        }
    }

    void saveTask(String title, String detail) {
        Task t;
        if (mIsNewTask && Strings.isNullOrEmpty(mTaskId)) {
            t = Task.create(title, detail);
        } else {
            t = Task.create(title, detail, mTaskId);
        }
        tasksRepository.saveTask(t);
        editTaskNavigator.onTaskSaved();
    }
}
