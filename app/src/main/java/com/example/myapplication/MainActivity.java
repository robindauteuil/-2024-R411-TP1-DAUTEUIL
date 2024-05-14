package com.example.myapplication;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.TaskList;
public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private  ArrayAdapter<TaskList> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.listView = findViewById(R.id.my_list_view);
        //val adapter = ArrayAdapter<TaskList>


    }
}