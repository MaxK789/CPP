import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

public class Tunnel {
    private final String name;
    private final ReentrantLock lock = new ReentrantLock();

    public Tunnel(String name) {
        this.name = name;
    }

    public boolean enter(String trainId, int maxWaitTime) {
        try {
            if (lock.tryLock(maxWaitTime, TimeUnit.MILLISECONDS)) {
                System.out.println("Train " + trainId + " entered " + name);
                return true;
            } else {
                System.out.println("Train " + trainId + " could not enter " + name + " and is trying another tunnel.");
                return false;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Train " + trainId + " was interrupted while trying to enter " + name);
            return false;
        }
    }

    public void exit(String trainId) {
        lock.unlock();
        System.out.println("Train " + trainId + " exited " + name);
    }
}
