import java.util.Scanner;

public class Main {
	static int[][] graph;
	static int[] check;
	static int n;
	static int m;
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt(); //정점의 개수
		m = scanner.nextInt(); //간선의 개수
		
		//2차원 배열에 그래프 할당
		graph = new int[1001][1001];
		for (int i = 0; i < m; i++) {
			int u = scanner.nextInt();
			int v = scanner.nextInt();
			
			graph[u][v] = 1;
			graph[v][u] = 1;
		}
		
		check = new int[n+1]; //방문 체크할 배열
		int elementCount = 0; //연결 요소의 개수
		
		//방문하지 않은 정점 방문
		for (int i = 1; i <= n; i++)
			if(check[i] == 0) {
				dfs(i);
				elementCount++;
			}
		
		//연결 요소의 개수 출력
		System.out.println(elementCount); 
		scanner.close();
	}
	
	static void dfs(int index) {
		check[index] = 1; //방문 체크
		for (int j = 1; j <= n; j++) 
			if(check[j] == 0 && graph[index][j] == 1) //방문하지 않았고 연결된 정점 방문
				dfs(j);
		
	}
}
