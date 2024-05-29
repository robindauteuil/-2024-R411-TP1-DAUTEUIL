package com.example.myapplication;

public interface IStorageTasks {

    public TaskList ReadTasks() ;


    public void updateTask(Task task);


    public void addTask(Task task);
}
