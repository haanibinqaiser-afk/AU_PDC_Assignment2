package ID_247126_Assignment2.Q2;

public class PacketSymmetryAuditor {
    public static void main(String[] args) {
        String testPacket = "HEADER-REDACTED-CBAABC-REDACTED-HEADER";
        String payload = "RACECAR";

        System.out.println("--- Starting Zero-Trust Packet Auditor ---");
        System.out.println("Ingesting Network Payload: " + payload);

        TransactionStack stack = new TransactionStack(payload.length());
        RequestQueue queue = new RequestQueue(payload.length());

        for (int i = 0; i < payload.length(); i++) {
            char ch = payload.charAt(i);
            stack.push(ch);
            queue.enqueue(ch);
        }

        boolean symmetric = true;
        while (!stack.isEmpty() && !queue.isEmpty()) {
            char fromStack = stack.pop();
            char fromQueue = queue.dequeue();

            if (fromStack != fromQueue) {
                symmetric = false;
                break;
            }
        }

        if (symmetric) {
            System.out.println("AUDIT PASSED: Packet signature shows bi-directional symmetry (Palindrome).");
        } else {
            System.out.println("AUDIT FAILED: Packet header payload symmetry broken!");
        }
    }
}