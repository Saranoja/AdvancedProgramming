/**
 * Author: Calin Irina, I2E2
 */

package Concurrency;

public class Token {
    public boolean blank=false;
    int value;

    public boolean isBlank() {
        return blank;
    }

    public Token(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "token: " + value;
    }
}