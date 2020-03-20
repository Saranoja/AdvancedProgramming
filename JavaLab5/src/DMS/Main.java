package DMS; /**
 * @author : Calin Irina, I2E2
 */
//Note: I have changed some paths there in order to test my code

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
    }

    private void testCreateSave() throws IOException { //create a catalog and "save" it (write its path in its own content)
        Catalog catalog = new Catalog("Java Resources", "c:/users/irina/desktop/catalog1.ser"); //changed the path in order to test the functionality
        Document doc = new Document("java1", "Java Course 1",
                "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");

        doc.addTag("type", "Slides");
        catalog.add(doc);
        CatalogUtil.Save(catalog);
    }

    private void testLoadView() { //read the content, search for the document with the id "java1" and open (view) it
        try {
            Catalog catalog = CatalogUtil.Load("c:/users/irina/desktop/catalog1.ser");
            Document doc = catalog.findById("java1");
            CatalogUtil.View(doc);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidCatalogException e) {
            e.printStackTrace();
        }
    }

}