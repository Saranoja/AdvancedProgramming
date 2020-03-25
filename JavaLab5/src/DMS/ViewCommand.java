package DMS;
/**
 * @author : Calin Irina, I2E2
 */

import java.io.IOException;
import java.util.Scanner;

public class ViewCommand implements Command {
    public static void StartCommand(Scanner scanner) throws IOException, InvalidCatalogException {
        System.out.println("Type the path of the document:");
        String path = scanner.next();
        Document doc = new Document(path);
        CatalogUtil.View(doc);
    }
}
