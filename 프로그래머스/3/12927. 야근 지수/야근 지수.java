import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int sum = 0;
        for (int i = 0; i < works.length; i++) {
            pq.offer(works[i]);
            sum += works[i];
        }
        if (sum <= n) {
            return 0;
        }
        while (n-- > 0) {
            pq.offer(pq.poll() - 1);
        }
        long answer = 0;
        while (!pq.isEmpty()) {
            answer += Math.pow(pq.poll(), 2);
        }
        return answer;
    }
}