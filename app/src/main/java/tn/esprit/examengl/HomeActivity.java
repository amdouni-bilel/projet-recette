package tn.esprit.examengl;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import tn.esprit.examengl.database.AppDataBase;
import tn.esprit.examengl.entity.Recette;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 99;
    public static final String NAME_KEY = "NAME";
    public static final String DESCRIPTION_KEY = "DESCRIPTION";
    private Button btnAddProd, btnLogout;
    private RecyclerView mRecyclerView;
    private RecetteAdapter recetteAdapter;

    private List<Recette> recettes;

    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setTitle("Phone Store");

        btnAddProd = findViewById(R.id.addProd);
        btnLogout = findViewById(R.id.logout);
        mRecyclerView = findViewById(R.id.recycler);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mPreferences = getSharedPreferences(MainActivity.sharedPrefFile, MODE_PRIVATE);

        recettes = new ArrayList<Recette>();

        recettes = AppDataBase.getAppDatabase(getApplicationContext()).recetteDao().getAllRecettes();

        recetteAdapter = new RecetteAdapter(this, recettes);

        mRecyclerView.setAdapter(recetteAdapter);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor preferencesEditor = mPreferences.edit();
                preferencesEditor.clear();
                preferencesEditor.apply();

                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);

                finish();
            }
        });

        btnAddProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AjouterRecetteActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        recettes = AppDataBase.getAppDatabase(getApplicationContext()).recetteDao().getAllRecettes();
        recetteAdapter = new RecetteAdapter(this, recettes);
        mRecyclerView.setAdapter(recetteAdapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            recettes = AppDataBase.getAppDatabase(getApplicationContext()).recetteDao().getAllRecettes();
            recetteAdapter = new RecetteAdapter(this, recettes);
            mRecyclerView.setAdapter(recetteAdapter);
        }
    }
}
