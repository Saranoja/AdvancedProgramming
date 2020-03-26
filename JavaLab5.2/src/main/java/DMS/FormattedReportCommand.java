package DMS;

/**
 * @author : Calin Irina, I2E2
 */

//I've written a simple template in order to display the content of the catalog in HTML format + updated the libraries.
//I used FreeMarker for this and followed their examples

import freemarker.template.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FormattedReportCommand implements Command {

    public static void StartCommand(Scanner scanner) throws IOException {
        System.out.println("Type the path of the file for which you want a formatted report:");
        Catalog catalog1 = CatalogUtil.PlainTextLoad(scanner.next());
        System.out.println("Type the output path for your file:");
        String outputPath = scanner.next();
        FileWriter fw = new FileWriter(outputPath + "/HTMLFormattedReport.html");
        BufferedWriter out = new BufferedWriter(fw);
        File dir = new File("C:/Users/Irina/Desktop/AdvancedProgramming/JavaLab5.2/src/main/java/DMS/templates/");

        //Configure FreeMarker
        Configuration configuration = new Configuration();
        configuration.setDirectoryForTemplateLoading(dir);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        //Process template
        Template template = configuration.getTemplate("helloworld.ftl");
        Map<String, Object> input = new HashMap<String, Object>();

        for (Document document : catalog1.getDocuments()) {
            input.put("document", document);
        }

        try {
            template.process(input, out);
        } catch (TemplateException te) {
            System.out.println("Template exception occured");
        } finally {
            out.flush();
            out.close();
        }

        System.out.println("A file with the name HTMLFormattedReport.html was created at " + outputPath + "/HTMLFormattedReport.html");
    }
}
