package com.urja.carclinics.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by BAPI1 on 12/31/2016.
 */

public class SharedPreferenceUtili {
    private static SharedPreferenceUtili instance = null;
    private SharedPreferenceUtili() {}

    public static SharedPreferenceUtili getInstance() {
        if(instance == null) {
            instance = new SharedPreferenceUtili();
        }
        return instance;
    }

    public void setDataInSharedPreference(Context context, Map<String, String> datas){
        if (datas != null){
            SharedPreferences.Editor editor = context.getSharedPreferences(AppConstants.SHARED_PREF_NAME, MODE_PRIVATE).edit();
            for (Map.Entry<String, String> entry : datas.entrySet()) {
                System.out.println(entry.getKey() + "/" + entry.getValue());
                editor.putString(entry.getKey(), entry.getValue());
            }
            editor.commit();
        }
    }

    public String getDataFromSharedPreference(Context context, String key){
        SharedPreferences prefs = context.getSharedPreferences(AppConstants.SHARED_PREF_NAME, MODE_PRIVATE);
        return prefs.getString("key", "");
    }

}
