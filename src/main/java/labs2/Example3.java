package labs2;

import utils.Logger;

import java.util.Scanner;

public class Example3 {
    public static void main(String[] args) throws InterruptedException {
        Logger.info("Example 3");

        Worker worker = new Worker();

        worker.start();

        Logger.debug("Press [enter] to stop the worker!");

        new Scanner(System.in).nextLine();

        Logger.debug("Stopping worker...");

        worker.shouldRun = false;

        worker.join();
    }


}
