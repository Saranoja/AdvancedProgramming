package ThreadPoolExecutor;/**
 * @author: Calin Irina, I2E2
 */

import db.ConnectionPool;

import java.util.concurrent.*;

public class TPE {
    private int tasksNr;
    private ConnectionPool cp;

    public TPE(int tasksNr, ConnectionPool cp) {
        this.tasksNr = tasksNr;
        this.cp = cp;
    }

    public void start() {
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(tasksNr);

        for (int i = 0; i < tasksNr; ++i) {
            MainThread mainThread = new MainThread(cp);
            threadPoolExecutor.execute(mainThread);
        }

        threadPoolExecutor.shutdown();
    }

}
