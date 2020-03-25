package DMS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class ViewCommand implements Command {
    public static void StartCommand(Scanner scanner) throws IOException, InvalidCatalogException {
        System.out.println("Type the path of the document:");
        String path = scanner.next();
        Document doc = new Document(path);
        CatalogUtil.View(doc);
    }
}
