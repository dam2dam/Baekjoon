import java.util.ArrayList;
import java.util.PriorityQueue;

class Solution {
    
    ArrayList<Island>[] adjList;
    
    public int solution(int n, int[][] costs) {
        makeAdjacencyList(n, costs);
        return makeBridges(n);
    }
    
    int makeBridges(int n) {
        int totalCost = 0;
        PriorityQueue<Island> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[n];
        
        pq.offer(new Island(0, 0));
        
        while (!pq.isEmpty()) {
            Island from = pq.poll();
            if (visited[from.number]) {
                continue;
            }
            visited[from.number] = true;
            totalCost += from.cost;
            
            for (Island to : adjList[from.number]) {
                pq.offer(to);
            }
        }
        return totalCost;
    }
    
    void makeAdjacencyList(int n, int[][] costs) {
        adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < costs.length; i++) {
            int island1 = costs[i][0];
            int island2 = costs[i][1];
            int cost = costs[i][2];
            adjList[island1].add(new Island(island2, cost));
            adjList[island2].add(new Island(island1, cost));
        }
    }
    
    class Island implements Comparable<Island> {
        int number;
        int cost;
        
        public Island(int number, int cost) {
            this.number = number;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Island i) {
            return this.cost < i.cost ? -1 : this.cost == i.cost ? 0 : 1;
        }
    }
}