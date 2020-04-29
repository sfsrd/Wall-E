package com.sonya.wall_e.api.request;

import android.os.AsyncTask;

import com.sonya.wall_e.constants.EspClientConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MoveRequest extends AsyncTask<String, Object, String> {

    @Override
    protected String doInBackground(String... directions) {

        try {
            for (String direction: directions) {
                URL obj = new URL(EspClientConstants.BASE_URL + "move/" + direction);

                HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

                connection.setRequestMethod("GET");

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = bufferedReader.readLine()) != null) {
                    response.append(inputLine);
                }
                bufferedReader.close();

                return response.toString();
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
        return null;
    }
}
