	/*
	
	[1012] 유기농 배추
	배추들이 모여있는 곳에는 배추흰지렁이가 한 마리만 있으면 되므로 서로 인접해있는 배추들이 몇 군데에 퍼져있는지 조사하면 총 몇 마리의 지렁이가 필요한지 알 수 있다.
	한 배추의 상하좌우 네 방향에 다른 배추가 위치한 경우에 서로 인접해있는 것이다.
	
	각 테스트 케이스에 대해 필요한 최소의 배추흰지렁이 마리 수를 출력한다.
	
	1. 배추의 위치를 2차원 배열에 저장한다
	2. 배추가 있는 장소를 방문하고 인접한 배추를 모두 방문한다 (dfs)
	3. 인접한 배추 방문이 끝나면 그 장소를 count+1 한다
	4. 배추가 몇 군데 퍼져있는지(=필요한 지렁이 수) 출력한다
	
	*/

import java.util.Scanner;

public class Main {
	static int m;
	static int n;
	static int k;
	static int[][] field;
	static int[][] check;
	static int[] deltaR;
	static int[] deltaC;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		// 입력1 : 테스트 케이스의 수
		int T = scanner.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			
			// 입력2 : m: 배추밭의 가로길이, n:배추밭의 세로길이, k: 배추가 심어져있는 위치의 개수
			m = scanner.nextInt();
			n = scanner.nextInt();
			k = scanner.nextInt();
			
			// 입력3 : 배추의 위치 배열에 저장
			field = new int[m][n];
			for (int i = 0; i < k; i++) {
				int x = scanner.nextInt();
				int y = scanner.nextInt();
				field[x][y] = 1;
			}
			
			// 탐색을 위한 델타 배열	(상 하 좌 우 )
			deltaR = new int[]{-1, 1, 0, 0};
			deltaC = new int[]{0, 0, -1, 1};
			
			check = new int[m][n];	// 방문체크 배열
			int count = 0;
			for (int i = 0; i < field.length; i++)
				for (int j = 0; j < field[i].length; j++)
					if (check[i][j] == 0 && field[i][j]==1) {
						find(i, j);
						count++;	// 방문이 끝나면 배추가 퍼져있는 장소 count
					}
			// 출력 : 필요한 최소의 배추흰지렁이 마리 수
			System.out.println(count);
		}
	}
	
	static void find(int r, int c) {
		if (field[r][c] == 1) {
			check[r][c] = 1;	//방문체크
			for (int d = 0; d < 4; d++) {
				int nr = r + deltaR[d];
				int nc = c + deltaC[d];
				if (0<=nr && nr<m && 0<=nc && nc<n)
					if(check[nr][nc] == 0 && field[nr][nc]==1)
						find(nr, nc);	// 인접한 배추 방문
			}
		}
	}
}
