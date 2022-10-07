
	/*
	
	[5643] 키 순서
	1번부터 N번까지 번호가 붙여져 있는 학생들에 대하여 두 학생끼리 키를 비교한 결과의 일부가 주어져 있다.
	단, N명의 학생들의 키는 모두 다르다고 가정한다.
	이 비교 결과로부터 모든 학생 중에서 키가 가장 작은 학생부터 자신이 몇 번째인지 알 수 있는 학생들도 있고 그렇지 못한 학생들도 있다
	학생들의 키를 비교한 결과가 주어질 때, 자신의 키가 몇 번째인지 알 수 있는 학생들이 모두 몇 명인지 계산하여 출력하는 프로그램을 작성하시오.
	
	자신이 키가 몇 번째인지 알 수 있는 학생이 모두 몇 명인지를 출력한다.
	
	1. 두 학생의 키를 비교한 결과를 입력받아 인접 리스트로 저장하고 연결된 학생들을 기록할 connection배열을 생성한다
	2. 모든 학생들을 각각 시작점으로 하여 다른 학생들과 직접 또는 간접적으로 연결 관계를 가졌는지 탐색한다
	3. 연결 관계를 가진 학생들을 기록하고, 다른 모든 학생들과 연결 관계가 있는 학생을  count하여 출력한다
	
	*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	
	final static int CONNECT = 1;
	
	static int T, N, M;
	static ArrayList<Integer>[] graph;
	static int[][] connection;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		
		// 입력1 : 테스트 케이스의 수
		T = Integer.parseInt(bufferedReader.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			// 입력2 : N:학생들의 수, M:두 학생의 키를 비교한 횟수
			N = Integer.parseInt(bufferedReader.readLine());
			M = Integer.parseInt(bufferedReader.readLine());

			// 인접 리스트의 각 인덱스 초기화
			graph = new ArrayList[N+1];
			for (int i = 0; i < graph.length; i++)
				graph[i] = new ArrayList<Integer>();
			
			connection = new int[N+1][N+1];
			for (int i = 0; i < M; i++) {
				// 입력 3 : 두 학생의 키를 비교한 결과를 입력받아 인접 리스트로 저장
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				int from = Integer.parseInt(stringTokenizer.nextToken());
				int to = Integer.parseInt(stringTokenizer.nextToken());
				
				graph[from].add(to);
				
				// 연결된 학생들을 기록할 connection배열
				connection[from][to] = CONNECT;
			}
			
			// 모든 학생들을 시작점으로 하는 탐색 시작
			for (int i = 1; i <= N; i++) {
				visited = new boolean[N+1];
				dfs(i, i);
			}
			
			int studentCount = 0;
			for (int i = 1; i < connection.length; i++) {
				int connectionCount = 0;
				for (int j = 1; j < connection[i].length; j++) 
					// 몇 명의 학생들과 연결 관계를 알 수 있는지 count
					if (connection[i][j] == CONNECT) 
						connectionCount++;

				// 다른 모든 학생들과 연결 관계가 있어 자신의 키가 몇 번째인지 알 수 있는 학생 count
				if (connectionCount == N) 
					studentCount++;
			}
			
			// 출력 : 자신이 키가 몇 번째인지 알 수 있는 학생 수
			System.out.printf("#%d %d\n", tc, studentCount);
		}
		bufferedReader.close();
	}

	/**
	 * 학생들의 연결 관계를 탐색하는 함수
	 * @param start	연결 관계를 알아볼 학생
	 * @param current 연결 관계를 알아볼 학생과 연결 된 현재 학생
	 */
	private static void dfs(int start, int current) {
		// 방문 체크
		visited[current] = true;
		
		// 연결 관계를 알아보려는 학생과 연결되어 있는 현재 학생의 연결 관계 기록
		connection[start][current] = connection[current][start] = CONNECT;
		
		// 현재 학생과 연결된 학생을 탐색
		for (int i = 0; i < graph[current].size(); i++) {
			if (visited[graph[current].get(i)] == true) 
				continue;
			
			dfs(start, graph[current].get(i));
		}
	}
	
}
