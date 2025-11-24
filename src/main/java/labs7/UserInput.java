package labs7;

import java.util.Scanner;

public class UserInput extends Thread {

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String line = sc.nextLine();

            // send the message
            PeerList.broadcast(line);
        }
    }
}
