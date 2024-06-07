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
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity2 extends AppCompatActivity {


    private EditText zoneTitre;
    private EditText description;

    private RatingBar priority;

    private Task task;
    private String tasktitle;
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
            tasktitle = task.getTitle();
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }

    /**
     * Gestionnaire de clic pour le bouton d'ajout de tâche.
     * Cette méthode est appelée lorsque l'utilisateur clique sur le bouton pour ajouter une nouvelle tâche.
     * Elle tente de créer  la tâche en utilisant les informations fournies.
     * Si la création est réussie, elle renvoie les données de la tâche à l'activité appelante.
     */
    public void clickOnAdd() {
        // Tente de créer ou de mettre à jour la tâche avec les informations fournies
        createOrUpdate();

        // Crée un Intent pour retourner les données de la tâche à l'activité appelante
        Intent intent = new Intent();
        intent.putExtra("task", task);  // Ajoute l'objet Task mis à jour à l'intent avec la clé "task"

        // Définit le résultat de l'activité comme "ADD" (ajout réussi)
        // et passe les données de l'intent à l'activité appelante
        setResult(ActivityCode.ADD, intent);

        // Termine l'activité actuelle et retourne à l'activité précédente
        finish();
    }


    @Override
    public void onBackPressed() {
        try {
            // Tente de créer ou de mettre à jour la tâche
            createOrUpdate();

            // Préparez les données à retourner seulement si la mise à jour réussit
            Intent intent = new Intent();
            intent.putExtra("task", task);
            setResult(ActivityCode.EDIT, intent);

            // Appeler super.onBackPressed() et finish() pour retourner à l'activité précédente
            super.onBackPressed();
            finish();

        } catch (IllegalArgumentException e) {
            // Attrapez l'exception si la priorité n'est pas entre 0 et 4
            // Affichez un message d'erreur et restez sur l'activité actuelle
            Toast.makeText(this, "La priorité doit être entre 0 et 4", Toast.LENGTH_SHORT).show();
            // Ne pas appeler super.onBackPressed() ni finish() pour rester sur l'activité
        }
    }

    protected void createOrUpdate() {

        if (tasktitle == null ) {
            // Récupérer le texte du champ titre
            tasktitle = zoneTitre.getText().toString().trim();
        }
        task = new Task(
                tasktitle,
                description.getText().toString(),
                (int) priority.getRating()
        );

        // Afficher un message de succès
        Toast.makeText(this, "Tâche mise à jour avec succès", Toast.LENGTH_SHORT).show();
    }
}