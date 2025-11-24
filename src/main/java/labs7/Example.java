package labs7;

import utils.Logger;

public class Example {
    public static void main(String[] args) {
        Logger.info("Decentralized chat system");

        new Server().start();
        new UserInput().start();
    }
}
