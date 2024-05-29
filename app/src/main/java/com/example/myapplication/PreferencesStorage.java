package com.example.myapplication;
import android.content.SharedPreferences;
import com.google.gson.Gson;

import android.content.SharedPreferences;

public class PreferencesStorage implements IStorageTasks{

    private TaskList tasks;

    private SharedPreferences preferences;

    private Gson gson;

    public PreferencesStorage(SharedPreferences preferences){
        this.preferences = preferences;
        //SharedPreferences.Editor editor = this.preferences.edit();
        //this.gson = new Gson();
        this.tasks = null;

    }
    @Override
    public TaskList ReadTasks() {
        if (tasks == null){
            String tasksString = preferences.getString("tasks", null);
            if(tasksString != null){
                Gson gson = new Gson();
                tasks = gson.fromJson(tasksString, TaskList.class);
            }
        }
        if( tasks == null) tasks = new TaskList();
        return this.tasks;
    }

    @Override
    public void updateTask(Task task) {

        //this.tasks.replaceTask(task);

    }

    @Override
    public void addTask(Task task) {
        saveTasks();
        //this.tasks.addTasks(task);

    }

    private void saveTasks() {
        Gson gson = new Gson();

        SharedPreferences.Editor editor =  preferences.edit();
        String json = gson.toJson(tasks);
        editor.putString("tasks", json);
        editor.apply();
    }
}
