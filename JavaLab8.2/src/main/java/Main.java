/**
 * @author: Calin Irina, I2E2
 */

// Maven project, user Java Faker Library to generate fake data to populate the tables and Apache FreeMarker to create
// a HTML Formatted report containing the resulted artists chart

import com.github.javafaker.Faker;
import controllers.AlbumController;
import controllers.ArtistController;
import freemarker.template.*;
import models.Artist;
import models.Chart;
import db.Database;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.io.File;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {

        Faker faker = new Faker();
        Database db = Database.getInstance();
        ArtistController artistController = new ArtistController(db);
        AlbumController albumController = new AlbumController(db);
        List<String> names = new ArrayList<>();

        //Generating fake data for my tables

        /* try {
            //insert in the database fake artists
            for (int i = 0; i < 100; i++) {
                artistController.create(faker.artist().name(), faker.country().name());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //create the artists' id array for which we will generate random albums
        List<Integer> artistIds = new ArrayList<>();
        try {
            for (String name : names) {
                artistIds.add(artistController.getId(name));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //for each ID, we generate a random album
        try {
            for (Integer artistID : artistIds) {
                albumController.create(faker.music().genre() + " " + faker.color().name() + " " + faker.ancient().hero(),
                        artistID, (int) (Math.random() * 120 + 1900));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } */

        //Generated a HTML report using FreeMarker

        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the output path for your HTML report:");
        String outputPath = scanner.next();
        FileWriter fw = new FileWriter(outputPath + "/HTMLFormattedReport.html");
        BufferedWriter out = new BufferedWriter(fw);
        File dir = new File("C:/Users/Irina/Desktop/AdvancedProgramming/JavaLab8.2/src/main/java/templates/");

        //Configure FreeMarker
        Configuration configuration = new Configuration();
        configuration.setDirectoryForTemplateLoading(dir);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        //Process template
        Template template = configuration.getTemplate("helloworld.ftl");
        Map<String, Object> input = new HashMap<String, Object>();

        Chart chart = new Chart(db);

        chart.updateChartToLive();

        //Will loop through all the artists in the template

        List<Artist> allArtists = chart.getArtistList();
        input.put("artist", allArtists);

        try {
            template.process(input, out);
        } catch (TemplateException te) {
            System.out.println("Template exception occured");
        } finally {
            out.flush();
            out.close();
        }

        System.out.println("A report with the name HTMLFormattedReport.html was created at " + outputPath + "/HTMLFormattedReport.html");
        chart.printTop();
    }

}


