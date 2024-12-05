import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    
    static final String BLANK = " ";
    static final String INSERT = "I";
    static final String DELETE = "D";
    static final int MAXIMUM = 1;
    static final int MINIMUM = -1;
    
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> minimumPQ = new PriorityQueue<>();
        PriorityQueue<Integer> maximumPQ = new PriorityQueue<>(Collections.reverseOrder());
        
        for (String operation : operations) {
            String command = operation.split(BLANK)[0];
            int number = Integer.parseInt(operation.split(BLANK)[1]);
            
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
        if (minimumPQ.isEmpty() && maximumPQ.isEmpty()) {
            return new int[]{0, 0};
        }
        return new int[]{maximumPQ.poll(), minimumPQ.poll()};
    }
}