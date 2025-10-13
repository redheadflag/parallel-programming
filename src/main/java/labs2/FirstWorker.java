package labs2;

import utils.Logger;

public class FirstWorker extends Thread {

    @Override
    public void run() {
        Thread.currentThread().setName("FirstWorker");
        Logger.info("Worker running");
    }
}
