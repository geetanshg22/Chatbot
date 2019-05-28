package com.example.kitaablo;

public class ChatMessage {
        private String mMessage;
        private boolean mIsMe ;

        public ChatMessage(String Message, Boolean isMe){
            mMessage = Message;
            mIsMe = isMe;
        }

        public String getmMessage(){
            return mMessage;
        }

        public Boolean getmIsMe(){
            return mIsMe;
        }
}
