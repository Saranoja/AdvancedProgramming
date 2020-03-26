package DMS;

/**
 * @author : Calin Irina, I2E2
 */

public class InvalidCatalogException extends Exception {
    public InvalidCatalogException(Exception ex) {
        super("Invalid catalog file.", ex);
    }
}
