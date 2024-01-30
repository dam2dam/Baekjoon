import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        Arrays.sort(times); // 이분탐색 전 정렬
        
        long min = 1;   // 최소 시간
        long max = (long) n * times[times.length - 1]; // 최대 시간
        long mid; 
        long count; // 심사가능인원
        while (min < max) {
            mid = (min + max) / 2;
            
            count = 0;
            for (int i = 0; i < times.length; i++) {
                count += mid / times[i];    // 심사가능인원 += (총 심사시간 / 심사대 별 심사시간)
            } 
            
            if (count >= n) {   // 심사가능인원이 대기인원보다 많거나 같으면 하한선 찾기
                max = mid;
            } else {    // 심사가능인원이 대기인원보다 적으면 상한선 찾기
                min = mid + 1;
            }
        }
        answer = (min + max) / 2;
        return answer;
    }
}