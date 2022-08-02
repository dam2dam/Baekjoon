import java.io.FileInputStream;
import java.util.Scanner;
 
public class Solution {
 
    public static void main(String[] args) throws Exception {
//      System.setIn(new FileInputStream("input.txt"));
        Scanner scanner = new Scanner(System.in);
        for (int t = 0; t < 10; t++) { //테스트 케이스
            int tc = scanner.nextInt();
             
            //사다리 입력
            int[][] ladder = new int[100][100]; 
            for (int i = 0; i < 100; i++)
                for (int j = 0; j < 100; j++)
                    ladder[i][j] = scanner.nextInt();
 
            //현재좌표
            int x = 0;
            int y = 0;
             
            //도착점 찾기
            for (int i = 0; i < 100; i++)
                if(ladder[99][i] == 2) {
                    x = 99;
                    y = i;
                }
 
            //방문체크
            int[][] check = new int[100][100];
             
            //도착점부터 사다리를 거꾸로 올라가기
            while(x >= 0) {
                if(y>=1 && check[x][y-1]==0) //범위를 넘지 않고 방문하지 않은 경우
                    while (y>=1 && ladder[x][y-1]==1) { //왼쪽에 통로가 있으면 가기
                        y--;
                        check[x][y] = 1;
                    }
                if(y<99 && check[x][y+1]==0) //범위를 넘지 않고 방문하지 않은 경우
                    while (y<99 && ladder[x][y+1]==1) { //오른쪽에 통로가 있으면 가기
                        y++;
                        check[x][y] = 1;
                    }
                x--;
            }
            System.out.printf("#%d %d\n", tc, y);
        }
    }
}