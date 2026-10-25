package ID_247126_Assignment2.Q1;

public class QuoteEvent extends TradeEvent {
    private double bidPrice;
    private double askPrice;

    public QuoteEvent(String senderId, long timestamp, double basePrice, double bidPrice, double askPrice) {
        super(senderId, timestamp, basePrice);
        this.bidPrice = bidPrice;
        this.askPrice = askPrice;
    }

    @Override
    public void dispatch() {
        double spread = askPrice - bidPrice;
        System.out.println("[REUTERS QUOTE] Market Analytics | Sender: " + senderId + " | Bid: $" + bidPrice + " | Ask: $" + askPrice + " | Bid-Ask Spread: $" + spread + " | Time: " + timestamp);
    }
}