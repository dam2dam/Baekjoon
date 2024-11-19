import java.util.*;

class Solution {
    
    static boolean[] prime;
    
    public int solution(int n) {
        prime = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            prime[i] = true;
        }
        for (int i = 2; i * i <= n; i++) {
             if (!isPrime(i)) {
                 continue;
             }
            for (int j = i * i; j <= n; j += i) {
                prime[j] = false;
            }
        }
        int answer = 0;
        for (int i = 2; i <= n; i++) {
            if (!isPrime(i)) {
                answer++;
            }
        }
        return answer;
    }
    static boolean isPrime(int n) {
        return prime[n];
    }
}