package labs5;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TicketStorage {

    private final ArrayList<Integer> ticketIds = new ArrayList<>();

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public void insertTicket(int ticketId) {
        writeLock.lock();

        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        ticketIds.add(ticketId);

        writeLock.unlock();
    }

    public void removeTicket(int ticketId) {
        writeLock.lock();
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        int index = ticketIds.indexOf(ticketId);
        if (index == -1) {
            writeLock.unlock();
            return;
        }
        ticketIds.remove(index);

        writeLock.unlock();
    }

    public boolean containsTicket(int ticketId) {
        readLock.lock();
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        boolean b = ticketIds.contains(ticketId);
        readLock.unlock();
        return b;
    }
}
