class Solution {
    
    final int WIN = 1;
    final int LOSE = -1;
    
    public int solution(int n, int[][] results) {
        int answer = 0;
        
        // winAndLose[A][B] : A가 B에게 이기면 1, 지면 -1
        int[][] winAndLose = new int[n + 1][n + 1];
        for (int[] result : results) {
            int winner = result[0];
            int loser = result[1];
            winAndLose[winner][loser] = WIN;
            winAndLose[loser][winner] = LOSE;
        }
        for (int start = 1; start <= n; start++) {
            for (int end = 1; end <= n; end++) {
                if (start == end) {
                    continue;
                }
                for (int mid = 1; mid <= n; mid++) {
                    // A가 B에게 이기고 B가 C에게 이기면 A가 C에게 이긴다는 결과를 기록한다.
                    if (winAndLose[start][mid] == WIN && winAndLose[mid][end] == WIN) {
                        winAndLose[start][end] = WIN;
                        winAndLose[end][start] = LOSE;
                    }
                    // A가 B에게 지고 B가 C에게 지면 A가 C에게 진다는 결과를 기록한다.
                    if (winAndLose[start][mid] == LOSE && winAndLose[mid][end] == LOSE) {
                        winAndLose[start][end] = LOSE;
                        winAndLose[end][start] = WIN;
                    }
                }
            }
        }
        // 결과가 0(이기고 지는 관계를 모름)인 관계가 자신 뿐이라면 자신의 순위를 알 수 있다.
        for (int i = 1; i <= n; i++) {
            int count = 0;
            for (int j = 1; j <= n; j++) {
                if (winAndLose[i][j] == 0) {
                    count++;
                } 
            }
            if (count == 1) {
                answer++;
            }
        }
        return answer;
    }
}