package DMS;
/**
 * @author : Calin Irina, I2E2
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;

import org.xml.sax.SAXException;

public class InfoCommand implements Command {
    public static void StartCommand(Scanner scanner) {
        System.out.println("Type the path of the file you want to see metadata from. You may see some Tika warnings you don't care about:");
        String path = scanner.next();
        File file = new File(path);
        try {
            Parser parser = new AutoDetectParser();
            BodyContentHandler handler = new BodyContentHandler();
            Metadata metadata = new Metadata();
            FileInputStream inputStream = new FileInputStream(file);
            ParseContext context = new ParseContext();

            parser.parse(inputStream, handler, metadata, context);
            System.out.println(handler.toString());

            //getting the list of all meta data elements
            String[] metadataNames = metadata.names();

            for (String name : metadataNames) {
                System.out.println(name + ": " + metadata.get(name));

            }
        } catch (TikaException te) {
            System.out.println("Tika exception");
        } catch (IOException e) {
            System.out.println("File not found");
        } catch (SAXException se) {
            System.out.println("SAXException");
        }
    }
}
