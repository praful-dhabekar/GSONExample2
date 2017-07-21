package com.praful.gsonexample;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    String jsonstring;
    RecyclerView playersRV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        playersRV = (RecyclerView)findViewById(R.id.playersRV);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(MainActivity.this);
        playersRV.setLayoutManager(manager);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Fetching the player detail..", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                NetworkTask task = new NetworkTask();
                task.execute();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class NetworkTask extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {

            /* with the help OkHttpClient we can connect to internet.
             * To create OkHttpClient we need to add dependencies in
             * gradle app file from http://square.github.io/okhttp/ */

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url("https://madhu-e399d.firebaseapp.com/data.json").build();

            try {
                Response response = client.newCall(request).execute();
                jsonstring = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return jsonstring;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            String json = (String)o;
            Gson gson = new Gson();
            APIResponse apiResponse = gson.fromJson(json,APIResponse.class);

            PlayerAdapter playerAdapter = new PlayerAdapter(MainActivity.this,apiResponse);
            playersRV.setAdapter(playerAdapter);
        }
    }
}
