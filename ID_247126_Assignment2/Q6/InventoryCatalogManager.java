package ID_247126_Assignment2.Q6;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class InventoryCatalogManager {

    public static void benchmarkSynchronizedMap() throws InterruptedException {
        Map<String, Integer> baseMap = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            baseMap.put("ITEM_" + i, 1000);
        }
        Map<String, Integer> syncMap = Collections.synchronizedMap(baseMap);

        int threadCount = 10;
        int opsPerThread = 50000;
        ExecutorService pool = Executors.newFixedThreadPool(threadCount);

        long start = System.nanoTime();

        for (int t = 0; t < threadCount; t++) {
            pool.submit(() -> {
                for (int i = 0; i < opsPerThread; i++) {
                    String key = "ITEM_" + (i % 1000);
                    syncMap.get(key);
                    syncMap.put(key, 999);
                }
            });
        }

        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.MINUTES);
        long elapsed = System.nanoTime() - start;

        double seconds = elapsed / 1_000_000_000.0;
        long totalOps = (long) threadCount * opsPerThread * 2;
        double throughput = totalOps / seconds;

        System.out.println("--- Benchmark: Collections.synchronizedMap ---");
        System.out.println("Time Elapsed: " + (elapsed / 1_000_000.0) + " ms");
        System.out.println("Throughput: " + String.format("%.2f", throughput) + " ops/sec");
    }

    public static void benchmarkConcurrentMap() throws InterruptedException {
        ConcurrentHashMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        for (int i = 0; i < 1000; i++) {
            concurrentMap.put("ITEM_" + i, 1000);
        }

        int threadCount = 10;
        int opsPerThread = 50000;
        ExecutorService pool = Executors.newFixedThreadPool(threadCount);

        long start = System.nanoTime();

        for (int t = 0; t < threadCount; t++) {
            pool.submit(() -> {
                for (int i = 0; i < opsPerThread; i++) {
                    String key = "ITEM_" + (i % 1000);
                    concurrentMap.get(key);
                    concurrentMap.put(key, 999);
                }
            });
        }

        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.MINUTES);
        long elapsed = System.nanoTime() - start;

        double seconds = elapsed / 1_000_000_000.0;
        long totalOps = (long) threadCount * opsPerThread * 2;
        double throughput = totalOps / seconds;

        System.out.println("--- Benchmark: ConcurrentHashMap ---");
        System.out.println("Time Elapsed: " + (elapsed / 1_000_000.0) + " ms");
        System.out.println("Throughput: " + String.format("%.2f", throughput) + " ops/sec");
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== High Concurrency Inventory Catalog Benchmark ===");
        benchmarkSynchronizedMap();
        System.out.println();
        benchmarkConcurrentMap();
    }
}
