/**
 * @author: Calin Irina, I2E2
 */

package entity;

import javax.persistence.*;

@Entity
@Table(name = "CHART", schema = "BDA", catalog = "")
public class Chart {
    private long artistId;
    private long popularity;

    @Id
    @Column(name = "ARTIST_ID")
    public long getArtistId() {
        return artistId;
    }

    public void setArtistId(long artistId) {
        this.artistId = artistId;
    }

    @Basic
    @Column(name = "POPULARITY")
    public long getPopularity() {
        return popularity;
    }

    public void setPopularity(long popularity) {
        this.popularity = popularity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chart that = (Chart) o;

        if (artistId != that.artistId) return false;
        if (popularity != that.popularity) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (artistId ^ (artistId >>> 32));
        result = 31 * result + (int) (popularity ^ (popularity >>> 32));
        return result;
    }
}
