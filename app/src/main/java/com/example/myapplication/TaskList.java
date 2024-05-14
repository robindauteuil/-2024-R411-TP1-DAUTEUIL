package com.example.myapplication;

import java.util.List;

public class TaskList {

    private List<Task> tasks;

    // Getter for title
    public List<Task> Tasks() {
        return tasks;
    }

    public void addTasks(Task tasks) {
        this.tasks.add(tasks);
    }
}
