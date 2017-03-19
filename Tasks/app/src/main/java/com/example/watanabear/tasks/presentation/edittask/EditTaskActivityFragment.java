package com.example.watanabear.tasks.presentation.edittask;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.watanabear.tasks.R;
import com.example.watanabear.tasks.databinding.FragmentEditTaskBinding;
import com.example.watanabear.tasks.infra.TasksRepository;
import com.example.watanabear.tasks.infra.TasksRepositoryImpl;

/**
 * A placeholder fragment containing a simple view.
 */
public class EditTaskActivityFragment extends Fragment {

    public static final String ARG_TASK_ID = "task_id";

    private FragmentEditTaskBinding editTaskBinding;

    private EditTaskViewModel editTaskViewModel;

    private TasksRepository tasksRepository;

    private EditTaskNavigator editTaskNavigator;

    public EditTaskActivityFragment() {
    }

    public static EditTaskActivityFragment editInstance(String taskId) {
        Bundle args = new Bundle();
        EditTaskActivityFragment fragment = new EditTaskActivityFragment();
        args.putString(ARG_TASK_ID, taskId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        editTaskBinding = FragmentEditTaskBinding.inflate(inflater, container, false);
        return editTaskBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof EditTaskNavigator) {
            editTaskNavigator = (EditTaskNavigator)context;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        tasksRepository = new TasksRepositoryImpl();
        editTaskViewModel = new EditTaskViewModel(tasksRepository, editTaskNavigator);

        if (getArguments() != null) {
            editTaskViewModel.start(getArguments().getString(ARG_TASK_ID));
        } else {
            editTaskViewModel.start(null);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupFab();
    }

    private void setupFab() {
        FloatingActionButton fab =
                (FloatingActionButton) getActivity().findViewById(R.id.fab_save);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTaskViewModel.saveTask(editTaskViewModel.title.get(), editTaskViewModel.description.get());
            }
        });
    }
}
