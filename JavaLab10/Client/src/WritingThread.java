import java.io.PrintWriter;
import java.util.Scanner;

public class WritingThread implements Runnable {
    public Scanner scanner;
    private PrintWriter out;
    private volatile String command;
    private Board board;
    private ReadingThread readingThread;

    public WritingThread(Scanner scanner, PrintWriter printWriter, Board board, ReadingThread readingThread) {
        this.scanner = scanner;
        this.out = printWriter;
        this.board = board;
        this.readingThread = readingThread;
    }

    @Override
    public void run() {
        while (scanner.hasNextLine()) {
            command = scanner.nextLine();
            System.out.println("Added command: " + command);
            out.println(command);

            readingThread.setCommand(command);

            if (command.toLowerCase().equals("stop"))
                break;
        }
    }
}
