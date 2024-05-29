package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;

import android.widget.ImageButton;
import android.widget.RatingBar;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity2 extends AppCompatActivity {


    private EditText zoneTitre;
    private EditText description;

    private RatingBar priority;

    private Task task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        description = findViewById(R.id.description);
        zoneTitre = findViewById(R.id.titre);
        priority = findViewById(R.id.priority);
        ImageButton button = findViewById(R.id.imageButton2);


        task = (Task) getIntent().getSerializableExtra("taskClicked");

        button.setOnClickListener(v -> clickOnAdd());

        if (task != null) {
            zoneTitre.setText(task.getTitle());
            description.setText(task.getDescription());
            priority.setRating(task.getPriority());
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }

public void clickOnAdd(){
    createOrUpdate();
    Intent intent = new Intent();
    intent.putExtra("task", task);
    setResult(ActivityCode.ADD, intent);
    finish();
}


    @Override
    public void onBackPressed() {


        createOrUpdate();  // Mettez à jour la tâche

        // Préparez les données à retourner
        Intent intent = new Intent();
        intent.putExtra("task", task);
        setResult(ActivityCode.EDIT, intent);
        super.onBackPressed();
        finish();

    }

    protected void createOrUpdate(){

        task = new Task(zoneTitre.getText().toString(),description.getText().toString(),(int) priority.getRating());

    }
}