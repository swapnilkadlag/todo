package com.swapnilkadlag.todo.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.swapnilkadlag.todo.R;
import com.swapnilkadlag.todo.data.Task;

import java.util.List;

public class MainActivity extends AppCompatActivity implements TaskFragment.BottomSheetListener {

    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.rvTasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final TaskAdapter adapter = new TaskAdapter(viewModel);
        recyclerView.setAdapter(adapter);

        viewModel.getAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                adapter.submitList(tasks);
                adapter.notifyDataSetChanged();
            }
        });

        adapter.setOnItemClickListener(new TaskAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Task task) {
                TaskFragment bottomSheet = new TaskFragment(viewModel, false, task);
                bottomSheet.show(getSupportFragmentManager(), "addTaskBottomSheet");
            }
        });

        FloatingActionButton buttonOpenBottomSheet = findViewById(R.id.btnAddTask);
        buttonOpenBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskFragment bottomSheet = new TaskFragment(viewModel, true);
                bottomSheet.show(getSupportFragmentManager(), "addTaskBottomSheet");
            }
        });
    }

    @Override
    public void onButtonClicked(String text) {

    }
}
