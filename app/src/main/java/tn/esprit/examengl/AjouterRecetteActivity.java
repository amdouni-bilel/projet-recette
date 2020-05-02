package tn.esprit.examengl;

import androidx.appcompat.app.AppCompatActivity;
import tn.esprit.examengl.database.AppDataBase;
import tn.esprit.examengl.entity.Recette;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class AjouterRecetteActivity extends AppCompatActivity {

    private EditText nom, description;
    private RadioButton rbSamsung, rbApple, rbHuawei;
    private Button saveProd;

    private Recette recette;

    private AppDataBase database ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_recette);

        getSupportActionBar().setTitle("Ajouter une Recette");

        nom = findViewById(R.id.nom);
        description = findViewById(R.id.description);

        rbSamsung = findViewById(R.id.rbSamsung);
        rbApple = findViewById(R.id.rbApple);
        rbHuawei = findViewById(R.id.rbHuawei);

        saveProd = findViewById(R.id.saveProd);

        database = AppDataBase.getAppDatabase(this);

        saveProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validator()) {

                    recette= new Recette(nom.getText().toString(),description.getText().toString() );

                    if(rbHuawei.isChecked()){
                        recette.setMarque("huawei");
                    }else if(rbSamsung.isChecked()){
                        recette.setMarque("samsung");
                    }else if(rbApple.isChecked()){
                        recette.setMarque("apple");
                    }

                    database.recetteDao().insertOne(recette);

                    setResult(RESULT_OK);

                    finish();
                }
            }
        });

    }

    public boolean validator(){
        if (nom.getText().toString().length() == 0
                || description.getText().toString().length() == 0){
            Toast.makeText(this, "Data must not be empty !", Toast.LENGTH_SHORT).show();
            return false;
        }

        Recette tmpRecette = database.recetteDao().findByNom(nom.getText().toString());

        if (tmpRecette != null){
            Toast.makeText(this, "Recette exist in database !", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
