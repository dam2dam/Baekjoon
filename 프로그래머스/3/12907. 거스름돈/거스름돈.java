import java.util.Arrays;

class Solution {
    
    static final int DIVIDING = 1_000_000_007;
    
    public int solution(int n, int[] money) {
        return (int) (makeChange(n, money) % DIVIDING);
    }
    
    static long makeChange(int n, int[] money) {
        long[] cases = new long[n + 1];
        
        Arrays.sort(money);
        // 초기화 (가장 작은 화폐 지불)
        for (int i = 0; i <= n; i++) {
            if (i % money[0] == 0) {
                cases[i] = 1;
            }
        }
        for (int j = 1; j < money.length; j++) {
            int coin = money[j];
            for (int amount = coin; amount <= n; amount++) {
                cases[amount] += cases[amount - coin];
            }
        }
        return cases[n];
    }
}