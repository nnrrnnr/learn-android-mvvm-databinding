package com.example.watanabear.tasks.presentation.showtask;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.watanabear.tasks.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class ShowTaskActivityFragment extends Fragment {

    public static final String ARG_TASK_ID = "taskId";

    private ShowTaskViewModel viewModel;

    public ShowTaskActivityFragment() {
    }

    public static ShowTaskActivityFragment newInstance(String taskId) {

        Bundle args = new Bundle();
        args.putString(ARG_TASK_ID, taskId);
        ShowTaskActivityFragment fragment = new ShowTaskActivityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_show_task, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setFub();
    }

    public void setViewModel(ShowTaskViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setFub() {
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab_edit);
        fab.setOnClickListener(v -> viewModel.startEditTask());
    }
}
