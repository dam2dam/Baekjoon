import java.util.ArrayList;

class Solution {
    
    static final int CEO = 0;
    static final int ATTEND = 1;
    static final int NOT_ATTEND = 0;
    
    static int N;
    static ArrayList<Integer>[] staffs;
    static int[][] cost;
    
    public int solution(int[] sales, int[][] links) {
        N = sales.length;
        // 직원 정보(조직도) 배열 초기화
        staffs = new ArrayList[N];
        cost = new int[N][2];
        for (int i = 0; i < N; i++) {
            staffs[i] = new ArrayList<>();
            // 워크샵 참석 비용 배열 초기화
            cost[i][NOT_ATTEND] = 0;
            cost[i][ATTEND] = sales[i];
        }
        // 조직도 기록
        for (int i = 0; i < N - 1; i++) {
            int senior = links[i][0] - 1;
            int junior = links[i][1] - 1;
            staffs[senior].add(junior);
        }
        dfs(CEO);
        
        return Math.min(cost[CEO][ATTEND], cost[CEO][NOT_ATTEND]);
    }
    
    /**
	 * 워크숍에 참석 할 경우에 드는 비용을 계산한다
	 * @param number 직원 번호
	 */
    static void dfs(int number) {
        if (staffs[number].isEmpty()) {
            return;
        }
        int minSales = Integer.MAX_VALUE;
        
        // 현재 직원이 팀장인 팀의 팀원들부터 계산
        for (int i = 0; i < staffs[number].size(); i++) {
            int junior = staffs[number].get(i);
            dfs(junior);
            // 팀원이 참석하는 경우
            if (cost[junior][ATTEND] < cost[junior][NOT_ATTEND]) {
                // 현재 직원(팀장)이 참석하는 경우
                cost[number][ATTEND] += cost[junior][ATTEND];
                // 현재 직원(팀장)이 참석하지 않는 경우
                cost[number][NOT_ATTEND] += cost[junior][ATTEND];
                minSales = 0;
            // 팀원이 참석하지 않는 경우
            } else {
                // 현재 직원(팀장)이 참석하는 경우
                cost[number][ATTEND] += cost[junior][NOT_ATTEND];
                // 현재 직원(팀장)이 참석하지 않는 경우      
                cost[number][NOT_ATTEND] += cost[junior][NOT_ATTEND];          
                // 팀장과 팀원 모두 참석하지 않았기 때문에 가장 적은 비용이 드는 팀원을 참석하게 함
                minSales = Math.min(minSales, cost[junior][ATTEND] - cost[junior][NOT_ATTEND]);
            }
        }
        cost[number][NOT_ATTEND] += minSales;
    }
}
