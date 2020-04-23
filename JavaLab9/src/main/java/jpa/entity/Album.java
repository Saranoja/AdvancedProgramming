/**
 * @author: Calin Irina, I2E2
 */

package jpa.entity;

import javax.persistence.*;

@NamedQueries(
        {@NamedQuery(name = "album.findByName", query = "SELECT a FROM Album a where a.name = :inputname"),
                @NamedQuery(name = "album.findByArtist", query = "SELECT a FROM Album a where a.artistId = :inputId"),
                @NamedQuery(name = "album.getAllAlbums", query = "SELECT a FROM Album a"),
                @NamedQuery(name = "album.getGenre", query = "SELECT g.genre FROM MusicGenre g where g.albumId = :inputId"),
                @NamedQuery(name = "album.getArtist", query = "SELECT a.name FROM Artist a where a.id = :inputId")}
)


@Entity
@Table(name = "ALBUMS", schema = "BDA", catalog = "")
public class Album {
    private long id;
    private String name;
    private long artistId;
    private Long releaseYear;

    public Album() {
    }

    public Album(long id, String name, long artist, long releaseYear) {
        this.name = name;
        this.artistId = artist;
        this.releaseYear = releaseYear;
        this.id = id;
    }

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "ARTIST_ID")
    public long getArtistId() {
        return artistId;
    }

    public void setArtistId(long artistId) {
        this.artistId = artistId;
    }

    @Basic
    @Column(name = "RELEASE_YEAR")
    public Long getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Long releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Album that = (Album) o;

        if (id != that.id) return false;
        if (artistId != that.artistId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (releaseYear != null ? !releaseYear.equals(that.releaseYear) : that.releaseYear != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (artistId ^ (artistId >>> 32));
        result = 31 * result + (releaseYear != null ? releaseYear.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Albums{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", artistId=" + artistId +
                ", releaseYear=" + releaseYear +
                '}';
    }
}
