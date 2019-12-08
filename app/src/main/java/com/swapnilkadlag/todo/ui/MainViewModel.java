package com.swapnilkadlag.todo.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.swapnilkadlag.todo.data.Task;
import com.swapnilkadlag.todo.data.TaskRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private TaskRepository repository;
    private LiveData<List<Task>> allTasks;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new TaskRepository(application);
        allTasks = repository.getAllTasks();
    }

    public void insertTask(Task task) {
        repository.insert(task);
    }

    public void updateTask(Task task) {
        repository.update(task);
    }

    public void deleteTask(Task task) {
        repository.delete(task);
    }

    public void deleteAllCompletedTasks() {
        repository.deleteAllCompleted();
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

}
