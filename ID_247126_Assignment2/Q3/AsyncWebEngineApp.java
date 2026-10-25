package ID_247126_Assignment2.Q3;

public class AsyncWebEngineApp {
    public static void main(String[] args) {
        System.out.println("=== Master App Started on Thread: " + Thread.currentThread().getName() + " ===");

        TelemetryThread telemetryWorker = new TelemetryThread("Telemetry-Worker");
        Thread logWorker = new Thread(new LogProcessorTask(), "Log-Worker");

        System.out.println("Launching worker threads asynchronously...");
        telemetryWorker.start();
        logWorker.start();

        System.out.println("Main thread continues non-blocking execution tasks...");
        
        try {
            telemetryWorker.join();
            logWorker.join();
        } catch (InterruptedException e) {
            System.out.println("Main execution interrupted while waiting for workers.");
        }

        System.out.println("=== All background processes finished. Exiting main application. ===");
    }
}
