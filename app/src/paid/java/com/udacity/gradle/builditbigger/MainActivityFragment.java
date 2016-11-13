package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.jokefactory.JokeActivity;
import com.udacity.gradle.builditbigger.EndpointAsyncTask;
import com.udacity.gradle.builditbigger.R;

public class MainActivityFragment extends Fragment {

    Button jokeButton;

    public MainActivityFragment() {
        // Required empty public constructor
    }

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
