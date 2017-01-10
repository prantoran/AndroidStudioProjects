package com.prantoran.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //SendMessage() method stub
    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        // Do something in response to button
        //read the contents of the text field and deliver that text to another activity
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        //new Intent(Context,Class);
        //A Context as its first parameter (this is used because the Activity class is a subclass of Context)
        //Intent is used to bind DisplayMessageActivity with this Activity at runtime
        //The Class of the app component to which the system should deliver the Intent (in this case, the activity that should be started).
        EditText editText = (EditText)findViewById(R.id.edit_message);
        //R = Resource, R.id = id from resource
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivity(intent);
    }
}
