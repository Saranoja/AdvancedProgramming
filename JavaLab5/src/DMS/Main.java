package DMS; /**
 * @author : Calin Irina, I2E2
 */
//Note: I have changed some paths there in order to test my code
//I have switched the load/save methods to Plain Text mode. This means that now the objects are "translated" to ascii
//by writing their attributes in plain text and, for the reverse process, calling the constructor using the stored lines as arguments

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Main app = new Main();
        try {
            app.testCreateSave();
        } catch (IOException e) {
            e.printStackTrace();
        }
        app.testLoadView();
        Shell shell = new Shell();
        try {
            shell.Start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void testCreateSave() throws IOException { //create a catalog and "save" it (write its path in its own content)
        Catalog catalog = new Catalog("Java Resources", "c:/users/irina/desktop/catalog1.txt"); //changed the path in order to test the functionality
        Document doc = new Document("java1", "Java Course 1",
                "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");

        doc.addTag("type", "Slides");
        catalog.add(doc);
        //CatalogUtil.Save(catalog);
        CatalogUtil.PlainTextSave(catalog);
    }

    private void testLoadView() { //read the content, search for the document with the id "java1" and open (view) it
        try {
            //Catalog catalog = CatalogUtil.Load("c:/users/irina/desktop/catalog1.ser");
            Catalog catalog = CatalogUtil.PlainTextLoad("c:/users/irina/desktop/catalog1.txt");
            Document doc = catalog.findById("java1");
            CatalogUtil.View(doc);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } /* catch (InvalidCatalogException e) {
            e.printStackTrace();
        }*/
    }

}