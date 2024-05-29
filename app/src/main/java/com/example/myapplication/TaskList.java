package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;
import com.example.myapplication.Exception.TaskNotFoundException;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(){
        this.tasks = new ArrayList<Task>() {
        };
    }

    /**
     * Returns a list of all tasks.
     *
     * @return A list of Task objects.
     */
    public ArrayList<Task> getAllTask() {
        return tasks;
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task The Task object to be added.
     */
    public void addTasks(Task task) {
        this.tasks.add(task);
    }

    public void replaceTask(Task task, int index){

        this.tasks.set(index, task);

    }

    /**
     * Removes a task from the task list.
     *
     * @param task The Task object to be removed.
     * @throws TaskNotFoundException if the task doesn't exists
     */
    public void removeTask(Task task)throws TaskNotFoundException {
        if (tasks.contains(task)) tasks.remove(task);
        else throw new TaskNotFoundException("Task with title '" + task.getTitle() + "' not found.");
    }




}
