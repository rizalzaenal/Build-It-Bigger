package com.rizalzaenal.jokeviewer;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class JokeViewerActivity extends AppCompatActivity {
    TextView jokesText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_viewer);
        jokesText = findViewById(R.id.tv_jokes);
        String joke = getIntent().getStringExtra("EXTRA_JOKES");
        jokesText.setText(joke);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public static Intent newIntent(Context context, String jokes){
        Intent intent = new Intent(context, JokeViewerActivity.class);
        intent.putExtra("EXTRA_JOKES", jokes);
        return intent;
    }
}