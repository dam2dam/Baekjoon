
	/*
	
	[17070] 파이프 옮기기 1
	오늘은 집 수리를 위해서 파이프 하나를 옮기려고 한다. 파이프는 아래와 같은 형태이고, 2개의 연속된 칸을 차지하는 크기이다.
	파이프는 회전시킬 수 있으며, 아래와 같이 3가지 방향이 가능하다.
	파이프가 벽을 긁으면 안 된다. 즉, 파이프는 항상 빈 칸만 차지해야 한다.
	파이프를 밀 수 있는 방향은 총 3가지가 있으며, →, ↘, ↓ 방향이다. 파이프는 밀면서 회전시킬 수 있다. 
	회전은 45도만 회전시킬 수 있으며, 미는 방향은 오른쪽, 아래, 또는 오른쪽 아래 대각선 방향이어야 한다.
	가장 처음에 파이프는 (1, 1)와 (1, 2)를 차지하고 있고, 방향은 가로이다. 파이프의 한쪽 끝을 (N, N)로 이동시키는 방법의 개수를 구해보자.
	
	첫째 줄에 파이프의 한쪽 끝을 (N, N)으로 이동시키는 방법의 수를 출력한다. 이동시킬 수 없는 경우에는 0을 출력한다.
	
	1. dp[행][열][파이프 방향]
	2. 가로로 놓일 수 있는 경우의 수 : 가로 방향 or 대각선 방향에서 오는 경우
	3. 세로로 놓일 수 있는 경우의 수 : 세로 방향 or 대각선 방향에서 오는 경우
	4. 대각선으로 놓일 수 있는 경우의 수 : 가로 방향 or 세로 방향 or 대각선 방향에서 오는 경우
	
	*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	final static int HORIZONTAL = 0;	// 가로
	final static int VERTICAL = 1;		// 세로
	final static int DIAGONAL = 2;		// 대각선

	static int N;
	static int[][] map;
	static int[][][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		
		// 입력 1 : 집의 크기
		N =Integer.parseInt(bufferedReader.readLine());
		
		// 입력 2 : 집의 상태를 입력받아 map배열에 저장
		map = new int[N+1][N+1];
		for (int[] is : map)
			Arrays.fill(is, 1);	// 0행, 0열 벽으로 처리

		for (int i = 1; i <= N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 1; j <= N; j++)
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
		}
		
		// dp[행][열][파이프 방향]
		dp = new int[N+1][N+1][3];
		// 파이프 초기 상태 : 가로
		dp[1][2][HORIZONTAL] = 1;
		
		for (int i = 1; i <= N; i++) {
			for (int j = 3; j <= N ; j++) {
				
				// 가로로 놓일 수 있는 경우의 수 : 가로 방향 or 대각선 방향에서 오는 경우
				if (map[i][j] != 1 && map[i][j-1] != 1) 
					dp[i][j][HORIZONTAL] = dp[i][j-1][HORIZONTAL] + dp[i][j-1][DIAGONAL];

				// 세로로 놓일 수 있는 경우의 수 : 세로 방향 or 대각선 방향에서 오는 경우
				if (map[i][j] != 1 && map[i-1][j] != 1)
					dp[i][j][VERTICAL] = dp[i-1][j][VERTICAL] + dp[i-1][j][DIAGONAL];
				
				// 대각선으로 놓일 수 있는 경우의 수 : 가로 방향 or 세로 방향 or 대각선 방향에서 오는 경우
				if (map[i][j] != 1 && map[i][j-1] != 1 && map[i-1][j] != 1 && map[i-1][j-1] != 1) 
					dp[i][j][DIAGONAL] = dp[i-1][j-1][HORIZONTAL] + dp[i-1][j-1][VERTICAL] + dp[i-1][j-1][DIAGONAL];
				
			}
		}
		
		// 출력 : 가로 방향, 세로 방향, 대각선 방향에서 온 경우의 합
		System.out.println(dp[N][N][VERTICAL] + dp[N][N][HORIZONTAL] + dp[N][N][DIAGONAL]);
		
		bufferedReader.close();
	}

}
