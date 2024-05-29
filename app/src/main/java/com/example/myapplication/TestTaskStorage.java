package com.example.myapplication;

public class TestTaskStorage implements IStorageTasks{
    @Override
    public TaskList ReadTasks() {
        TaskList list = new TaskList();
        Task task1 = new Task("tache 1", null,0);
        Task task2 = new Task("tache 2", null,0);
        list.addTasks(task1);
        list.addTasks(task2);

        return list;
    }

    @Override
    public void updateTask(Task task) {

    }

    @Override
    public void addTask(Task task) {

    }


}
