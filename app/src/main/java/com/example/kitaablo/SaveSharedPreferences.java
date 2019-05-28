package com.example.kitaablo;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveSharedPreferences {

    static final String PREF_EMAIL = "email";
    static SharedPreferences getSharedPreferences(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setPrefEmail(Context context, String email) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(PREF_EMAIL,email);
        editor.commit();
    }

    public static String getPrefEmail(Context context) {
        return getSharedPreferences(context).getString(PREF_EMAIL,"");
    }

}
