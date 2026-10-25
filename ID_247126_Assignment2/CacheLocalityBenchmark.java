package ID_247126_Assignment2;

public class CacheLocalityBenchmark {
    private static final int DIMENSION = 2000;
    private static double[][] grid = new double[DIMENSION][DIMENSION];

    public static void fillMatrix() {
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                grid[i][j] = 1.0;
            }
        }
    }

    public static double traverseRowMajor() {
        double accumulator = 0.0;
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                accumulator += grid[i][j];
            }
        }
        return accumulator;
    }

    public static double traverseColumnMajor() {
        double accumulator = 0.0;
        for (int j = 0; j < DIMENSION; j++) {
            for (int i = 0; i < DIMENSION; i++) {
                accumulator += grid[i][j];
            }
        }
        return accumulator;
    }

    public static void main(String[] args) {
        fillMatrix();
        int runs = 10;

        traverseRowMajor();
        traverseColumnMajor();

        long rowTimeAccumulator = 0;
        for (int r = 0; r < runs; r++) {
            long t1 = System.nanoTime();
            traverseRowMajor();
            long t2 = System.nanoTime();
            rowTimeAccumulator += (t2 - t1);
        }
        double avgRowTimeMs = (rowTimeAccumulator / (double) runs) / 1_000_000.0;

        long colTimeAccumulator = 0;
        for (int r = 0; r < runs; r++) {
            long t1 = System.nanoTime();
            traverseColumnMajor();
            long t2 = System.nanoTime();
            colTimeAccumulator += (t2 - t1);
        }
        double avgColTimeMs = (colTimeAccumulator / (double) runs) / 1_000_000.0;

        double ratio = avgColTimeMs / avgRowTimeMs;

        System.out.println("=== Cache Locality Matrix Traversal Performance ===");
        System.out.println("Matrix Size: " + DIMENSION + " x " + DIMENSION);
        System.out.println("Row-Major Mean Time: " + String.format("%.3f", avgRowTimeMs) + " ms");         System.out.println("Column-Major Mean Time: " + String.format("%.3f", avgColTimeMs) + " ms");
        System.out.println("Calculated Speedup Ratio: " + String.format("%.2f", ratio) + "x");
    }
}
 