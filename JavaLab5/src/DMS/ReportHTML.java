package DMS;

import java.io.*;
import java.util.Scanner;

public class ReportHTML implements Command {
    public static void StartCommand(Scanner scanner) throws IOException {
        System.out.println("Type the path of the catalog");
        Catalog catalog1 = CatalogUtil.PlainTextLoad(scanner.next());
        System.out.println("Type the output path for your file:");
        String outputPath = scanner.next();
        FileWriter fw = new FileWriter(outputPath + "/HTMLReport.html");
        BufferedWriter out = new BufferedWriter(fw);
        out.write("<!DOCTYPE HTML>");
        out.write("<html>");
        out.write("\t <head>");
        out.write("\t\t <title> Catalog </title>");
        out.write("\t </head>");
        out.write("\t <body>");

        for (Document document : catalog1.getDocuments()) {
            out.write("\t\t<h1> Document 1 </h1>");
            out.write("\t\t<p> ID: " + document.getId() + " </p>");
            out.write("\t\t<p> Tags: " + document.getTags() + " </p>");
        }

        out.write("\t </body>");
        out.write("</html>");
        fw.flush();
        out.flush();
        System.out.println("A file with the name HTMLReport.html was created at " + outputPath + "/HTMLReport.html");
    }
}
