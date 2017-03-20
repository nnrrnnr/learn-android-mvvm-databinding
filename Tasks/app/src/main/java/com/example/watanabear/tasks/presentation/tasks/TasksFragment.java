package com.example.watanabear.tasks.presentation.tasks;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.watanabear.tasks.R;
import com.example.watanabear.tasks.databinding.FragmentTasksBinding;
import com.example.watanabear.tasks.domain.model.Task;
import com.example.watanabear.tasks.infra.TasksRepository;

import java.util.List;

public class TasksFragment extends Fragment {

    private FragmentTasksBinding binding;
    private TasksViewModel viewModel;

    public TasksFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.start();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTasksBinding.inflate(inflater, container, false);
        binding.setViewmodel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setFab();
    }

    private void setFab() {
        FloatingActionButton fab  = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(v -> viewModel.addNewTask());
    }

    public void setViewModel(TasksViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public class TasksAdapter extends BaseAdapter {

        private TasksRepository repository;
        private TasksViewModel viewModel;
        private List<Task> tasks;

        public TasksAdapter(List<Task> tasks, TasksRepository repository, TasksViewModel viewModel) {
            this.repository = repository;
            this.viewModel = viewModel;
            setTasks(tasks);
        }


        @Override
        public int getCount() {
            return tasks != null ? tasks.size() : 0;
        }

        @Override
        public Object getItem(int position) {
            return tasks.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }

        public void replaceData(List<Task> tasks) {
            setTasks(tasks);
        }

        public void setTasks(List<Task> tasks) {
            this.tasks = tasks;
            notifyDataSetChanged();
        }
    }

}
