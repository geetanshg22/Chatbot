package com.example.kitaablo;

import android.Manifest;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;

import org.alicebot.ab.AIMLProcessor;
import org.alicebot.ab.Bot;
import org.alicebot.ab.MagicStrings;
import org.alicebot.ab.PCAIMLProcessorExtension;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Welcome extends AppCompatActivity {

    public Bot bot;
    public static org.alicebot.ab.Chat chat;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);

        ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        FirebaseApp.initializeApp(this);

        Button physicsButton = findViewById(R.id.physicsButton);
        Button chemButton = findViewById(R.id.chemButton);
        Button mathButton = findViewById(R.id.mathButton);
        TextView login = findViewById(R.id.welcomeLoginButton);
        TextView register = findViewById(R.id.welcomeRegisterButton);

        final SaveSharedPreferences saveSharedPreferences = new SaveSharedPreferences();
        if(!saveSharedPreferences.getPrefEmail(Welcome.this).isEmpty()){
            login.setVisibility(View.INVISIBLE);
            register.setText("Log out");
        }
        else{
            login.setVisibility(View.VISIBLE);
            register.setText("Register");
        }

        physicsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(saveSharedPreferences.getPrefEmail(Welcome.this).isEmpty()){
                    Intent intent = new Intent(Welcome.this,LoginActivity.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(Welcome.this,Chat.class);
                    startActivity(intent);
                }
            }
        });

        chemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(saveSharedPreferences.getPrefEmail(Welcome.this).isEmpty()){
                    Intent intent = new Intent(Welcome.this,LoginActivity.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(Welcome.this,Chat.class);
                    startActivity(intent);
                }

            }
        });
        mathButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(saveSharedPreferences.getPrefEmail(Welcome.this).isEmpty()){
                    Intent intent = new Intent(Welcome.this,LoginActivity.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(Welcome.this,Chat.class);
                    startActivity(intent);
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Welcome.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(saveSharedPreferences.getPrefEmail(Welcome.this).isEmpty()){
                    Intent intent = new Intent(Welcome.this,Register.class);
                    startActivity(intent);
                }
                else{
                    saveSharedPreferences.setPrefEmail(Welcome.this,"");
                    Intent intent = new Intent(Welcome.this,Welcome.class);
                    startActivity(intent);
                }
            }
        });



        //receiving the assets from the app directory
        AssetManager assets = getResources().getAssets();
        File jayDir = new File(Environment.getExternalStorageDirectory().toString() + "/chatbox/Bots/Bot");
        boolean b = jayDir.mkdirs();
        if (jayDir.exists()) {
            //Reading the file
            try {
                for (String dir : assets.list("Bot")) {
                    File subdir = new File(jayDir.getPath() + "/" + dir);
                    subdir.mkdirs();
                    for (String file : assets.list("Bot/" + dir)) {
                        File f = new File(jayDir.getPath() + "/" + dir + "/" + file);
                        if (f.exists()) {
                            continue;
                        }
                        InputStream in = null;
                        OutputStream out = null;
                        in = assets.open("Bot/" + dir + "/" + file);
                        out = new FileOutputStream(jayDir.getPath() + "/" + dir + "/" + file);
                        //copy file from assets to the mobile's SD card or any secondary memory
                        copyFile(in, out);
                        in.close();
                        in = null;
                        out.flush();
                        out.close();
                        out = null;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //copying the file
    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this,"Press home Button",Toast.LENGTH_SHORT).show();
    }
}
