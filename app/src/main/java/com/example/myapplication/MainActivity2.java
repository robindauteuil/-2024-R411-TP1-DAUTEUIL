package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import android.widget.RatingBar;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity2 extends AppCompatActivity {


    private EditText zoneTitre;
    private EditText description;

    private RatingBar pririority;

    private Task task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        description = findViewById(R.id.description);
        zoneTitre = findViewById(R.id.titre);
        pririority = findViewById(R.id.priority);
        task = (Task) getIntent().getSerializableExtra("taskClicked");
        if (task != null) {
            zoneTitre.setText(task.getTitle());
            description.setText(task.getDescription());
            pririority.setRating(task.getPriority());
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }


    @Override
    public void onBackPressed() {

        createOrUpdate();
        // Créez un Intent pour retourner à la MainActivity
        super.onBackPressed();
        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
        startActivity(intent);
        finish(); // Terminez l'activité actuelle
    }

    protected void createOrUpdate(){

        task.setDescription(description.toString());
        task.setPriority(pririority.getNumStars());
    }
}