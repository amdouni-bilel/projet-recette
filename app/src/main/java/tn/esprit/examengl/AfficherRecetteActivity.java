package tn.esprit.examengl;

import androidx.appcompat.app.AppCompatActivity;

import tn.esprit.examengl.database.AppDataBase;
import tn.esprit.examengl.entity.Recette;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AfficherRecetteActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView nom, description;
    private Button delete, modif;
    private int id;
    private Recette recette;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_recette);

        getSupportActionBar().setTitle("Informations");

        imageView = findViewById(R.id.imageRecette);
        nom = findViewById(R.id.nom);
        description = findViewById(R.id.description);
        delete = findViewById(R.id.delete);
        id = getIntent().getIntExtra("ID_PROD", 0);
        recette = AppDataBase.getAppDatabase(getApplicationContext()).recetteDao().findById(id);
        modif = findViewById(R.id.modif);
        nom.setText(recette.getNom());
        description.setText(recette.getDescription());


        if (recette.getMarque().equals("samsung")) {
            imageView.setImageResource(R.drawable.ic_samsung);
        } else if (recette.getMarque().equals("huawei")) {
            imageView.setImageResource(R.drawable.ic_huawei);
        } else if (recette.getMarque().equals("apple")) {
            imageView.setImageResource(R.drawable.ic_apple);
        }

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDataBase.getAppDatabase(getApplicationContext()).recetteDao().delete(recette);
                finish();
            }
        });
        modif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ajouter une indication qu'il sagit d'une modification et l'identifiant du produit à modifier
                Intent intent = new Intent(AfficherRecetteActivity.this, AjouterRecetteActivity.class);
                intent.putExtra("modif", true);
                intent.putExtra("idProduit", id);
                startActivity(intent);
                finish();
            }
        });

//        Log.e("AFFICHER",getIntent().getIntExtra("ID_PROD",0) + "");
    }
}
