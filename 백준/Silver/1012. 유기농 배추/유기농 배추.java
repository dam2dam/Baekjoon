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
		// 테스트 케이스 입력
		int T = scanner.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			
			// m: 가로길이, n: 세로길이
			m = scanner.nextInt();
			n = scanner.nextInt();
			// k: 배추가 심어져있는 위치의 개수
			k = scanner.nextInt();
			
			//배추밭 입력
			field = new int[m][n];
			for (int i = 0; i < k; i++) {
				int x = scanner.nextInt();
				int y = scanner.nextInt();
				field[x][y] = 1;
			}
			
			// 탐색을 위한 델타 배열  //상하좌우
			deltaR = new int[]{-1, 1, 0, 0};
			deltaC = new int[]{0, 0, -1, 1};
			
			check = new int[m][n];	//방문체크배열
			int count = 0;	//인접한 배추가 몇 군데 퍼져있는지 카운트
			for (int i = 0; i < field.length; i++)
				for (int j = 0; j < field[i].length; j++)
					if (check[i][j] == 0 && field[i][j]==1) {
						find(i, j);
						count++;
					}
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
						find(nr, nc);
			}
		}
	}
}
