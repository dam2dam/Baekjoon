import java.util.ArrayList;

class Solution {
    public int[] solution(int n) {        
        int[][] snail = new int[n][n];
        // 일반화를 위한 초기값 세팅 (1을 (0, 0)에 넣기 위해)
        int r = -1;
        int c = 0;
        int number = 1;
        int N = n;
        while (N > 0) {
            // 삼각형의 왼쪽 변
            for (int i = 0; i < N; i++) {
                snail[++r][c] = number++;
            }
            N--;
            // 삼각형의 아래쪽 변
            for (int i = 0; i < N; i++) {
                snail[r][++c] = number++;
            }
            N--;
            // 삼각형의 오른쪽 변
            for (int i = 0; i < N; i++) {
                snail[--r][--c] = number++;
            }
            N--;
        }
        // 2차원 배열의 행 순서대로 리스트에 채우기
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                list.add(snail[i][j]);
            }
        }
        // 리스트를 배열로 바꾸기
        int[] answer = list.stream()
            .mapToInt(Integer::intValue)
            .toArray();
        return answer;
    }
}