package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Exception.TaskNotFoundException;

public class TaskAdapter extends ArrayAdapter<Task> {

    private TaskList model;

    public TaskAdapter(@NonNull Context context, int resource, TaskList tasks)
    {
        super(context, resource, tasks.getAllTask());
        model = tasks;
    }


    public void AddTask(Task task){
        model.addTasks(task);
        notifyDataSetChanged();

    }

    public void UpdateTask(Task task, int index){
        model.replaceTask(task,index);
        notifyDataSetChanged();

    }

    public void RemoveTask(Task task){

        try{
            model.removeTask(task);
            notifyDataSetChanged();
        }catch(TaskNotFoundException e) {
            Toast.makeText(this.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {

        if(convertView==null){
            convertView =
                    LayoutInflater.from(getContext()).inflate(R.layout.taskview,parent,false);

        }


        TaskViewHolder holder = (TaskViewHolder) convertView.getTag();
        if(holder==null)
        {
            holder = new TaskViewHolder();
            holder.title = convertView.findViewById(R.id.taskTitle);
            holder.completed = convertView.findViewById(R.id.taskCompleted);
            holder.priority = convertView.findViewById(R.id.taskPriority);
            convertView.setTag(holder);
        }

        Task task = getItem(position);
        holder.title.setText(task.getTitle());
        holder.completed = convertView.findViewById(R.id.taskCompleted);
        holder.priority = convertView.findViewById(R.id.taskPriority);
        convertView.setTag(holder);


        task = getItem(position);
        holder.title.setText(task.getTitle());
        holder.completed.setChecked(task.isCompleted());
        holder.priority.setRating(task.getPriority());
        return convertView;

    }



    private class TaskViewHolder{
        TextView title;
        CheckBox completed;
        RatingBar priority;



    }


}
