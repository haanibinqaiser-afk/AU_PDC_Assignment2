package ID_247126_Assignment2.Q5;

import java.util.Random;

public class SensorGridMonitorApp {
    public static void main(String[] args) throws InterruptedException {
        int totalElements = 50000;
        double[] telemetry = new double[totalElements];
        Random rand = new Random(10);

        for (int i = 0; i < totalElements; i++) {
            telemetry[i] = -50.0 + (rand.nextDouble() * 200.0);
        }

        int segments = 4;
        int chunkSize = totalElements / segments;

        ParallelMinSearch[] minRunners = new ParallelMinSearch[segments];
        ParallelMaxSearch[] maxRunners = new ParallelMaxSearch[segments];
        Thread[] workerThreads = new Thread[segments * 2];

        long startTime = System.nanoTime();

        for (int i = 0; i < segments; i++) {
            int start = i * chunkSize;
            int end = (i == segments - 1) ? totalElements : (start + chunkSize);

            minRunners[i] = new ParallelMinSearch(telemetry, start, end);
            maxRunners[i] = new ParallelMaxSearch(telemetry, start, end);

            workerThreads[i] = new Thread(minRunners[i]);
            workerThreads[i + segments] = new Thread(maxRunners[i]);

            workerThreads[i].start();
            workerThreads[i + segments].start();
        }

        for (Thread t : workerThreads) {
            t.join();
        }

        double overallMin = Double.MAX_VALUE;
        double overallMax = -Double.MAX_VALUE;

        for (int i = 0; i < segments; i++) {
            if (minRunners[i].getMinResult() < overallMin) overallMin = minRunners[i].getMinResult();
            if (maxRunners[i].getMaxResult() > overallMax) overallMax = maxRunners[i].getMaxResult();
        }

        long totalTime = System.nanoTime() - startTime;

        System.out.println("=== IoT Sensor Grid Parallel Search Complete ===");
        System.out.println("Total Samples Processed: " + totalElements);
        System.out.println("Lowest Recorded Threshold (Min): " + String.format("%.2f", overallMin));
        System.out.println("Highest Recorded Threshold (Max): " + String.format("%.2f", overallMax));
        System.out.println("Parallel Execution Time: " + (totalTime / 1_000_000.0) + " ms");
    }
}
