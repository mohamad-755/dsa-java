import java.util.*;

public class KWayMerge {

    private static class Node {
        int value;
        int listId;
        int indexInList;

        Node(int v, int lid, int idx) {
            this.value = v;
            this.listId = lid;
            this.indexInList = idx;
        }
    }

    public static int[] mergeKSorted(int[][] lists) {
        class comp implements Comparator<Node> {
            public int compare(Node n1, Node n2) {
                return Integer.compare(n1.value, n2.value);
            }
        }
        if (lists == null || lists.length == 0) {
            return new int[0];
        }
        int totalE = 0;
        for (int[] list : lists) {
            totalE += list.length;
        }
        int[] result = new int[totalE];

        PriorityQueue<Node> minHeap = new PriorityQueue<>(new comp());
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null && lists[i].length > 0) {
                minHeap.offer(new Node(lists[i][0], i, 0));
            }
        }
        int resultIndex = 0;

        while (!minHeap.isEmpty()) {
            if (resultIndex >= result.length) {
                System.out.println("warning");
                break;
            }
            Node x = minHeap.poll();
            result[resultIndex++] = x.value;

            int nextIdx = x.indexInList + 1;
            if (nextIdx < lists[x.listId].length) {
                minHeap.offer(new Node(lists[x.listId][nextIdx], x.listId, nextIdx));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] lists = {
                { 1, 4, 5 },
                { 1, 3, 4 },
                { 2, 6 }
        };
        int[] merged = mergeKSorted(lists);
        System.out.println(Arrays.toString(merged));
    }
}
