package avans.muvi;

/**
 * Created by saman on 18-6-2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;




public class FilmActivity extends AppCompatActivity implements FilmAPIHandler.OnFilmAvailable, View.OnClickListener, ListView.OnItemClickListener {


    private ArrayList<Film> mFilms = new ArrayList<Film>();
    private ListView mFilmListView = null;
    private FilmArrayAdapter mFilmArrayAdapter = null;
    private FilmAPIHandler mFilmAPIHandler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.film_list);

        Intent intent = getIntent();

        mFilmArrayAdapter = new FilmArrayAdapter(this, mFilms);
        mFilmListView = (ListView) findViewById(R.id.filmLV);

        mFilmListView.setAdapter(mFilmArrayAdapter);

        mFilmListView.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Film film = mFilms.get(position);
        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
        intent.putExtra("FILM", (Serializable) film);
        startActivity(intent);

    }


    @Override
    public void onClick(View v) {
        mFilms.clear();
        //get current year
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);

        //Log.i(TAG, "onClick(...)");


            FilmAPIHandler getFilm = new FilmAPIHandler(this);
            String[] urls = new String[]{"https://muvi-server.herokuapp.com/api/v1/films"};
            getFilm.execute(urls);
        }



    @Override
    public void onFilmAvailable(Film film) {
        mFilms.add(film);
        //Log.i(TAG, "Film toegevoegd" + film.toString() + ")");
        mFilmArrayAdapter.notifyDataSetChanged();
    }
}
