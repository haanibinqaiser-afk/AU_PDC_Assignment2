package ID_247126_Assignment2.Q1;

public class TradeEvent {
    protected String senderId;
    protected long timestamp;
    protected double basePrice;

    public TradeEvent(String senderId, long timestamp, double basePrice) {
        this.senderId = senderId;
        this.timestamp = timestamp;
        this.basePrice = basePrice;
    }

    public void dispatch() {
        System.out.println("[BASE EVENT] Logged trade event from sender: " + senderId + " at time " + timestamp + " with base price $" + basePrice);
    }
}
