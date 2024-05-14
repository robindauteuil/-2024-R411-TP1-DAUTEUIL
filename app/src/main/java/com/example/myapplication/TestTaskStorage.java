package com.example.myapplication;

public class TestTaskStorage implements IStorageTasks{
    @Override
    public TaskList ReadTasks() {
        TaskList list = new TaskList();
        Task task1 = new Task();
        Task task2 = new Task();
        list.addTasks(task1);
        list.addTasks(task2);

        return list;
    }
}
