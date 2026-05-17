import java.util.*;

public class HeapSortInPlace {

    private static void swap(int[] A, int i, int j) {
        int t = A[i];
        A[i] = A[j];
        A[j] = t;
    }

    private static int left(int i) {
        return 2 * i + 1;
    }

    private static int right(int i) {
        return 2 * i + 2;
    }

    private static void downheap(int[] A, int i, int heapSize) {
        while (left(i) < heapSize) {
            int left = left(i);
            int largeIndex = left;
            if (right(i) < heapSize) {
                int right = right(i);
                if (A[left] < A[right]) {
                    largeIndex = right;
                }
            }

            if (A[i] >= A[largeIndex]) {
                break;
            }
            swap(A, i, largeIndex);
            i = largeIndex;
        }
    }

    private static void buildMaxHeap(int[] A) {
        int startIndex = A.length / 2 - 1;
        for (int i = startIndex; i >= 0; i--) {
            downheap(A, i, A.length);
        }

    }

    public static void heapSort(int[] A) {
        buildMaxHeap(A);

        for (int i = A.length; i > 1; i--) {
            swap(A, 0, i - 1);
            downheap(A, 0, i - 1);
        }

    }

    public static boolean isSorted(int[] A) {
        for (int i = 1; i < A.length; i++)
            if (A[i - 1] > A[i])
                return false;
        return true;
    }

    public static void main(String[] args) {
        int[] arr = { 4, 10, 3, 5, 1 };
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(isSorted(arr));
    }
}
