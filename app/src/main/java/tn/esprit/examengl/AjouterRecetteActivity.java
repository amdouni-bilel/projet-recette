package tn.esprit.examengl;

import androidx.appcompat.app.AppCompatActivity;

import tn.esprit.examengl.database.AppDataBase;
import tn.esprit.examengl.entity.Recette;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;


public class AjouterRecetteActivity extends AppCompatActivity {

    private EditText nom, description;
    private Button saveProd;
    private Spinner spinner;
    private Recette recette;
    private AppDataBase database;
    private RadioButton rbSamsung, rbApple, rbHuawei;
    private int id;
    private boolean modif;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_recette);
        initViews();
        getExtra();
        addItemsOnSpinner();
        addListenerOnButton();
    }

    private void initViews() {

        spinner = (Spinner) findViewById(R.id.spinner);
        saveProd = (Button) findViewById(R.id.saveProd);

        getSupportActionBar().setTitle("Ajouter une Recette");

        nom = findViewById(R.id.nom);
        description = findViewById(R.id.description);

        saveProd = findViewById(R.id.saveProd);
        rbHuawei = findViewById(R.id.rbHuawei);
        rbApple = findViewById(R.id.rbApple);
        rbSamsung = findViewById(R.id.rbSamsung);

        database = AppDataBase.getAppDatabase(this);
    }

    private void getExtra() {

        modif = getIntent().getBooleanExtra("modif", false);
        if (modif) {
            id = getIntent().getIntExtra("idProduit", 0);
            fillForm();
            getSupportActionBar().setTitle("Modifier Produit");
        }
    }

    private void fillForm() {
        recette = AppDataBase.getAppDatabase(getApplicationContext()).recetteDao().findById(id);
        nom.setText(recette.getNom());
        description.setText(recette.getDescription());
    }

    // add items into spinner dynamically
    public void addItemsOnSpinner() {

        spinner = (Spinner) findViewById(R.id.spinner);
        List<String> list = new ArrayList<String>();
        list.add("salé");
        list.add("Sucré");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        saveProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validator()) {
                    if (modif) {
                        // requette de modification
                        recette.setDescription(description.getText().toString());
                        recette.setNom(nom.getText().toString());
                        if (rbHuawei.isChecked()) {
                            recette.setMarque("huawei");
                        } else if (rbSamsung.isChecked()) {
                            recette.setMarque("samsung");
                        } else if (rbApple.isChecked()) {
                            recette.setMarque("apple");
                        }
                        database.recetteDao().updateProduct(recette);
                    } else {
                        // ajout d'un nouveau produit
                        recette = new Recette(nom.getText().toString(), description.getText().toString());

                        if (rbHuawei.isChecked()) {
                            recette.setMarque("huawei");
                        } else if (rbSamsung.isChecked()) {
                            recette.setMarque("samsung");
                        } else if (rbApple.isChecked()) {
                            recette.setMarque("apple");
                        }
                        database.recetteDao().insertOne(recette);

                    }
                    setResult(RESULT_OK);

                    finish();
                }
            }
        });

    }

    public boolean validator() {
        if (nom.getText().toString().length() == 0 || description.getText().toString().length() == 0) {
            Toast.makeText(this, "Data must not be empty !", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!modif) {
            Recette tmpProduit = database.recetteDao().findByNom(nom.getText().toString());

            if (tmpProduit != null) {
                Toast.makeText(this, "Produit exist in database !", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        return true;
    }
}
