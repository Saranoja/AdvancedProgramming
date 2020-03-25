package DMS;
/**
 * @author : Calin Irina, I2E2
 */

import java.io.IOException;
import java.util.Scanner;

public class LoadCommand implements Command {
    public static void StartCommand(Scanner scanner) throws IOException, InvalidCatalogException {
        System.out.println("Type the path of the catalog:");
        Catalog catalog1 = CatalogUtil.PlainTextLoad(scanner.next());
        System.out.println("Documents found in this catalog: ");
        for(Document d : catalog1.getDocuments())
            System.out.println(d.getLocation() + " with tags: " + d.getTags());
        //CatalogUtil.View(doc);
    }
}
