package ID_247126_Assignment2.Q1;

public class TradingEngineApp {
    public static void main(String[] args) {
        System.out.println("--- Starting Trading Engine Event Dispatcher ---");
        
        TradeEvent baseEvent = new TradeEvent("GENERIC-SRC", System.currentTimeMillis(), 1000.0);
        TradeEvent fixOrder = new FIXOrderEvent("FIX-CLIENT-88", System.currentTimeMillis(), 2500.0, "ORD-2026-99");
        TradeEvent reutersQuote = new QuoteEvent("REUTERS-FEED", System.currentTimeMillis(), 2495.0, 2490.5, 2498.0);

        baseEvent.dispatch();
        fixOrder.dispatch();
        reutersQuote.dispatch();
    }
}
