import java.util.*;

public class TopKUsingMinHeap {

    public static int[] topK(int[] a, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            heap.add(a[i]);
        }
        for (int i = k; i < a.length; i++) {
            if (a[i] > heap.peek()) {
                heap.poll();
                heap.add(a[i]);
            }
        }
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = heap.poll();
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = { 3, 1, 5, 12, 2, 11 };
        int[] res = topK(arr, 6);
        System.out.println(Arrays.toString(res));
    }
}
