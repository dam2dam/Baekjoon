import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    
    static ArrayList<Path>[] map;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        // 출발지에서 도착지까지 요금을 저장할 배열 초기화
        map = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            map[i] = new ArrayList<>();
        }
        // 연결된 지점끼리 나오는 요금 저장
        for (int i = 0; i < fares.length; i++) {
            int from = fares[i][0];
            int to = fares[i][1];
            int cost = fares[i][2];
            map[from].add(new Path(to, cost));
            map[to].add(new Path(from, cost));
        }
        int min = Integer.MAX_VALUE;
        // 주어진 출발지에서 다른 모든 지점으로 가는 최소 요금
        int[] fromStart = goTaxi(s, n); 
        for (int number = 1; number <= n; number++) {
            // 임의의 경유지(number)에서 다른 모든 지점으로 가는 최소 요금
            int[] fromNumber = goTaxi(number, n);
            
            // 출발지에서 number지점까지 가는 요금 (합승 요금) + number지점에서 a까지 가는 요금 + number지점에서 b까지 가는 요금
            //             ↗ ⓐ
            // ⓢ → number
            //             ↘ ⓑ
            // ex)
            // number == s : 합승 안하는 경우
            // number == a : a까지 합승 후 a가 내리고 b까지 가는 경우
            // number == b : b까지 합승 후 b가 내리고 a까지 가는 경우
            min = Math.min(min, fromStart[number] + fromNumber[a] + fromNumber[b]);
        }
        return min;
    }
    
    /**
	 * 출발지에서 다른 모든 지점까지의 최소 택시 요금을 찾는 메서드
	 * @param start 출발점
	 * @param n 지점의 개수
	 * @return 출발지에서 각 지점까지의 최소 택시 요금이 담긴 1차원 배열
	 */
    static int[] goTaxi(int start, int n) {
        // 최소 요금 배열 초기화
        int[] minCosts = new int[n + 1];
        Arrays.fill(minCosts, Integer.MAX_VALUE);
        // 우선 순위 큐 정렬 기준 : cost 오름차순
        PriorityQueue<Path> pq = new PriorityQueue<>();
        // 출발지부터 시작
        minCosts[start] = 0;
        pq.offer(new Path(start, 0));
        
        while(!pq.isEmpty()) {
            Path path = pq.poll();
            int from = path.number;
            // 최소 요금이 이미 갱신된 경우 넘어감
            if (minCosts[from] < path.cost) {
                continue;
            }
            // 현재 지점(from)에 연결된 각 지점(to)까지 가장 적은 요금 찾기
            for (int i = 0; i < map[from].size(); i++) {
                int to = map[from].get(i).number;
                int cost = map[from].get(i).cost;
                // 목표 지점(to)까지 기록된 최소 요금 보다 현재 지점(from)을 거쳐서 가는 요금이 더 적으면 갱신
                int fromToCost = minCosts[from] + cost;
                if (minCosts[to] > fromToCost) {
                    minCosts[to] = fromToCost;
                    pq.offer(new Path(to, fromToCost));
                }
            }
        }
        return minCosts;
    }
    
    static class Path implements Comparable<Path> {
        int number;
        int cost;
        
        /**
		 * @param number 지점 번호
		 * @param cost 지점까지 나오는 요금
		 */
        public Path(int number, int cost) {
            this.number = number;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Path p) {
            return this.cost < p.cost ? -1 : this.cost == p.cost ? 0 : 1;
        }
    }
}