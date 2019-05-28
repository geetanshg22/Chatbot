package com.example.kitaablo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import org.alicebot.ab.AIMLProcessor;
import org.alicebot.ab.Bot;
import org.alicebot.ab.MagicStrings;
import org.alicebot.ab.PCAIMLProcessorExtension;
import java.util.ArrayList;

public class Chat extends AppCompatActivity {

    public Bot bot;
    public static org.alicebot.ab.Chat chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        FloatingActionButton button = findViewById(R.id.btn_send);
        final EditText message = findViewById(R.id.et_message);
        final ListView lv = findViewById(R.id.listView);
        final ArrayList<ChatMessage> messages = new ArrayList<>();
        final ChatAdapter adapter = new ChatAdapter(this,messages);

        //get the working directory
        MagicStrings.root_path = Environment.getExternalStorageDirectory().toString() + "/chatbox";
        AIMLProcessor.extension =  new PCAIMLProcessorExtension();
        //Assign the AIML files to bot for processing
        bot = new Bot("Bot", MagicStrings.root_path, "chat");
        chat = new org.alicebot.ab.Chat(bot);
        String[] args = null;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!TextUtils.isEmpty(String.valueOf(message.getText()))){
                    messages.add(new ChatMessage(String.valueOf(message.getText()),true));
                    String response = chat.multisentenceRespond(message.getText().toString());
                    messages.add(new ChatMessage(response,false));
                }


                lv.setAdapter(adapter);
                message.setText("");
                lv.setSelection(adapter.getCount() - 1);
            }
        });

    }

    public void onBackPressed() {
        Intent intent = new Intent(Chat.this,Welcome.class);
        startActivity(intent);
    }
}
