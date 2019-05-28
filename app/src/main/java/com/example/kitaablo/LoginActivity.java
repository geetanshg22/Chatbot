package com.example.kitaablo;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class LoginActivity extends AppCompatActivity {

    EditText user_email, password;
    Button buttonLogin;

    FirebaseAuth auth;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseApp.initializeApp(this);

        user_email = findViewById(R.id.user_emailEditTextLogin);
        password = findViewById(R.id.passwordEditTextLogin);
        buttonLogin = findViewById(R.id.loginButton);

        auth = FirebaseAuth.getInstance();

        ImageButton back_login = findViewById(R.id.back_login);
        back_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,Welcome.class);
                startActivity(intent);
            }
        });


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user_emailString = String.valueOf(user_email.getText());
                String passwordString = String.valueOf(password.getText());

                if(user_emailString.isEmpty() || passwordString.isEmpty()){
                    Toast.makeText(LoginActivity.this,"All Fields are Mandatory",Toast.LENGTH_SHORT).show();
                }
                else{
                    auth.signInWithEmailAndPassword(user_emailString,passwordString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                ProgressBar progressBar = findViewById(R.id.login_progress_bar);
                                progressBar.setVisibility(View.VISIBLE);
                                SaveSharedPreferences saveSharedPreferences = new SaveSharedPreferences();
                                saveSharedPreferences.setPrefEmail(LoginActivity.this,user_emailString);
                                Intent intent = new Intent(LoginActivity.this,Chat.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(LoginActivity.this,"Wrong Credentials",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        TextView register_now = findViewById(R.id.registernow);
        register_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this,Register.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(LoginActivity.this,Welcome.class);
        startActivity(intent);
    }

}


