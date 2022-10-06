
	/*
	
	[1263] [S/W 문제해결 응용] 8일차 - 사람 네트워크2
	어떤 사화과학 연구 단체에서 사람의 네트워크에 대하여 여러 가지 측정 방법을 사용하여 연구하기로 하였다.
	이를 위해 우선 주어진 사람 네트워크에서 누가 가장 영향력이 있는 사람인지를 알 수 있는 척도로서 다음을 분석하는 프로그램을 작성하시오.
	단, N은 입력 사람 네트워크 (그래프)의 노드 수이다.
	Closeness Centrality(CC):Closeness는 네트워크 상에서 한 사용자가 다른 모든 사람에게 얼마나 가까운가를 나타낸다.
	따라서 사용자 i의 CC(i)는 다음과 같이 계산된다.
	      CC(i) = ∑ j dist(i,j) 단, dist(i,j)는 노드i로부터 노드 j까지의 최단 거리이다.
	
	주어진 사람 그래프에서 사람들의 CC 값들 중에서 최솟값을 한 줄에 출력한다.
	
	1. 사람 네트워크의 인접행렬을 입력받아 network배열에 저장하고 본인과의 경로가 아니면서 연결되어있지 않다면 임의의 큰 수를 저장한다
	2. 현재까지 고려된 최단 경로보다, 현재 고려할 경유지를 거치는 경로가 더 짧다면 최단 경로를 갱신한다
	3. 모든 경유지를 고려한 결과가 완성되면 각 사용자의 CC값을 구하고 최솟값 갱신하여 출력한다
	
	*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	final static int INF = 9999999;
	static int T, N, sum, min;
	static int[][] network;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		StringBuilder stringBuilder = new StringBuilder();
		
		// 입력1 : 테스트 케이스의 수
		T = Integer.parseInt(bufferedReader.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			// 입력2 : N:사람 수, 사람 네트워크의 인접행렬을 입력받아 network배열에 저장
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			N = Integer.parseInt(stringTokenizer.nextToken());
			network = new int[N][N];
			for (int i = 0; i < N; i++) 
				for (int j = 0; j < N; j++) {
					network[i][j] = Integer.parseInt(stringTokenizer.nextToken());
					
					// 본인과의 경로가 아니고 연결되어있지 않다면 임의의 큰 수 저장
					if (i != j && network[i][j] == 0) 
						network[i][j] = INF;
				}

			// 경유지
			for (int stopover = 0; stopover < N; stopover++) 
				// 출발지 
				for (int start = 0; start < N; start++) {
					
					// 출발지와 경유지가 같다면 고려하지 않음
					if (start == stopover) 
						continue;
					
					// 도착지
					for (int end = 0; end < N; end++) {
						
						// 경유지와 도착지가 같거나 출발지와 도착지가 같다면  고려하지 않음
						if (stopover == end || start == end) 
							continue;
						
						// 현재까지 고려된 최단 경로보다, 현재 고려할 경유지를 거치는 경로가 더 짧다면 갱신
						if (network[start][end] > network[start][stopover] + network[stopover][end]) 
							network[start][end] = network[start][stopover] + network[stopover][end];
					}
				}
			
			sum = 0;
			min = Integer.MAX_VALUE;
			for (int[] is : network) {
				// 각 사용자의 Closeness Centrality 값 구하기
				sum = Arrays.stream(is).sum();
				
				// 각 사용자의 CC값 중 최솟값 갱신
				min = Math.min(min, sum);
			}
			
			// 출력 : 사람들의 CC 값들 중에서 최솟값
			stringBuilder.append("#").append(tc).append(" ").append(min).append("\n");
		}
		System.out.println(stringBuilder.toString());
	}
}
