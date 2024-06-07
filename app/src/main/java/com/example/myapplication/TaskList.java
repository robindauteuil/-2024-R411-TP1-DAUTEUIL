package com.example.myapplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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


    // Méthode pour trier par ordre alphabétique des titres
    public void sortByTitle() {
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                return t1.getTitle().compareToIgnoreCase(t2.getTitle());
            }
        });
    }

    // Méthode pour trier par ordre décroissant des priorités
    public void sortByPriorityDescending() {
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                return Integer.compare(t2.getPriority(), t1.getPriority());
            }
        });
    }




}
