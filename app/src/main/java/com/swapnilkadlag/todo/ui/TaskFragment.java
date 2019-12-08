package com.swapnilkadlag.todo.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.swapnilkadlag.todo.R;
import com.swapnilkadlag.todo.data.Task;

public class TaskFragment extends BottomSheetDialogFragment {
    private MainViewModel viewModel;
    private boolean isNewTask;
    private Task task;

    public TaskFragment(MainViewModel viewModel, boolean isNewTask) {
        this.viewModel = viewModel;
        this.isNewTask = isNewTask;
    }

    public TaskFragment(MainViewModel viewModel, boolean isNewTask, Task task) {
        this.viewModel = viewModel;
        this.isNewTask = isNewTask;
        this.task = task;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_task, container, false);

        MaterialButton btnSave = v.findViewById(R.id.btnSave);
        final TextView txtTitle = v.findViewById(R.id.txtTitle);
        final TextView txtDescription = v.findViewById(R.id.txtDescription);
        final CheckBox checkBox = v.findViewById(R.id.checkbox);
        final ImageButton btnDelete = v.findViewById(R.id.btnDelete);
        final TextView txtTask = v.findViewById(R.id.txtTask);

        if (isNewTask) {
            checkBox.setVisibility(View.INVISIBLE);
            btnDelete.setVisibility(View.INVISIBLE);
            txtTask.setText("new task");
        }
        else {
            txtTitle.setText(task.getTitle());
            txtDescription.setText(task.getDescription());
            checkBox.setChecked(task.isDone());
            txtTask.setText("task");
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtTitle.getText().toString().equals("") && txtTitle.getText() != null) {
                    if (isNewTask) {
                        viewModel.insertTask(new Task(txtTitle.getText().toString(), txtDescription.getText().toString(), false));
                    } else {
                        task.setTitle(txtTitle.getText().toString());
                        task.setDescription(txtDescription.getText().toString());
                        task.setDone(checkBox.isChecked());
                        viewModel.updateTask(task);
                    }
                    dismiss();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isNewTask){
                    viewModel.deleteTask(task);
                }
                dismiss();
            }
        });

        return v;
    }

    public interface BottomSheetListener {
        void onButtonClicked(String text);
    }
}
