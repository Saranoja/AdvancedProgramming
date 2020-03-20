package DMS; /**
 * @author : Calin Irina, I2E2
 */

import java.awt.*;
import java.io.*;
import java.net.URI;

public class CatalogUtil {
    public static void Save(Catalog catalog) throws IOException { //creates a stream and writes the catalog object in the file found at the specified path
        FileOutputStream fos = new FileOutputStream(catalog.getPath());
        ObjectOutputStream out = new ObjectOutputStream(fos); //serialize
        out.writeObject(catalog);
    }

    public static Catalog Load(String path) throws InvalidCatalogException, IOException { //creates a stream and reads the catalog object found at the specified path
        Catalog catalog = new Catalog();
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fis); //serialize
            catalog = (Catalog) in.readObject();
        } catch (ClassNotFoundException e) {

        }
        return catalog;
    }

    public static void View(Document doc) throws IOException { //given a document, View either opens it or browses for it - depending on its location
        try {
            if (doc.getLocation().startsWith("http")) { //well... you should browse for it in case the path starts like this
                URI uri2 = new URI(doc.getLocation());
                Desktop desktop = Desktop.getDesktop();
                desktop.browse(uri2);
            } else {
                Desktop.getDesktop().open(new File(doc.getLocation())); //otherwise, open the file at the given path
            }
        } catch (Exception ex) {
        }
    }

}
