import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RailwayTunnel {
    public static void main(String[] args) {
        Tunnel tunnel1 = new Tunnel("Tunnel 1");
        Tunnel tunnel2 = new Tunnel("Tunnel 2");

        int numTrains = 10;
        int maxWaitTime = 5000;

        ExecutorService executor = Executors.newFixedThreadPool(numTrains);

        for (int i = 1; i <= numTrains; i++) {
            Train train = new Train("Train " + i, tunnel1, tunnel2, maxWaitTime);
            executor.submit(train);
        }

        executor.shutdown();
    }
}
