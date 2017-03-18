package com.example.watanabear.todo_mvvm;

import com.example.watanabear.todo_mvvm.domain.DomainModule;
import com.example.watanabear.todo_mvvm.infra.InfraModule;
import com.example.watanabear.todo_mvvm.presentation.tasks.TasksActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by watanabear on 2017/03/18.
 */
@Singleton
@Component(modules = {InfraModule.class, DomainModule.class, AppModule.class})
public interface AppComponent {
    public void inject(TasksActivity activity);
}
