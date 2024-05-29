package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.TaskList;
public class MainActivity extends AppCompatActivity {


    private  ArrayAdapter<Task> adapter;

    private TaskList taskList;
    private IStorageTasks tasksStore;

    private int selectInt;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tasksStore = new TestTaskStorage();
        taskList = tasksStore.ReadTasks();

        ListView listView = findViewById(R.id.my_list_view);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList.getAllTask());

        // Configurer l'adaptateur pour la ListView
        listView.setAdapter(adapter);

        ImageButton button = findViewById(R.id.imageButton);
        button.setOnClickListener(v -> clickOnAdd());

        // Configurez l'écouteur de clics
        listView.setOnItemClickListener(this::onTaskItemClick);


    }


    // Méthode pour gérer le clic sur les éléments de la ListView
    private void onTaskItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectInt = position; // Sauvegarder l'index de la tâche sélectionnée
        Task selectedTask = taskList.getAllTask().get(position); // Récupérer la tâche sélectionnée
        Intent intent = new Intent(MainActivity.this, MainActivity2.class); // Créer un intent pour lancer MainActivity2
        intent.putExtra("taskClicked", selectedTask); // Passer la tâche sélectionnée à l'activité suivante
        startActivityForResult(intent, ActivityCode.EDIT); // Lancer l'activité pour éditer la tâche
    }



    // Méthode appelée lors du clic sur l'ImageButton pour ajouter une nouvelle tâche
    public void clickOnAdd(){

        Intent intent = new Intent(MainActivity.this, MainActivity2.class);

        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==ActivityCode.EDIT) // code used by startActivityForResult
        {
            if(resultCode== ActivityCode.EDIT) // code sent by setResult in other activity
            {
                Bundle extras = data.getExtras();
                Task taskModif = (Task)extras.getSerializable("task");

                taskList.getAllTask().set(selectInt, taskModif); // Mettre à jour la tâche dans la liste
                adapter.notifyDataSetChanged(); // Notifier l'adaptateur de la mise à jour
                tasksStore.updateTask(taskModif); // Mettre à jour la tâche dans le stockage


            }
        }
        if(requestCode==ActivityCode.ADD) // code used by startActivityForResult
        {
            if(resultCode==ActivityCode.ADD) // code sent by setResult in other activity
            {
                Bundle extras = data.getExtras();
                Task newTask = (Task)extras.getSerializable("task");
                taskList.addTasks(newTask); // Ajouter la nouvelle tâche à la liste
                adapter.notifyDataSetChanged(); // Notifier l'adaptateur de la mise à jour
                tasksStore.addTask(newTask); // Ajouter la nouvelle tâche dans le stockage
            }
        }
    }
}

