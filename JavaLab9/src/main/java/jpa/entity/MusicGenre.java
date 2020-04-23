/**
 * @author: Calin Irina, I2E2
 */

package jpa.entity;

import javax.persistence.*;

@Entity
@Table(name = "MusicGenres", schema = "BDA", catalog = "")
public class MusicGenre {
    private long albumId;
    private String genre;

    public MusicGenre() {
    }

    public MusicGenre(long albumId, String genre) {
        this.albumId = albumId;
        this.genre = genre;
    }

    @Id
    @Column(name = "ALBUM_ID")
    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    @Basic
    @Column(name = "GENRE")
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MusicGenre that = (MusicGenre) o;

        if (albumId != that.albumId) return false;
        if (genre != null ? !genre.equals(that.genre) : that.genre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (albumId ^ (albumId >>> 32));
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        return result;
    }
}
