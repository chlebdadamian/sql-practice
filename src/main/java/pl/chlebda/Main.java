package pl.chlebda;

import pl.chlebda.model.Artist;
import pl.chlebda.model.Datasource;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        Datasource datasource = new Datasource();
        if (!datasource.open()) {
            System.out.println("Cannot open datasource");
            return;
        }

        List<Artist> artists = datasource.queryArtists();
        if (artists == null || artists.isEmpty()) {

            System.out.println("Cannot open datasource");
            return;

        }

        for (Artist artist : artists) {

            System.out.println("ID = " + artist.getId() + ", Name = " + artist.getName());

        }
        datasource.close();

    }

}
