import java.util.Arrays;

class Solution {
    public int solution(int[] A, int[] B) {
        // A, B 오름차순 정렬
        Arrays.sort(A);
        Arrays.sort(B);
        int N = A.length - 1;
        int score = 0;
        // 큰 수부터 비교하고 B가 크다면 득점, 작거나 같다면 A의 더 작은 수와 비교
        for (int i = N, j = N; i >= 0; i--) {
            if (B[j] > A[i]) {
                j--;
                score++;
            }
        }
        return score;
    }
}