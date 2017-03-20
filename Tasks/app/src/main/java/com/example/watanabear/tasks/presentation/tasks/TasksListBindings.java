package com.example.watanabear.tasks.presentation.tasks;

import android.databinding.BindingAdapter;
import android.widget.ListView;

import com.example.watanabear.tasks.domain.model.Task;

import java.util.List;

/**
 * Created by watanabear on 2017/03/20.
 */

public class TasksListBindings {

    @SuppressWarnings("unchecked")
    @BindingAdapter("app:tasks")
    public static void setItems(ListView listView, List<Task> items) {
        TasksFragment.TasksAdapter adapter = (TasksFragment.TasksAdapter) listView.getAdapter();
        if (adapter != null) {
            adapter.replaceData(items);
        }
    }
}
