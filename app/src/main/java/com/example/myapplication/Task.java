package com.example.myapplication;

import java.io.Serializable;

public class Task implements Serializable {

    private  String title ;
    private  String description ;
    private  int priority ;
    private  boolean completed ;



    /**
     * Constructs a new Task.
     *
     * @param title The title of the task, which must not be null or empty.
     * @param description The description of the task (optional).
     * @param priority The importance of the task, must be between 0 and 4 (optional).
     * @throws IllegalArgumentException if the title is null or empty, or if importance is not between 0 and 4.
     */
    public Task(String title, String description, int priority) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title must not be null or empty.");
        }

        this.title = title;
        this.description = description;
        this.completed = false;
        setPriority(priority);
    }

    // Getter for title
    public String getTitle() {
        return title;
    }



    // Getter for description
    public String getDescription() {
        return description;
    }

    // Setter for description
    public void setDescription(String description) {
        this.description = description;
    }

    // Getter for priority
    public int getPriority() {
        return priority;
    }

    // Setter for priority
    public void setPriority(int priority) {
        if (priority < 0 || priority > 4) {
            throw new IllegalArgumentException("Importance must be between 0 and 4.");
        }
        this.priority = priority;
    }

    // Getter for completed status
    public boolean isCompleted() {
        return completed;
    }

    // Setter for completed status
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }


    @Override
    public boolean equals(Object obj) {
        boolean res = false;
        if (this == obj) {
            res = true;
        } else if (obj != null && getClass() == obj.getClass()) {
            Task task = (Task) obj;
            res = title.equals(task.title);
        }
        return res;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Importance: " + priority;
    }

}
