	/*
	
	[2606] 바이러스
	한 컴퓨터가 웜 바이러스에 걸리면 그 컴퓨터와 네트워크 상에서 연결되어 있는 모든 컴퓨터는 웜 바이러스에 걸리게 된다.
	어느 날 1번 컴퓨터가 웜 바이러스에 걸렸다. 
	컴퓨터의 수와 네트워크 상에서 서로 연결되어 있는 정보가 주어질 때, 1번 컴퓨터를 통해 웜 바이러스에 걸리게 되는 컴퓨터의 수를 출력하는 프로그램을 작성하시오.
	
	1번 컴퓨터가 웜 바이러스에 걸렸을 때, 1번 컴퓨터를 통해 웜 바이러스에 걸리게 되는 컴퓨터의 수를 첫째 줄에 출력한다.
	
	1. 컴퓨터 네트워크 인접 행렬을 2차원 배열에 저장
	2. 1번 컴퓨터와 연결된 컴퓨터를 dfs로 탐색
	3. 1번 컴퓨터를 통해 바이러스에 걸리게 되는 컴퓨터의 수(1번 제외)를 count하여 출력
	
	*/

import java.util.Scanner;

public class Main {
	static int[][] computers;
	static int[] check;
	static int n;
	static int m;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		// 입력1 : n:컴퓨터의 수, m:연결된 컴퓨터 쌍의 수
		n = scanner.nextInt();
		m = scanner.nextInt();
		
		// 연결된 컴퓨터 네트워크를 배열에 저장
		computers = new int[n+1][n+1];
		for (int i = 0; i < m; i++) {
			// 입력2 : 한 줄에 한 쌍씩 네트워크 상에서 직접 연결되어 있는 컴퓨터의 번호 쌍
			int u = scanner.nextInt();
			int v = scanner.nextInt();
			
			computers[u][v] = 1;
			computers[v][u]	= 1;
		}
		// 방문 체크 배열
		check = new int[n+1];
		
		for (int j = 1; j <= n; j++)
			if(check[1] == 0 && computers[1][j] == 1) {
				dfs(1); // 1번 컴퓨터와 연결된 컴퓨터 탐색
				break;
			}

		int virusCount = 0;
		for (int i = 1; i <= n; i++)
			if (check[i] == 1)
				virusCount++; // 1번 컴퓨터와 연결된 컴퓨터 수 count
		
		// 출력 : 1번(본인)을 제외한 웜바이러스 감염 컴퓨터
		System.out.println(virusCount-1);
	}
	
	static void dfs(int index) {
		check[index] = 1;
		for (int j = 1; j <= n; j++)
			if(computers[index][j] == 1 && check[j] == 0)
				dfs(j);
	}
}
