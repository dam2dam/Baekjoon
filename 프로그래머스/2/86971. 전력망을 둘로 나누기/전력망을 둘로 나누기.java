import java.util.ArrayDeque;
import java.util.ArrayList;

class Solution {
    
    static ArrayList<Integer>[] tops;
    static boolean[][] visited;
    
    public int solution(int n, int[][] wires) {
        // 송전탑의 인접 리스트
        tops = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            tops[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            int from = wires[i][0];
            int to = wires[i][1];
            tops[from].add(to);
            tops[to].add(from);
        }
        
        int network1;
        int network2;
        int count1;
        int count2;
        int min = 100;
        for (int i = 0; i < n - 1; i++) {
            visited = new boolean[n + 1][n + 1];
            network1 = wires[i][0];
            network2 = wires[i][1];
            // 전선 끊기
            visited[network1][network2] = true;
            
            count1 = countTops(network1);
            count2 = countTops(network2);            
            min = Math.min(min, Math.abs(count1 - count2));
        }
        return min;
    }

    /**
     * 하나의 네트워크에 포함된 송전탑의 개수를 세는 메서드
     * @param start 출발점 송전탑의 번호
     * @return 송전탑의 개수
     */
    static int countTops(int start) {
        int count = 0;
        
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        
        while (!queue.isEmpty()) {
            int top = queue.poll();
            count++;
                
            for (int i = 0; i < tops[top].size(); i++) {
                int connectedTop = tops[top].get(i);
                if (visited[top][connectedTop] || visited[connectedTop][top] ) {
                    continue;
                }
                queue.offer(connectedTop);
                visited[top][connectedTop] = true;
            }
        }
        return count;
    }
}