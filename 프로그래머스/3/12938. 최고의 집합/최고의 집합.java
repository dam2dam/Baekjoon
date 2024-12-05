class Solution {
    public int[] solution(int n, int s) {
        int[] answer = {};
        
        int quotient = s / n;
        if (quotient > 0) {
            int remainder = s % n;
            answer = new int[n];
            for (int i = n - 1; i >= 0; i--) {
                if (remainder-- > 0) {
                    answer[i] = quotient + 1;
                } else {
                    answer[i] = quotient;
                }
            }
        } else {
            answer = new int[]{-1};
        }
        return answer;
    }
}