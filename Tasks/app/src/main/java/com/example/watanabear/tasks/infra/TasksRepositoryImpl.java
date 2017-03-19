package com.example.watanabear.tasks.infra;

import android.support.annotation.NonNull;

import com.example.watanabear.tasks.domain.model.Task;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by watanabear on 2017/03/18.
 */

public class TasksRepositoryImpl implements TasksRepository {

    @Override
    public void getTasks(@NonNull LoadTasksCallback callback) {
        RealmResults<Task> tasks = Realm.getDefaultInstance()
                .where(Task.class)
                .findAll();
        if (tasks.isEmpty()) {
            callback.onDataNotAvailable();
        } else {
            callback.onTasksLoaded(tasks);
        }

    }

    @Override
    public void getTask(@NonNull String taskId, @NonNull GetTaskCallback callback) {
        Task t = Realm.getDefaultInstance()
                .where(Task.class)
                .equalTo(Task.FEILD_ID, taskId)
                .findFirst();
        if (t == null) {
            callback.onDataNotAvailable();
        } else {
            callback.onTaskLoaded(t);
        }
    }

    @Override
    public void saveTask(@NonNull Task task) {
        Realm.getDefaultInstance().executeTransaction(realm -> realm.copyToRealmOrUpdate(task));
    }

    @Override
    public void completeTask(@NonNull String taskId) {
        Realm.getDefaultInstance().executeTransaction(realm -> {
            Task t = realm.where(Task.class).equalTo(Task.FEILD_ID, taskId).findFirst();
            t.isComplete = true;
        });
    }

    @Override
    public void activateTask(@NonNull String taskId) {
        Realm.getDefaultInstance().executeTransaction(realm -> {
            Task t = realm.where(Task.class).equalTo(Task.FEILD_ID, taskId).findFirst();
            t.isComplete = false;
        });
    }

    @Override
    public void clearCompletedTasks() {
        Realm.getDefaultInstance().executeTransaction(realm -> {
            RealmResults<Task> tasks = realm
                    .where(Task.class)
                    .equalTo(Task.FEILD_IS_COMPLETE, true)
                    .findAll();
            tasks.deleteAllFromRealm();
        });
    }

    @Override
    public void deleteAllTasks() {
        Realm.getDefaultInstance().executeTransaction(realm -> realm.delete(Task.class));
    }

    @Override
    public void deleteTask(@NonNull String taskId) {
        Realm.getDefaultInstance().executeTransaction(realm -> {
            Task t = realm.where(Task.class).equalTo(Task.FEILD_ID, taskId).findFirst();
            t.deleteFromRealm();
        });
    }
}
