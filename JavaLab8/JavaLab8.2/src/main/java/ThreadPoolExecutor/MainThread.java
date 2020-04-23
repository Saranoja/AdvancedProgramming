package ThreadPoolExecutor;/**
 * @author: Calin Irina, I2E2
 */

import com.github.javafaker.Faker;

import controllers.AlbumController;
import controllers.ArtistController;
import controllers.ChartController;
import db.ConnectionPool;
import db.Database;

import java.sql.Connection;

public class MainThread implements Runnable {
    private ConnectionPool cp;
    private ArtistController artistController;
    private AlbumController albumController;
    private ChartController chartController;

    public MainThread(ConnectionPool cp) {
        this.cp = cp;
        artistController = new ArtistController(cp);
        albumController = new AlbumController(cp);
        chartController = new ChartController(cp);
    }

    @Override
    public void run() {
        Faker faker = new Faker();
        //insert random data -  artists and albums
        try {
            for (int i = 0; i < 50; i++) {
                artistController.CPcreate(faker.artist().name(), faker.country().name());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            for (int i = 0; i < 50; i++) {
                albumController.CPcreate(faker.music().genre() + " " + faker.color().name() + " " + faker.ancient().hero(), i, (int) (Math.random() * 120 + 1900));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* try {
            chartController.updateChart();
        } catch (Exception e) {
            e.printStackTrace();
        } */
    }

}
