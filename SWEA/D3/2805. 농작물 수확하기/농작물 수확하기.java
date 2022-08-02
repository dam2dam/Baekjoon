import java.io.FileInputStream;
import java.util.Scanner;
 
public class Solution {
    public static void main(String[] args) throws Exception {
//      System.setIn(new FileInputStream("input.txt")); 
        Scanner scanner = new Scanner(System.in);
         
        // 테스트 케이스 수 입력
        int T = scanner.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            int n = scanner.nextInt();
             
            // 농장 입력
            int[][] farm = new int[n][n];
            for (int i = 0; i < n; i++) {
                String s = scanner.next();
                for (int j = 0; j < n; j++)
                    farm[i][j] = s.charAt(j) - '0';
            }
             
            //현재 좌표
            int x = 0;
            int y = 0;
             
            //대칭 범위를 위한 변수
            int d = 0; 
             
            //농작물을 수확하여 얻을 수 있는 수익
            int profit = 0;
             
            //마름모 윗부분 : 0 ~ n/2 번째 줄
            for (int i = 0; i <= n/2; i++) {
                for (int j = n/2-d; j <= n/2+d; j++)
                    if (j>=0 && j<n)
                        profit += farm[i][j];
                d++;
            }
            d-=2; //대칭범위 조절
             
            //마름모 아랫부분 : n/2+1 ~ n 번째 줄
            for (int i = n/2+1; i < n; i++) {
                for (int j = n/2-d; j <= n/2+d; j++)
                    if (j>=0 && j<n)
                        profit += farm[i][j];
                d--;
            }
            System.out.printf("#%d %d\n", tc, profit);
        }
        scanner.close();
    }
}