package com.proj.asus.json;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class GermanyActivity extends AppCompatActivity {
    public static TextView data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.germany_activity);

        data = (TextView) findViewById(R.id.fetcheddata);

        FetchData process = new FetchData();
        process.execute();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(GermanyActivity.this, GermanyActivity.class);
                finishActivity();
                startActivity(intent);
            }
        }, 100000);
    }

    private void finishActivity() {
        Intent intent = new Intent(GermanyActivity.this, Activity1.class);
        startActivity(intent);
    }


    public class FetchData extends AsyncTask<Void, Void, Void> {
        String jsonStr = "";
        String dataParsed = "";
        String singleParsed = "";

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL("http://livescore-api.com/api-client/scores/live.json?key=957r1BFvd03yNIm1&secret=NPqlofr2AmTS5IXfN5HOHmkUnnta6PcT&country=1");  //string converted to URL type
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer StrBfr = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null)
                {
                    StrBfr.append(line);
                }

                jsonStr = StrBfr.toString();

                JSONObject JObj = new JSONObject(jsonStr);

                JSONObject IndividualObj = JObj.getJSONObject("data");

                JSONArray JContcts = IndividualObj.getJSONArray("match");

                if(JContcts.length()==0) dataParsed="No Live Match Going On !!!";
                else{
                    for (int i = 0; i < JContcts.length(); i++) {

                        JSONObject match = JContcts.getJSONObject(i);
                        String LeagueName = match.getString("league_name");
                        String HomeName = match.getString("home_name");
                        String Score = match.getString("score");
                        String AwayName = match.getString("away_name");
                        String Status = match.getString("status");
                        String Time;
                        if(Status=="IN PLAY" || Status=="ADDED TIME") Time = "Time: " + match.getString("time")+" minutes";
                        else if(Status=="NOT STARTED") Time = "Time: " + match.getString("time")+" GMT";
                        else Time = "Time: " + match.getString("time");

                        singleParsed = LeagueName + "\n" + HomeName + " (" + Score + ") " +
                                AwayName + "\n" + Status + "\n" + Time + "\n" + "___________________________________________";

                        dataParsed = dataParsed + singleParsed + "\n";
                    }

                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            GermanyActivity.data.setText(this.dataParsed);

        }

    }

}
