import java.util.*;

public class MedianFinder {
    private PriorityQueue<Integer> lower; // max-heap (store negatives or use reverse comparator)
    private PriorityQueue<Integer> upper; // min-heap

    public MedianFinder() {
        lower = new PriorityQueue<>(Comparator.reverseOrder());
        upper = new PriorityQueue<>();
    }

    public void add(int x) {
        if (lower.isEmpty() || x <= lower.peek())
            lower.add(x);
        else
            upper.add(x);
        if (lower.size() > upper.size() + 1) {
            upper.add(lower.poll());
        } else if (upper.size() > lower.size() + 1) {
            lower.add(upper.poll());
        }
    }

    public int median() {
        if (lower.size() == upper.size())
            return (lower.peek() + upper.peek()) / 2;
        else if (lower.size() > upper.size())
            return lower.peek();
        else
            return upper.peek();
    }

    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();
        int[] stream = { 5, 15, 1, 3 };
        for (int x : stream) {
            mf.add(x);
            System.out.println(mf.median());
        }
    }
}
