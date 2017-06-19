package avans.muvi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


/**
 * Created by saman on 16-6-2017.
 */

public class DetailActivity extends AppCompatActivity {


    private TextView titel = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        Intent intent = getIntent();
        Film film = (Film) intent.getSerializableExtra("FILM");


        TextView info = (TextView) findViewById(R.id.infoTV);
        info.setText(film.getInfo());

        TextView rating = (TextView) findViewById(R.id.ratingTV);
        rating.setText("Ratings: " + film.getWaardering());

        TextView releasedatum = (TextView) findViewById(R.id.releasedatumTV);
        releasedatum.setText("Releasedatum: " + film.getReleasedatum());

        TextView specialfeatures = (TextView) findViewById(R.id.specialfeaturesTV);
        specialfeatures.setText("Specialfeatures: " + film.getSpecialfeatures());

        titel = (TextView) findViewById(R.id.titelTV);
        titel.setText(film.getFilmTitel());


    }
}
