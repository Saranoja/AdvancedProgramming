/**
 * @author: Calin Irina, I2E2
 */


import app.BonusManager;
import jpa.entity.Album;
import jpa.repo.AlbumRepository;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class AlgorithmTest {
    //Test the size of the subset, see if it matches previous estimation
    @Test
    public void testResultLength() {
        BonusManager algorithmManager = new BonusManager();
        List<Album> albumsSubset = algorithmManager.getLargestSet();
        Assert.assertEquals("Subset size: valid", 18, albumsSubset.size());
    }

    //Artist 9392 only has one album with a unique genre, so it should be included in our subset
    @Test
    public void testEnrique() {
        BonusManager algorithmManager = new BonusManager();
        List<Album> albumsSubset = algorithmManager.getLargestSet();
        Assert.assertTrue("Enrique tested: valid", albumsSubset.contains(AlbumRepository.findByArtist((long) 9392).get(0)));
    }

}
