	/*
	
	[10026] 적록색약
	적록색약은 빨간색과 초록색의 차이를 거의 느끼지 못한다.
	크기가 N×N인 그리드의 각 칸에 R(빨강), G(초록), B(파랑) 중 하나를 색칠한 그림이 있다.
	그림은 몇 개의 구역으로 나뉘어져 있는데, 구역은 같은 색으로 이루어져 있다. 
	또, 같은 색상이 상하좌우로 인접해 있는 경우에 두 글자는 같은 구역에 속한다.
	그림이 입력으로 주어졌을 때, 적록색약인 사람이 봤을 때와 아닌 사람이 봤을 때 구역의 수를 구하는 프로그램을 작성하시오.
	
	적록색약이 아닌 사람이 봤을 때의 구역의 개수와 적록색약인 사람이 봤을 때의 구역의 수를 공백으로 구분해 출력한다.
	
	1. 크기가 n*n인 그림을 입력받아 2차원 배열에 저장한다
	2. 적록색약이 아닌 사람이 보는 구역과 적록색약인 사람이 보는 구역을 따로 세는 함수를 dfs로 구현한다 : notColorWeakness(), colorWeakness()
	3. 각각 볼 수 있는 구역을 count하여 출력한다
	
	*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static boolean[][] colorWeaknesscheck;
	static boolean[][] notColorWeaknesscheck;
	final static int[] deltaR = {-1, 1, 0, 0};
	final static int[] deltaC = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		
		// 입력1 : 크기가 n*n인 그림을 배열에 저장
		int n = Integer.parseInt(bufferedReader.readLine());
		char[][] picture = new char[n][n];
		for (int i = 0; i < n; i++) {
			 stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			 String input = stringTokenizer.nextToken();
			 for (int j = 0; j < n; j++) 
				picture[i][j] = input.charAt(j);
		}
		
		notColorWeaknesscheck = new boolean[n][n];
		int notColorWeaknessCount = 0;
		colorWeaknesscheck = new boolean[n][n];
		int colorWeaknessCount = 0;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (notColorWeaknesscheck[i][j] == false) {	// 적록색약이 아닌 사람이 봤을 때 구역의 수 count
					notColorWeakness(picture, n, i, j);
					notColorWeaknessCount++;
				}
				if (colorWeaknesscheck[i][j] == false) {	// 적록색약인 사람이 봤을 때 구역의 수 count 
					colorWeakness(picture, n, i, j);
					colorWeaknessCount++;
				}
			}
		}
		// 출력
		System.out.println(notColorWeaknessCount+" "+colorWeaknessCount);
		bufferedReader.close();
	}
	
	static void notColorWeakness(char[][] picture, int n, int x, int y) {
		notColorWeaknesscheck[x][y] = true;
		for (int d = 0; d < 4; d++) {
			int nr = x + deltaR[d];
			int nc = y + deltaC[d];
			if (0<=nr && nr<n && 0<=nc && nc<n && notColorWeaknesscheck[nr][nc] == false)
				if (picture[x][y] == picture[nr][nc])	// 같은 색인 경우만 탐색
					notColorWeakness(picture, n, nr, nc);
		}
	}
	static void colorWeakness(char[][] picture, int n, int x, int y) {
		colorWeaknesscheck[x][y] = true;
		for (int d = 0; d < 4; d++) {
			int nr = x + deltaR[d];
			int nc = y + deltaC[d];
			if (0<=nr && nr<n && 0<=nc && nc<n && colorWeaknesscheck[nr][nc] == false)
				if (picture[x][y] == picture[nr][nc]	// 같은 색이거나, 빨강-초록 구분없이 탐색
						|| (picture[x][y] == 'R' && picture[nr][nc] == 'G') 
						|| (picture[x][y] == 'G' && picture[nr][nc] == 'R'))
					colorWeakness(picture, n, nr, nc);
		}
	}
	
}
