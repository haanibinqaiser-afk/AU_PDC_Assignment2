package ID_247126_Assignment2.Q5;

public class ParallelMaxSearch extends SensorSearchEngine {
    private double maxResult = -Double.MAX_VALUE;

    public ParallelMaxSearch(double[] sensorData, int startIdx, int endIdx) {
        super(sensorData, startIdx, endIdx);
    }

    @Override
    public void processGrid() {
        for (int i = startIdx; i < endIdx; i++) {
            if (sensorData[i] > maxResult) {
                maxResult = sensorData[i];
            }
        }
    }

    public double getMaxResult() {
        return maxResult;
    }
}
