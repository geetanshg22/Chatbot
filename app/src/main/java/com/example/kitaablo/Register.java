package com.example.kitaablo;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Register extends AppCompatActivity {

    EditText username, email, password, confirmPassword;
    Button buttonRegister;

    FirebaseAuth auth;
    DatabaseReference reference;

    int gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FirebaseApp.initializeApp(this);

        username = findViewById(R.id.usernameEditText);
        password = findViewById(R.id.passwordEditText);
        confirmPassword = findViewById(R.id.confirmPasswordEditText);
        email = findViewById(R.id.emailEditText);
        buttonRegister = findViewById(R.id.registerButton);


        auth = FirebaseAuth.getInstance();
        gender = 3;

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameString = String.valueOf(username.getText());
                String emailString = String.valueOf(email.getText());
                String passwordString = String.valueOf(password.getText());
                String confirmPasswordString = String.valueOf(confirmPassword.getText());

                if(usernameString.isEmpty() || passwordString.isEmpty() || confirmPasswordString.isEmpty() || emailString.isEmpty() || gender==3){
                    Toast.makeText(Register.this,"All Fields are Mandatory",Toast.LENGTH_SHORT).show();
                }
                else if(passwordString.length()<6){
                    Toast.makeText(Register.this,"Password must be greater than 6 words",Toast.LENGTH_SHORT).show();
                }
                else if(!passwordString.equals(confirmPasswordString)){
                    Toast.makeText(Register.this,"Passwords do not Match",Toast.LENGTH_SHORT).show();
                }
                else {
                    register(usernameString, passwordString, emailString,gender);
                }
            }
        });

        ImageButton back_login = findViewById(R.id.back_register);
        back_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this,Welcome.class);
                startActivity(intent);
            }
        });

        TextView login_now = findViewById(R.id.login);
        login_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Register.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        TextView male = findViewById(R.id.male);
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                changeBackgroundColorGender(0);


            }
        });

        TextView female = findViewById(R.id.female);
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                changeBackgroundColorGender(1);
            }
        });

        final TextView exclamation1detail = findViewById(R.id.exclamtion1detail);
        final TextView exclamation2detail = findViewById(R.id.exclamtion2detail);
        final TextView exclamation3detail = findViewById(R.id.exclamtion3detail);
        final TextView exclamation4detail = findViewById(R.id.exclamtion4detail);

        final TextView exclamation1 = findViewById(R.id.exclamtion1);
        exclamation1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                exclamation1detail.setVisibility(View.VISIBLE);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        exclamation1detail.setVisibility(View.GONE);
                    }
                },750);



            }
        });
        final TextView exclamation2 = findViewById(R.id.exclamtion2);
        exclamation2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                exclamation2detail.setVisibility(View.VISIBLE);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        exclamation2detail.setVisibility(View.GONE);
                    }
                },750);


            }
        });
        final TextView exclamation3 = findViewById(R.id.exclamtion3);
        exclamation3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                exclamation3detail.setVisibility(View.VISIBLE);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        exclamation3detail.setVisibility(View.GONE);
                    }
                },750);


            }
        });
        final TextView exclamation4 = findViewById(R.id.exclamtion4);
        exclamation4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                exclamation4detail.setVisibility(View.VISIBLE);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        exclamation4detail.setVisibility(View.GONE);
                    }
                },750);


            }
        });


        password.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String passwordStringListener = String.valueOf(password.getText());
                if(passwordStringListener.length()>6) {
                    exclamation3.setVisibility(View.GONE);
                }
                else{
                    exclamation3.setVisibility(View.VISIBLE);
                }
                String confirmPasswordStringListener = String.valueOf(confirmPassword.getText());
                if(passwordStringListener.equals(confirmPasswordStringListener)) {
                    exclamation4.setVisibility(View.GONE);
                }
                else{
                    exclamation4.setVisibility(View.VISIBLE);
                }
            }
        });
        confirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String passwordStringListener = String.valueOf(password.getText());
                String confirmPasswordStringListener = String.valueOf(confirmPassword.getText());
                if(passwordStringListener.equals(confirmPasswordStringListener)) {
                    exclamation4.setVisibility(View.GONE);
                }
                else{
                    exclamation4.setVisibility(View.VISIBLE);
                }
                if(passwordStringListener.length()>6) {
                    exclamation3.setVisibility(View.GONE);
                }
                else{
                    exclamation3.setVisibility(View.VISIBLE);
                }
            }
        });
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String usernameStringListener = String.valueOf(username.getText());
                if(!TextUtils.isEmpty(usernameStringListener)) {
                    exclamation1.setVisibility(View.GONE);
                }
                else{
                    exclamation1.setVisibility(View.VISIBLE);
                }
            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String emailStringListener = String.valueOf(email.getText());
                if(!TextUtils.isEmpty(emailStringListener) && emailStringListener.contains("@") && emailStringListener.contains(".com")) {
                    exclamation2.setVisibility(View.GONE);
                }
                else{
                    exclamation2.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    public void changeBackgroundColorGender(int i){

        TextView male = findViewById(R.id.male);
        TextView female = findViewById(R.id.female);
        Drawable drawable = getDrawable(R.drawable.ripple_text);

        if(i==0){
            male.setBackground(drawable);
            male.setTextColor(Color.parseColor("#FFFFFF"));
            female.setBackgroundColor(Color.parseColor("#1F000000"));
            female.setTextColor(Color.parseColor("#68FFFFFF"));
            gender = 0;

        }
        else if(i==1){
            female.setBackground(drawable);
            female.setTextColor(Color.parseColor("#FFFFFF"));
            male.setBackgroundColor(Color.parseColor("#1F000000"));
            male.setTextColor(Color.parseColor("#68FFFFFF"));
            gender = 1;
        }
    }

    public void register(final String username,final String password,final String email,final int mGender){
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = auth.getCurrentUser();
                    assert user != null;
                    String userID = user.getUid();

                    reference = FirebaseDatabase.getInstance().getReference("users").child(userID);

                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("id", userID);
                    hashMap.put("username", username);
                    hashMap.put("email", email);
                    hashMap.put("password", password);
                    hashMap.put("imageURL", "default");
                    if(mGender==0) {
                        hashMap.put("gender", "Male");
                    }
                    else if(mGender==1){
                        hashMap.put("gender", "Female");
                    }

                    reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(Task<Void> task) {
                            SaveSharedPreferences saveSharedPreferences = new SaveSharedPreferences();
                            saveSharedPreferences.setPrefEmail(Register.this,email);
                            Intent intent = new Intent(Register.this, Chat.class);
                            startActivity(intent);
                        }

                    });
                }
                else{
                    final AlertDialog builder = new AlertDialog.Builder(Register.this).create();
                    View view = getLayoutInflater().inflate(R.layout.alertdialogregister,null);
                    builder.setView(view);
                    TextView login  = view.findViewById(R.id.alertLogin);
                    login.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Register.this,LoginActivity.class);
                            startActivity(intent);
                        }
                    });
                    TextView cancel  = view.findViewById(R.id.alertCancel);
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            builder.dismiss();
                        }
                    });
                    TextView fp  = view.findViewById(R.id.alertFP);
                    fp.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                    builder.show();

                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Register.this,Welcome.class);
        startActivity(intent);
    }
}
