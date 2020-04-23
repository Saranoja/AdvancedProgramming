package models; /**
 * @author: Calin Irina, I2E2
 */

import controllers.ChartController;
import db.Database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Chart {

    private List<Artist> artistList = new ArrayList<>();
    public Database db;

    public Chart(Database db) {
        this.db = db;
    }

    public void printTop() {
        System.out.println("Top Artists in the chart:");
        int index = 0;
        for (Artist artist : artistList) {
            index++;
            System.out.println(index + ". " + artist.getName() + " " + artist.getPopularity());
        }
    }

    public void updateChartToLive() throws SQLException, ClassNotFoundException {
        ChartController chartController = new ChartController(db);
        chartController.updateChart();
        artistList.removeAll(artistList);
        artistList.addAll(chartController.getChart());
    }

    public List<Artist> getArtistList() {
        return artistList;
    }
}