package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.jokefactory.JokeActivity;

public class MainActivityFragment extends Fragment {

    Button jokeButton;

    public MainActivityFragment() {
        // Required empty public constructor
    }


    public boolean testFlag = false;

    ProgressBar loadingBar = null;

    public String fetchedJoke = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        jokeButton = (Button) root.findViewById(R.id.jokeButton);
        jokeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                new EndpointAsyncTask(new EndpointAsyncTask.Listener() {

                    @Override
                    public void onJokeLoaded(String joke) {
                        Intent intent = new Intent(getContext(), JokeActivity.class);
                        intent.putExtra(JokeActivity.EXTRA_JOKE, joke);
                        startActivity(intent);
                    }
                }).execute();
            }
        });

        return root;

    }

}
