package com.example.myapplication;

public class Task {

    private  String title ;
    private  String description ;
    private  int priority ;
    private  boolean completed ;

    // Getter for title
    public String getTitle() {
        return title;
    }

    // Setter for title
    public void setTitle(String title) {
        this.title = title;
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

}
