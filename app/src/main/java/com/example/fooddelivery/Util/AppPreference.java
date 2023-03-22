package com.example.fooddelivery.Util;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreference {
    private static AppPreference appPreference;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public AppPreference(Context context){
        sharedPreferences = context.getSharedPreferences("MyCartData", Context.MODE_PRIVATE);
    }

    public static AppPreference getInstance(Context context){
        if(appPreference == null){
            appPreference = new AppPreference(context.getApplicationContext());
        }
        return appPreference;
    }

    public void putString(String key, String val){
        editor = sharedPreferences.edit();
        editor.putString(key, val);
        editor.commit();
    }

    public String getString(String key){
        return sharedPreferences.getString(key, "");
    }
}
