package com.example.watanabear.todo_mvvm.infra;


import com.example.watanabear.todo_mvvm.infra.repository.TasksRepository;
import com.example.watanabear.todo_mvvm.infra.repository.TasksRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by watanabear on 2017/03/18.
 */
@Module
public class InfraModule {

    @Singleton
    @Provides
    public TasksRepository provideTasksRepository() {
        return new TasksRepositoryImpl();
    }
}
