package com.example.sharedprefdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final String SHARED_PREF_NAME = "username";
    private static final String KEY_NAME = "key_username";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        //write to shared pref
//       sharedPreferences.edit().putString(KEY_NAME,"Aman").apply();

        //read from shared pref
        String name = sharedPreferences.getString(KEY_NAME, "NA");
        Log.i(TAG, "onCreate: " +name);

        ArrayList<String> names = new ArrayList<>(Arrays.asList("Aman", "Anmol", "Anhad"));
//        sharedPreferences.edit().putStringSet("names", new HashSet<>(names)).apply();

        Set<String> receivedNames = sharedPreferences.getStringSet("names", new HashSet<>(names));
        Log.i(TAG, "onCreate: " +receivedNames.toString());

        try {
            sharedPreferences.edit().putString("names_serial",ObjectSerializer.serialize(names)).apply();
//            Log.i(TAG, "onCreate: " +ObjectSerializer.serialize(names));
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> namesDeserialized = new ArrayList<>();
        String receivedData = sharedPreferences.getString("names-serial", null);
        try {
            namesDeserialized = (ArrayList) ObjectSerializer.deserialize(receivedData);

        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "onCreate: " +namesDeserialized);
    }
}