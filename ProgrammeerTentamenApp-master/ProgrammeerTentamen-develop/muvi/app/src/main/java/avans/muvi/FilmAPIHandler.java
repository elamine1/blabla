package avans.muvi;

/**
 * Created by saman on 18-6-2017.
 */

import android.os.AsyncTask;
import android.util.Log;

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
import java.net.URLConnection;


public class FilmAPIHandler extends AsyncTask<String, Void, String> {

    //call back
    private OnFilmAvailable listener;


    private static final String TAG = FilmAPIHandler.class.getSimpleName();

    public FilmAPIHandler(OnFilmAvailable listener){
        this.listener = listener;}

    @Override
    protected String doInBackground(String... params) {

        InputStream inputStream = null;
        int responsCode = -1;

        String response = "";

        for(String url : params) {
            Log.i(TAG, "doInBackground " + url);
        }

        try {

            URL url = new URL(params [0]);
            URLConnection urlConnection = url.openConnection();

            if (!(urlConnection instanceof HttpURLConnection)) {
                // Url
                return null;
            }

            HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;
            httpConnection.setAllowUserInteraction(false);
            httpConnection.setInstanceFollowRedirects(true);
            httpConnection.setRequestMethod("GET");
            httpConnection.connect();

            responsCode = httpConnection.getResponseCode();

            if (responsCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpConnection.getInputStream();
                response = getStringFromInputStream(inputStream);
                // Log.i(TAG, "doInBackground response = " + response);
            }
        } catch (MalformedURLException e) {
            Log.e(TAG, "doInBackground MalformedURLEx " + e.getLocalizedMessage());
            return null;
        } catch (IOException e) {
            Log.e(TAG, "doInBackground IOException " + e.getLocalizedMessage());
            return null;
        }

        return response;
    }

    protected void onPostExecute(String response) {

        Log.i(TAG, "onPostExecute " + response);

        // parse JSON and inform caller
        JSONObject jsonObject;

        try {
            // Top level json object
            jsonObject = new JSONObject(response);

            // verkrijgen van alle films, alle resultaten
            JSONArray films = jsonObject.getJSONArray("results");

            for(int idx = 0; idx < films.length(); idx++) {
                // array level objects and get films

                Film film = new Film();
                String titel = films.getJSONObject(idx).getString("title");
                film.setFilmTitel(titel);
                String filmId = films.getJSONObject(idx).getString("id");
                film.setFilmId(filmId);
                String waardering = films.getJSONObject(idx).getString("vote_average");
                film.setWaardering(waardering);
                String info = films.getJSONObject(idx).getString("overview");
                film.setInfo(info);
                String releasedatum = films.getJSONObject(idx).getString("release_date");
                film.setReleasedatum(releasedatum);
                String specialfeatures = films.getJSONObject(idx).getString("special_features");
                film.setSpecialfeatures(specialfeatures);

                Log.i(TAG, "Album gevonden: " + titel + ", " + filmId);


                // call back met nieuwe data over de films

                listener.onFilmAvailable(film);

            }
        } catch( JSONException ex) {
            Log.e(TAG, "onPostExecute JSONException " + ex.getLocalizedMessage());
        }
    }


    //
// convert InputStream to String
//
    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }

    // call back interface
    public interface OnFilmAvailable {
        void onFilmAvailable(Film film);
    }
}
