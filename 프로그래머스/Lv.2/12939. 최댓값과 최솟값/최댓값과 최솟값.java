import java.util.*;

class Solution {
    public String solution(String s) {
        String answer = "";
        
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        StringTokenizer st = new StringTokenizer(s);
        int num = 0;

        while(st.hasMoreTokens()) {
            
            num = Integer.parseInt(st.nextToken());
            
            if (min > num) {
                min = num;
            } 
            if (max < num) {
                max = num;
            }
        }
        answer = min + " " + max;
        return answer;
    }
}