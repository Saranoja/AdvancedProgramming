package logics;

import Gomoku.Player;
import freemarker.template.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//class used to generate a html report of the game
//each time a move is submitted, a new move is created and added to the moves array
//at the end of the game, we call putMoves(...) and flushAll()

public class Report {
    private File dir;
    private Configuration configuration;
    private Map<String, Object> input;
    private Template template;
    private BufferedWriter out;

    public Report() {
        try {
            FileWriter fw = new FileWriter("Gomoku.html");
            out = new BufferedWriter(fw);
            dir = new File("C:/Users/Irina/Desktop/AdvancedProgramming/JavaLab10/Server10.2/src/main/java/templates/");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void configure() throws IOException {
        configuration = new Configuration();
        configuration.setDirectoryForTemplateLoading(dir);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }

    public void processTemplate() throws IOException {
        template = configuration.getTemplate("helloworld.ftl");
        template.setAutoFlush(true);
        input = new HashMap<String, Object>();
    }

    public void putMoves(ArrayList<Move> moves) {
        input.put("allMoves", moves);
    }

    public void putWinner(Player player) {
        input.put("player", player);
    }

    public void flushAll() {
        try {
            template.process(input, out);
        } catch (TemplateException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeStream() throws IOException {
        out.close();
    }

    public File getDir() {
        return dir;
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
