package com.udacity.gradle.builditbigger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.rizalzaenal.jokeviewer.JokeViewerActivity;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import java.io.IOException;

public class EndpointAsyncTask extends AsyncTask<Context, Void, String> {
    private MyApi myApiService = null;
    private Context context;

    @Override
    protected String doInBackground(Context... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
              new AndroidJsonFactory(), null)
              // options for running against local devappserver
              // - 10.0.2.2 is localhost's IP address in Android emulator
              // - turn off compression when running against local devappserver
              .setRootUrl("http://10.0.2.2:8080/_ah/api/")
              .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                  @Override
                  public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws
                    IOException {
                      abstractGoogleClientRequest.setDisableGZipContent(true);
                  }
              });
            // end options for devappserver

            myApiService = builder.build();
        }

        context = params[0];
        //String name = params[0].second;

        try {
            return myApiService.sayHi().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        //Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        startJokeViewerActivity(result);
    }

    private void startJokeViewerActivity(String joke) {
        Intent intent = JokeViewerActivity.newIntent(context, joke);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}