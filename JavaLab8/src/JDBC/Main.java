/**
 * @author: Calin Irina, I2E2
 */

package JDBC;
//Business object from DAO

public class Main {
    public static void main(String[] args) {

        Database db = Database.getInstance();
        ArtistController artistController = new ArtistController(db);
        AlbumController albumController = new AlbumController(db);

        /* artistController.create("Metallica","USA");
        artistController.create("Arctic Monkeys","UK");
        artistController.create("Rammstein","Germany");
        artistController.create("Pink Floyd","UK");
        albumController.create("Dark side of the moon",6, 1973);
        albumController.create("Master of puppets",3, 1986); */

        artistController.findByName("Eminem");
        artistController.findByName("Metallica");
        albumController.findByArtist(3);
        albumController.findByArtist(6);
    }
}
