package avans.muvi;

/**
 * Created by new user on 19-6-2017.
 */

public class Film {



    String filmTitel;
    String filmId;
    String waardering;
    String info;
    String releasedatum;
    String specialfeatures;


    public void setFilmTitel(String filmTitel) {
        this.filmTitel = filmTitel;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }

    public void setWaardering(String waardering) {
        this.waardering = waardering;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setReleasedatum(String releasedatum) {
        this.releasedatum = releasedatum;
    }

    public void setSpecialfeatures(String specialfeatures) {
        this.specialfeatures = specialfeatures;
    }





    public String getFilmTitel() {
        return filmTitel;
    }

    public String getFilmId() {
        return filmId;
    }

    public String getWaardering() {
        return waardering;
    }

    public String getInfo() {
        return info;
    }

    public String getReleasedatum() {
        return releasedatum;
    }

    public String getSpecialfeatures() {
        return specialfeatures;
    }

    @Override
    public String toString() {
        return "Film{" +
                "filmTitel='" + filmTitel + '\'' +
                ", filmId='" + filmId + '\'' +
                ", waardering='" + waardering + '\'' +
                ", info='" + info + '\'' +
                ", releasedatum='" + releasedatum + '\'' +
                ", specialfeatures='" + specialfeatures + '\'' +
                '}';
    }

}


