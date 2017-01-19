package com.prantoran.buttonclickcounter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private EditText userInput; //can store reference to an EditText widget
    //private Button button;
    private TextView textView;
    private int numTimesClicked = 0;

    //A live template to define TAG: type logt
    private static final String TAG = "MainActivity";
    private final String TEXT_CONTENT = "TextContents";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //a Live Template used for debugging:type in logd and the template appears
        Log.d(TAG, "onCreate: in");


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userInput = (EditText) findViewById(R.id.editText);
        userInput.setText("");           //clear the initial text in textbox
        Button button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);
        textView.setText("");  //clear initial text before adding new text

        textView.setMovementMethod(new ScrollingMovementMethod()); //adding scrolling feature to textView widget
        //this works because we added a vertical scroller in the layout

        //This is an example of Event-Driven Programming Paradigm
        View.OnClickListener ourOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) { //View is ancestor of Widget
                numTimesClicked = numTimesClicked + 1;

                String result = "The button got tapped " + numTimesClicked + " time";
                if (numTimesClicked > 1) {
                    result += "s";
                    result = "\n" + result;
                }
                textView.append(result);


                String result2 = userInput.getText().toString(); //method chaining
                result2 = "\n" + result2;
                textView.append(result2);
                userInput.setText("");
            }
        };
        if (button != null) {
            button.setOnClickListener(ourOnClickListener);
        }

        //Live Template
        Log.d(TAG, "onCreate: out");

    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: in");
        super.onStart();

        Log.d(TAG, "onStart: out");

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "onRestoreInstanceState: in");

        super.onRestoreInstanceState(savedInstanceState);

        String savedString = savedInstanceState.getString(TEXT_CONTENT); //accessing key-value pair of bundle savedInstanceState
        textView.setText(savedString);
        Log.d(TAG, "onRestoreInstanceState: out");

    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "onRestart: in");

        super.onRestart();
        Log.d(TAG, "onRestart: out");
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: in");
        super.onPause();
        Log.d(TAG, "onPause: out");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: in");

        outState.putString(TEXT_CONTENT, textView.getText().toString()); //bundle stores a set of key-value pairs

        super.onSaveInstanceState(outState);


        Log.d(TAG, "onSaveInstanceState: out");
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: in");
        super.onResume();
        Log.d(TAG, "onResume: out");
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: in");
        super.onStop();
        Log.d(TAG, "onStop: out");
    }
}
