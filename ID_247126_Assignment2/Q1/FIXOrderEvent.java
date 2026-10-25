package ID_247126_Assignment2.Q1;

public class FIXOrderEvent extends TradeEvent {
    private String clOrdId;

    public FIXOrderEvent(String senderId, long timestamp, double basePrice, String clOrdId) {
        super(senderId, timestamp, basePrice);
        this.clOrdId = clOrdId;
    }

    @Override
    public void dispatch() {
        double fee = basePrice * 0.0015;
        double totalCost = basePrice + fee;
        System.out.println("[FIX GATEWAY] Stock Purchase Executed | Order ID: " + clOrdId + " | Client: " + senderId + " | Price: $" + basePrice + " | Fee (0.15%): $" + fee + " | Total: $" + totalCost);
    }
}
