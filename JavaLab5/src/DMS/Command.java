package DMS;

import java.io.IOException;
import java.util.Scanner;

public interface Command {
    public static void StartCommand(Catalog catalog, Scanner scanner) throws IOException, InvalidCatalogException {};
}
