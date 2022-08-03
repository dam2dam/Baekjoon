import java.util.Scanner;

public class Main {
	static int[][] computers;
	static int[] check;
	static int n;
	static int m;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		// n: 컴퓨터의 수, m: 연결된 컴퓨터 쌍의 수
		n = scanner.nextInt();
		m = scanner.nextInt();
		
		// 연결된 컴퓨터 네트워크를 배열에 저장
		computers = new int[n+1][n+1];
		for (int i = 0; i < m; i++) {
			int u = scanner.nextInt();
			int v = scanner.nextInt();
			
			computers[u][v] = 1;
			computers[v][u]	= 1;
		}
		//방문 체크 배열
		check = new int[n+1];
		
		for (int j = 1; j <= n; j++)
			if(check[1] == 0 && computers[1][j] == 1) {
				dfs(1); //1번 컴퓨터와 연결된 컴퓨터 탐색
				break;
			}

		int virusCount = 0;
		for (int i = 1; i <= n; i++)
			if (check[i] == 1)
				virusCount++; //1번 컴퓨터와 연결된 컴퓨터 수
		
		System.out.println(virusCount-1); //1번(본인)을 제외한 웜바이러스 감염 컴퓨터
	}
	
	static void dfs(int index) {
		check[index] = 1;
		for (int j = 1; j <= n; j++)
			if(computers[index][j] == 1 && check[j] == 0)
				dfs(j);
	}
}
