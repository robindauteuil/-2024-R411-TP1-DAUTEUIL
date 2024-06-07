package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Exception.TaskNotFoundException;

public class TaskAdapter extends ArrayAdapter<Task> {

    private TaskList model;
    private IStorageTasks storage;

    public TaskAdapter(@NonNull Context context, int resource, TaskList tasks, IStorageTasks storage)
    {
        super(context, resource, tasks.getAllTask());
        model = tasks;
        this.storage = storage;
    }


    /**
     * Ajoute une nouvelle tâche à la liste de tâches.
     *
     * Cette méthode ajoute la tâche spécifiée au modèle, notifie l'adaptateur des changements de données,
     * et met à jour le stockage persistant.
     *
     * @param task La tâche à ajouter.
     */
    public void AddTask(Task task){
        model.addTasks(task);
        notifyDataSetChanged();
        storage.addTask(task);


    }


    /**
     * Met à jour une tâche existante à l'index spécifié.
     *
     * Cette méthode remplace la tâche à l'index donné dans le modèle, notifie l'adaptateur des changements de données,
     * et met à jour le stockage persistant avec les nouvelles informations de la tâche.
     *
     * @param task La tâche avec les informations mises à jour.
     * @param index L'index de la tâche à remplacer dans la liste.
     */
    public void UpdateTask(Task task, int index){
        model.replaceTask(task,index);
        notifyDataSetChanged();
        storage.updateTask(task);


    }


    /**
     * Supprime une tâche de la liste de tâches.
     *
     * Cette méthode tente de supprimer la tâche spécifiée du modèle. Si la tâche est introuvable, elle affiche un message d'erreur.
     * Après la suppression réussie de la tâche, elle notifie l'adaptateur des changements de données et met à jour le stockage persistant.
     *
     * @param task La tâche à supprimer.
     */
    public void RemoveTask(Task task){

        try{
            model.removeTask(task);
            notifyDataSetChanged();
            storage.DeleteTask(task);

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

        holder.completed.setOnCheckedChangeListener(createCheckedChangeListener());
        //Task task = tasks.get(position);
        // Attacher le tag avant de définir le listener
        holder.completed.setTag(task);

        task = getItem(position);
        holder.title.setText(task.getTitle());
        holder.completed.setChecked(task.isCompleted());
        holder.priority.setRating(task.getPriority());
        return convertView;

    }


    private CompoundButton.OnCheckedChangeListener createCheckedChangeListener() {
        return (buttonView, isChecked) -> {
            Task task = (Task) buttonView.getTag();
            task.setCompleted(isChecked);
            storage.updateTask(task);
            //preferencesStorage.UpdateTask(task); // Mettre à jour la tâche dans le stockage
        };
    }


    // Méthode pour trier par ordre alphabétique des titres et notifier les changements
    public void sortByTitle() {
        model.sortByTitle();
        notifyDataSetChanged(); // Notifier l'adaptateur que les données ont changé
    }

    // Méthode pour trier par ordre décroissant des priorités et notifier les changements
    public void sortByPriorityDescending() {
        model.sortByPriorityDescending();
        notifyDataSetChanged(); // Notifier l'adaptateur que les données ont changé
    }


    private class TaskViewHolder{
        TextView title;
        CheckBox completed;
        RatingBar priority;



    }


}
