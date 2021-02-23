package com.example.guessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Switch toggle = findViewById(R.id.switch1);
        Button back = findViewById(R.id.save);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        toggle.setChecked(sharedPref.getBoolean("sound", true));

        SharedPreferences.Editor editor = sharedPref.edit();
        toggle.setOnCheckedChangeListener((CompoundButton view , boolean isChecked)->{
            editor.putBoolean("sound", isChecked);
        editor.apply();
    }
        );
        back.setOnClickListener(v->{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

    }
}