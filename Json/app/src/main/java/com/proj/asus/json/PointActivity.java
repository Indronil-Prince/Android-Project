package com.proj.asus.json;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class PointActivity extends AppCompatActivity {
    public static TextView data;
    public static String API[]={"http://api.football-data.org/v1/competitions/445/leagueTable",
            "http://api.football-data.org/v1/competitions/455/leagueTable",
            "http://api.football-data.org/v1/competitions/456/leagueTable",
            "http://api.football-data.org/v1/competitions/452/leagueTable",
            "http://api.football-data.org/v1/competitions/450/leagueTable",
            "http://api.football-data.org/v1/competitions/457/leagueTable"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.point_activity);

        data = (TextView) findViewById(R.id.fetcheddata);

        FetchData process = new FetchData();
        process.execute();

    }


    public class FetchData extends AsyncTask<Void, Void, Void> {
        String jsonStr = "";
        String dataParsed = "";
        String singleParsed = "";

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                for(int j=0; j<=5; j++) {
                    URL url = new URL(API[j]);
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
                    if(j==0) dataParsed+="English ";
                    else if(j==1) dataParsed+="Spanish ";
                    else if(j==2) dataParsed+="Italian ";
                    else if(j==3) dataParsed+="German ";
                    else if(j==4) dataParsed+="French ";
                    else if(j==5) dataParsed+="Portuguese ";
                    dataParsed += JObj.getString("leagueCaption") + "\n" + "Pos. Team                   P W L D GD Pts"+ "\n" +
                            "__________________________________________" + "\n";


                    JSONArray JContcts = (JSONArray) JObj.get("standing");

                    if (JContcts.length() == 0) dataParsed = "No Live Match Going On !!!";
                    else {
                        for (int i = 0; i < JContcts.length(); i++) {
                            JSONObject match = (JSONObject) JContcts.get(i);
                            singleParsed = match.get("position") + ". " +
                                    match.get("teamName") + "       " +
                                    match.get("playedGames") + " " +
                                    match.get("wins") + " " +
                                    match.get("losses") + " " +
                                    match.get("draws") + " " +
                                    match.get("goalDifference") + " " +
                                    match.get("points") + " " ;

                            dataParsed = dataParsed + singleParsed + "\n";
                        }

                    }
                        dataParsed+="\n" + "\n";
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
            PointActivity.data.setText(this.dataParsed);

        }

    }

}
