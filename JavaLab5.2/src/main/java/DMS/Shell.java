package DMS;
/**
 * @author : Calin Irina, I2E2
 */

import java.io.IOException;
import java.util.Scanner;

public class Shell {
    public void Start() throws IOException, InvalidCatalogException {
        boolean quit = false;
        Scanner scanner = new Scanner(System.in);
        String command;
        System.out.println("Type command (load, view, html, info, report, quit):");
        while (!quit) {
            command = scanner.next();
            switch (command) {
                case "quit": {
                    quit = true;
                    break;
                }
                case "load": {
                    LoadCommand.StartCommand(scanner);
                    //System.out.println(catalog);
                    break;
                }
                case "view": {
                    ViewCommand.StartCommand(scanner);
                    break;
                }
                case "html": {
                    ReportHTML.StartCommand(scanner);
                    break;
                }
                case "info": {
                    InfoCommand.StartCommand(scanner);
                    break;
                }
                case "report": {
                    FormattedReportCommand.StartCommand(scanner);
                    break;
                }
                default: {
                    System.out.println("Unknown command! Try again.");
                    break;
                }
            }
        }
    }
}
