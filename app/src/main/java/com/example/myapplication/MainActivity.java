package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.TaskList;
public class MainActivity extends AppCompatActivity {


    private  ArrayAdapter<Task> adapter;

    private TaskList taskList;
    private IStorageTasks tasksStore;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tasksStore = new TestTaskStorage();
        taskList = tasksStore.ReadTasks();
        //adapter = taskList.getAllTask()
        ListView listView = findViewById(R.id.my_list_view);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList.getAllTask());

        // Configurer l'adaptateur pour la ListView
        listView.setAdapter(adapter);


        // Configurez l'écouteur de clics
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Task selectedTask = taskList.getAllTask().get(position);
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            intent.putExtra("taskClicked", selectedTask);
            startActivity(intent);
        });


    }
}

