import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Solution {
    
    static final String INSERT = "I";
    static final String DELETE = "D";
    static final int MAXIMUM = 1;
    static final int MINIMUM = -1;
    
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        
        PriorityQueue<Integer> minimumPQ = new PriorityQueue<>();
        PriorityQueue<Integer> maximumPQ = new PriorityQueue<>(Collections.reverseOrder());
        for (String operation : operations) {
            StringTokenizer st = new StringTokenizer(operation);
            String command = st.nextToken();
            int number = Integer.parseInt(st.nextToken());
            
            switch(command) {
                case INSERT:
                    minimumPQ.offer(number);
                    maximumPQ.offer(number);
                    break;
                case DELETE:
                    if (!minimumPQ.isEmpty() && !maximumPQ.isEmpty()) {
                        if (number == MAXIMUM) {
                            minimumPQ.remove(maximumPQ.poll());
                        } else if (number == MINIMUM) {
                            maximumPQ.remove(minimumPQ.poll());
                        }
                    }
                    break;
            }
        }
        if (!minimumPQ.isEmpty() && !maximumPQ.isEmpty()) {
            answer[0] = maximumPQ.poll();
            answer[1] = minimumPQ.poll();
        }
        return answer;
    }
}