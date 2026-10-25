package ID_247126_Assignment2.Q2;

public class RequestQueue {
    private char[] data;
    private int front;
    private int rear;
    private int count;
    private int capacity;

    public RequestQueue(int capacity) {
        this.capacity = capacity;
        this.data = new char[capacity];
        this.front = 0;
        this.rear = -1;
        this.count = 0;
    }

    public void enqueue(char c) {
        if (count < capacity) {
            rear = (rear + 1) % capacity;
            data[rear] = c;
            count++;
        }
    }

    public char dequeue() {
        if (!isEmpty()) {
            char val = data[front];
            front = (front + 1) % capacity;
            count--;
            return val;
        }
        return '\0';
    }

    public boolean isEmpty() {
        return count == 0;
    }
}