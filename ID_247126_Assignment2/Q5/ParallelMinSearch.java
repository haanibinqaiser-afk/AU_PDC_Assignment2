package ID_247126_Assignment2.Q5;

public class ParallelMinSearch extends SensorSearchEngine {
    private double minResult = Double.MAX_VALUE;

    public ParallelMinSearch(double[] sensorData, int startIdx, int endIdx) {
        super(sensorData, startIdx, endIdx);
    }

    @Override
    public void processGrid() {
        for (int i = startIdx; i < endIdx; i++) {
            if (sensorData[i] < minResult) {
                minResult = sensorData[i];
            }
        }
    }

    public double getMinResult() {
        return minResult;
    }
}
