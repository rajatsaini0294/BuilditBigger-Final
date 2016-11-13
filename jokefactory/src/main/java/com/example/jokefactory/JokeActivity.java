package com.example.jokefactory;

/**
 * Created by rajat on 10/28/2016.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static final String EXTRA_JOKE = "_JOKE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        String passedJoke = null;
        TextView textView = (TextView) findViewById(R.id.joke_view);

        Intent intent = getIntent();
        passedJoke = intent.getStringExtra(EXTRA_JOKE);

        if (passedJoke != null) {
            textView.setText(passedJoke);
        } else {
            textView.setText("No Jokes!!! Keep Laughing..");
        }
    }
}