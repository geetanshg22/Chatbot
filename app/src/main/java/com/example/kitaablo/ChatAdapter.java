package com.example.kitaablo;

import android.content.ContentUris;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatAdapter extends ArrayAdapter<ChatMessage> {

    private static final int MY_MESSAGE = 1;
    private static final int OTHER_MESSAGE = 2;

    public ChatAdapter(Context context, ArrayList<ChatMessage> message) {
        super(context, 0, message);
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage item = getItem(position);

        if (item.getmIsMe()) return MY_MESSAGE;
        else return OTHER_MESSAGE;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int getView = getItemViewType(position);

        if (getView == MY_MESSAGE) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_human, parent, false);
            TextView humanMessage = convertView.findViewById(R.id.humanMessage);
            humanMessage.setText(getItem(position).getmMessage());
        } else {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_bot, parent, false);
            TextView botMessage = convertView.findViewById(R.id.botMessage);
            botMessage.setText(getItem(position).getmMessage());
        }
        return convertView;
    }

}
