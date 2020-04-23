/**
 * @author: Calin Irina, I2E2
 */

package app;

import jpa.entity.Album;
import jpa.entity.Artist;
import jpa.entity.MusicGenre;
import jpa.repo.AlbumRepository;
import jpa.repo.ArtistRepository;
import jpa.repo.GenresRepository;

import com.github.javafaker.Faker;

import java.util.*;

public class BonusManager {
    private static ArtistRepository artistRepository = new ArtistRepository();
    private static AlbumRepository albumRepository = new AlbumRepository();
    private static GenresRepository genresRepository = new GenresRepository();

    public static void main(String[] args) {
        //insertAlbums();
        //insertGenres();
        System.out.println("Matching: ");
        for (Album album : getLargestSet())
            System.out.println(album.getName() + " - genre: " + albumRepository.getGenre(album.getId()) +
                    " - by artist: " + album.getArtistId());
    }

    public static void insertAlbums() {
        Faker faker = new Faker();
        Random random = new Random();
        List<Artist> allArtists = ArtistRepository.getAllArtists();
        System.out.println("Total artists: " + allArtists.size());
        Album album = new Album();
        long tempId = 1000;
        for (Artist artist : allArtists) {
            albumRepository.create(new Album(tempId, faker.color().name() + " " + faker.music().instrument(), artist.getId(),
                    random.nextInt(20) + 2000));
            ++tempId;
        }
    }

    public static void insertGenres() {
        Faker faker = new Faker();
        List<Album> allAlbums = AlbumRepository.getAllAlbums();
        for (Album album : allAlbums)
            genresRepository.create(new MusicGenre(album.getId(), faker.music().genre()));
    }

    public static List<Album> getLargestSet() {
        List<Album> albums = AlbumRepository.getAllAlbums();
        List<Album> albumsSubset = new ArrayList<>();
        int albumsNr = albums.size();
        /* Map<String, Album> genresMap = new HashMap<>();
        Map<String, Album> artistsMap = new HashMap<>();

        for (Album album : albums) {
            genresMap.put(albumRepository.getGenre(album.getId()).toString(), album);
            artistsMap.put(albumRepository.getArtist(album.getId()).toString(), album);
        } */

        while (albumsNr > 0) {
            Album album = albums.get(0);
            albumsSubset.add(album);
            albums.removeIf(thisAlbum -> thisAlbum.getArtistId() == album.getArtistId() || albumRepository.getGenre(thisAlbum.getId())
                    .equals(albumRepository.getGenre(album.getId())));
            albumsNr = albums.size();
        }
        return albumsSubset;
    }
}
