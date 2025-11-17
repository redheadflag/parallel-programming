package labs6;

import utils.Logger;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Example {

    public static ExecutorService threadPool = Executors.newFixedThreadPool(5);  // or newCachedThreadPool


    public static void main(String[] args) {
        Logger.info("Game lobby");

        Scanner sc = new Scanner(System.in);
        while (true) {
            sc.nextLine();
            threadPool.submit(new Player());
        }
    }
}
