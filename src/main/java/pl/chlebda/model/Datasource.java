package pl.chlebda.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Datasource {

    public static final String DB_NAME = "music.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:/Users/damian/SOURCE/sql-practice/" + DB_NAME;

    public static final String TABLE_ALBUMS = "albums";
    public static final String COLUMN_ALBUM_ID = "_id";
    public static final String COLUMN_ALBUM_NAME = "name";
    public static final String COLUMN_ALBUM_ARTIST = "artist";

    public static final String TABLE_ARTISTS = "albums";
    public static final String COLUMN_ARTIST_ID = "_id";
    public static final String COLUMN_ARTIST_NAME = "name";

    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_SONG_TRACK = "track";
    public static final String COLUMN_SONG_TITLE = "title";
    public static final String COLUMN_SONG_ALBUM = "album";

    private Connection conn;

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);

            return true;

        } catch (SQLException e) {
            System.out.println("Couldn't connect to the database: " + e.getMessage());
            return false;
        }

    }

    public void close() {

        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }

    public List<Artist> queryArtists() {

        try (Statement statement = conn.createStatement();
             ResultSet result = statement.executeQuery("SELECT * FROM " + TABLE_ARTISTS)) {

            List<Artist> artists = new ArrayList<>();
            while (result.next()) {
                Artist artist = new Artist();
                artist.setId(result.getInt(COLUMN_ARTIST_ID));
                artist.setName(result.getString(COLUMN_ARTIST_NAME));
                artists.add(artist);

            }
            return artists;

        } catch (SQLException e) {
            System.out.println("Query failde: " + e.getMessage());
            return Collections.emptyList();
        }


    }
}
