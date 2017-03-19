package com.example.watanabear.tasks.presentation.edittask;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.watanabear.tasks.R;
import com.example.watanabear.tasks.databinding.ActivityEditTaskBinding;
import com.example.watanabear.tasks.presentation.ActivityUtils;

public class EditTaskActivity extends AppCompatActivity implements EditTaskNavigator{

    private ActivityEditTaskBinding editTaskBinding;

    private EditTaskActivityFragment editTaskActivityFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editTaskBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_task);

        if (getIntent().hasExtra("taskId")) {
            editTaskActivityFragment = EditTaskActivityFragment.editInstance(getIntent().getStringExtra("taskId"));
        } else {
            editTaskActivityFragment = new EditTaskActivityFragment();
        }

        ActivityUtils.addFragmentToActivity(
                getSupportFragmentManager(),
                editTaskActivityFragment,
                editTaskBinding.contentFrame.getId());
    }

    @Override
    public void onTaskSaved() {
        finish();
    }
}
