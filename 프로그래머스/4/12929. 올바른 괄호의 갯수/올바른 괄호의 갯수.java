import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    
    public int solution(int n) {
        return makeParenthesisPair(n);
    }
    
    static int makeParenthesisPair(int n) {
        int count = 0;
        Queue<Pair> queue = new ArrayDeque<>();
        queue.offer(new Pair(0, 0));
        
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            
            if (pair.openCount == n && pair.closeCount == n) {
                count++;
                continue;
            }
            if (pair.openCount > 0 
                && pair.openCount > pair.closeCount 
                && pair.closeCount < n) {
                queue.offer(new Pair(pair.openCount, pair.closeCount + 1));
            }
            if (pair.openCount < n) {
                queue.offer(new Pair(pair.openCount + 1, pair.closeCount));
            }
        }
        return count;
    }
    
    static class Pair {
        int openCount;
        int closeCount;
        
        public Pair(int openCount, int closeCount) {
            this.openCount = openCount;
            this.closeCount = closeCount;
        }
    }
}