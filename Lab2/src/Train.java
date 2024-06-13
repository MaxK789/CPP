import java.util.concurrent.ThreadLocalRandom;

public class Train implements Runnable {
    private final String id;
    private final Tunnel tunnel1;
    private final Tunnel tunnel2;
    private final int maxWaitTime;

    public Train(String id, Tunnel tunnel1, Tunnel tunnel2, int maxWaitTime) {
        this.id = id;
        this.tunnel1 = tunnel1;
        this.tunnel2 = tunnel2;
        this.maxWaitTime = maxWaitTime;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (tunnel1.enter(id, maxWaitTime)) {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3000)); // Симуляція проїзду
                    tunnel1.exit(id);
                    break;
                }
                if (tunnel2.enter(id, maxWaitTime)) {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3000)); // Симуляція проїзду
                    tunnel2.exit(id);
                    break;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Train " + id + " was interrupted.");
            }
        }
    }
}
