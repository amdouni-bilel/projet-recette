package tn.esprit.examengl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String USERNAME = "USERNAME";
    public static final String PWD = "PWD";

    private EditText userName, pwd;
    private Button btnLogin;

    private SharedPreferences mPreferences;
    public static final String sharedPrefFile = "tn.esprit.examGL";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Connexion");

        mPreferences = getSharedPreferences(sharedPrefFile,MODE_PRIVATE);

        userName = findViewById(R.id.username);
        pwd = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userName.getText().toString().equals("admin") && pwd.getText().toString().equals("admin")){
                    SharedPreferences.Editor preferencesEditor = mPreferences.edit();
                    preferencesEditor.putString(USERNAME, userName.getText().toString());
                    preferencesEditor.putString(PWD, pwd.getText().toString());
                    preferencesEditor.apply();
                    login();
                }else {
                    Toast.makeText(getApplicationContext(),"Bad credentials !",Toast.LENGTH_SHORT).show();
                }
            }
        });

        if( !mPreferences.getString(USERNAME,"").equals("") && !mPreferences.getString(PWD,"").equals("")){
            login();
        }

    }

    public void login(){
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

}
