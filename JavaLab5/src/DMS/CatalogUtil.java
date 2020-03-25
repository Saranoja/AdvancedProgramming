package DMS; /**
 * @author : Calin Irina, I2E2
 */

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

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

    //for a plain text representation we need to write all the data describing an object so that when we load it, we can actually recreate an object
    public static void PlainTextSave(Catalog catalog) throws IOException {
        String path = catalog.getPath();
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(path));
            writer.write(catalog.getDocuments().size() + "\n");
            for (Document document : catalog.getDocuments()) {
                writer.write(document.getLocation() + "\n");
                writer.write(document.getTags().size() + "\n");
                for(Map.Entry<String,String> entry : document.getTags().entrySet()) {
                    writer.write(entry.getKey() + "\n" + entry.getValue() + "\n");
                }
                writer.write(document.getId());
            }
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("File path doesn't exist");
        } catch (IOException e) {
            System.out.println("IOException occured");
        }
    }

    //reconstructing the object using the information found at the given path
    public static Catalog PlainTextLoad(String path) throws IOException {
        Catalog catalog = new Catalog();
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get(path));
            int catalogSize = Integer.parseInt(reader.readLine());
            for (int i=0; i<catalogSize;i++) {
                Document document = new Document(reader.readLine());
                int tagsNr = Integer.parseInt(reader.readLine());
                for (int j=0; i<tagsNr; i++){
                    document.addTag(reader.readLine(),reader.readLine());
                }
                document.setId(reader.readLine());
                catalog.add(document);
            }
            reader.close();
        } catch(FileNotFoundException e) {
            System.out.println("File Not Found");
        } catch (IOException e) {
            System.out.println("Path not found");
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
