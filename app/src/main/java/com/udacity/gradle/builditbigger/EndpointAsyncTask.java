package com.udacity.gradle.builditbigger;

/**
 * Created by rajat on 10/29/2016.
 */

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.rajat.builditbigger.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

public class EndpointAsyncTask extends AsyncTask<Void, Void, String> {

    @SuppressWarnings("unused")
    public static String LOG_TAG = EndpointAsyncTask.class.getSimpleName();
    private  Listener mListener = null;
    private static MyApi myApiService = null;

    private MainActivityFragment mainActivityFragment;
    private Context context;

    public interface Listener {
        void onJokeLoaded(String joke);
    }

    public EndpointAsyncTask(Listener listener) {
        mListener = listener;
    }

    @Override
    protected String doInBackground(Void... params) {
        if (myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?>
                                                       abstractGoogleClientRequest)
                                throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }

        try {
            return myApiService.passJoke().execute().getData();
        } catch (IOException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            return "";
        }
    }

    @Override
    protected void onPostExecute(String joke) {
            mListener.onJokeLoaded(joke);

    }
}