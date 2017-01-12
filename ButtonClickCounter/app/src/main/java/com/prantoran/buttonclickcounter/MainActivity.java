package com.prantoran.buttonclickcounter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private EditText userInput; //can store reference to an EditText widget
    private Button button;
    private TextView textView;
    private int numTimesClicked = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userInput = (EditText)findViewById(R.id.editText);
        button = (Button)findViewById(R.id.button);
        textView = (TextView)findViewById(R.id.textView);
        textView.setText("");  //clear initial text before adding new text

        textView.setMovementMethod(new ScrollingMovementMethod()); //adding scrolling feature to textView widget
        //this works because we added a vertical scroller in the layout

        //This is an example of Event-Driven Programming Paradigm
        View.OnClickListener ourOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) { //View is ancestor of Widget
                numTimesClicked = numTimesClicked + 1;

                String result = "The button got tapped "+numTimesClicked + " time";
                if(numTimesClicked>1){
                    result+="s";
                    result  = "\n"+result;
                }
                textView.append(result);
            }
        };

        button.setOnClickListener(ourOnClickListener);

    }
}
