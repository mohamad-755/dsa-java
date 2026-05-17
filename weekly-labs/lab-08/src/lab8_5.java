import java.util.*;

class Solution {
    public int[] getOrder(int[][] tasks) {

        // arr[i] = {enqueueTime, processingTime, originalIndex}
        int n = tasks.length;
        int[][] arr = new int[n][3];
        for (int i = 0; i < n; i++) {
            arr[i][0] = tasks[i][0];
            arr[i][1] = tasks[i][1];
            arr[i][2] = i;
        }

        class p implements Comparator<int[]> {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        }
        // Sort by enqueue time
        Arrays.sort(arr, new p());

        // PriorityQueue:
        // first by processingTime
        // if tie, by originalIndex
        class q implements Comparator<int[]> {
            public int compare(int[] a, int[] b) {
                if (a[1] != b[1]) {
                    return a[1] - b[1];
                }
                return a[2] - b[2];
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(new q());
        int ans[] = new int[n];
        int ansIndex = 0;
        int i = 0;
        int time = 0;

        while (i < n || !pq.isEmpty()) {
            if (pq.isEmpty() && time < arr[i][0]) {
                time = arr[i][0];
            }

            while (i < n && arr[i][0] <= time) {
                pq.offer(arr[i]);
                i++;
            }

            int[] curr = pq.poll();
            ans[ansIndex] = curr[2];
            ansIndex++;

            time += curr[1];
        }
        return ans;
    }
}