package com.prantoran.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        Intent intent = getIntent(); //binding/handshake with MainActivity.java's Intent
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE); //dereferencing the key-value pair
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
        //The ViewGroup class contains the addView() method
        layout.addView(textView);
    }
}
