package Concurrency;

public class ProgressionToken extends Token {
    int value;

    public ProgressionToken(int value) {
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
