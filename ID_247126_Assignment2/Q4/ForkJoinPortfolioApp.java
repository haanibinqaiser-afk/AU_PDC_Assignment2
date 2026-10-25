package ID_247126_Assignment2.Q4;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class ForkJoinPortfolioApp {
    public static void main(String[] args) {
        int portfolioSize = 10000;
        double[] assetValues = new double[portfolioSize];
        Random rng = new Random(42);

        for (int i = 0; i < portfolioSize; i++) {
            assetValues[i] = 200.0 + (rng.nextDouble() * 800.0);
        }

        ForkJoinPool pool = new ForkJoinPool();

        long startTime = System.nanoTime();
        ParallelRiskCalculator riskCalc = new ParallelRiskCalculator(assetValues, 0, portfolioSize);
        double cumulativeRisk = pool.invoke(riskCalc);
        long riskTime = System.nanoTime() - startTime;

        long stressStart = System.nanoTime();
        PortfolioThresholdTask stressTask = new PortfolioThresholdTask(assetValues, 0, portfolioSize, 700.0);
        pool.invoke(stressTask);
        long stressTime = System.nanoTime() - stressStart;

        System.out.println("=== Fork/Join Parallel Analytics Result ===");
        System.out.println("Evaluated Assets Count: " + portfolioSize);
        System.out.println("Calculated Cumulative Risk Sum: $" + String.format("%.2f", cumulativeRisk));
        System.out.println("Risk Calculation Time: " + (riskTime / 1_000_000.0) + " ms");
        System.out.println("In-place Stress Penalty Adjustments Time: " + (stressTime / 1_000_000.0) + " ms");
    }
}
