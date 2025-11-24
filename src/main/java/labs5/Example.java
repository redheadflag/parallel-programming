package labs5;


import utils.Logger;

public class Example {
    public static void main(String[] args) throws InterruptedException {
        Logger.info("Producer-Consumer");
        long start = System.currentTimeMillis();

        EventQueue eventQueue = new EventQueue();

        EventProducer eventProducer = new EventProducer(eventQueue, 1_000);
        eventProducer.start();


        TicketStorage storage = new TicketStorage();
        ValidateTicketConsumer validateTicketConsumer = new ValidateTicketConsumer(eventQueue, storage);
        CreateTicketConsumer createTicketConsumer = new CreateTicketConsumer(eventQueue, storage);
        UseTicketConsumer useTicketConsumer = new UseTicketConsumer(eventQueue, storage);


        validateTicketConsumer.start();
        createTicketConsumer.start();
        useTicketConsumer.start();

        while (eventQueue.getSize() != 0) {
            Thread.sleep(10);
        }

        validateTicketConsumer.shouldRun = false;
        createTicketConsumer.shouldRun = false;
        useTicketConsumer.shouldRun = false;

        validateTicketConsumer.join();
        createTicketConsumer.join();
        useTicketConsumer.join();

        long end = System.currentTimeMillis();
        Logger.debug("Time taken: " + (end - start) + "ms");
        Logger.info("Done");
    }
}
