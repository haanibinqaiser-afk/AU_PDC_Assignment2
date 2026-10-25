package ID_247126_Assignment2.Q3;

public class LogProcessorTask implements Runnable {
    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                System.out.println("[" + Thread.currentThread().getName() + "] Batch Log Processing Step " + i + "/5");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Log Processor thread interrupted.");
        }
    }
}
