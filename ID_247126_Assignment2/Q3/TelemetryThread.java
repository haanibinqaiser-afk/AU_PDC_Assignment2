package ID_247126_Assignment2.Q3;

public class TelemetryThread extends Thread {
    public TelemetryThread(String threadName) {
        super(threadName);
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                System.out.println("[" + getName() + "] System Health Update (CPU/RAM Check) Step " + i + "/5");
                Thread.sleep(800);
            }
        } catch (InterruptedException e) {
            System.out.println("Telemetry thread interrupted.");
        }
    }
}
