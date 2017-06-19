package avans.muvi;

/**
 * Created by saman on 18-6-2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class FilmArrayAdapter extends ArrayAdapter<Film> {

    public FilmArrayAdapter(Context context, ArrayList<Film> films) {
        super(context, android.R.layout.simple_list_item_1 , films);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Film film = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.film_listview, parent, false);
        }

        TextView filmTitel = (TextView) convertView.findViewById(R.id.filmTitelTV);
        filmTitel.setText(film.getFilmTitel());

        TextView waardering = (TextView) convertView.findViewById(R.id.filmWaarderingTV);
        waardering.setText("Kijkersgemiddelde: " + film.getWaardering());

        return convertView;
    }

}