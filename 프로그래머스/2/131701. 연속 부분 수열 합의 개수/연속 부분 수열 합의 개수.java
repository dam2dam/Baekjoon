import java.util.*;

class Solution {
    public int solution(int[] elements) {
        Set<Integer> sums = new HashSet<>();
        
        int N = elements.length;
        int[] numbers = new int[2 * N - 1];
        for (int i = 0; i < N - 1; i++) {
            numbers[i] = elements[i];
            numbers[N + i] = elements[i];
        }
        numbers[N - 1] = elements[N - 1];
        
        int sum;
        int end;
        for (int start = 0; start < N; start++) {
            sum = 0;
            for (int num = 0; num < N; num++) {
                end = start + num;
                sum += numbers[end];
                sums.add(sum);
            }
        }
        return sums.size();
    }
}