	/*
	
	[10971] 외판원 순회 2
	1번부터 N번까지 번호가 매겨져 있는 도시들이 있고, 도시들 사이에는 길이 있다. (길이 없을 수도 있다)
	이제 한 외판원이 어느 한 도시에서 출발해 N개의 도시를 모두 거쳐 다시 원래의 도시로 돌아오는 순회 여행 경로를 계획하려고 한다.
	단, 한 번 갔던 도시로는 다시 갈 수 없다. (맨 마지막에 여행을 출발했던 도시로 돌아오는 것은 예외)
	이런 여행 경로는 여러 가지가 있을 수 있는데, 가장 적은 비용을 들이는 여행 계획을 세우고자 한다.
	N과 비용 행렬이 주어졌을 때, 가장 적은 비용을 들이는 외판원의 순회 여행 경로를 구하는 프로그램을 작성하시오.
	
	첫째 줄에 외판원의 순회에 필요한 최소 비용을 출력한다.
	
	1. 출발 도시를 0으로 정하고 가보지 않았던 도시 중,갈 수 있는 도시를 순회한다 : travelingSalesman()
	2. 모든 도시에 방문했다면 총 여행비용과 처음 도시로 돌아갈 때 발생하는 비용의 합을 최솟값과 비교하여 갱신한다
	3. 모든 여행 경로를 다 가봤다면 외판원 순회의 최소 비용을 출력한다
	
	*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, startCity, min;
	static int[][] cities;
	static boolean[] visit;

	public static void main(String[] args) throws IOException{
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		
		// 입력1 : 도시의 수
		N = Integer.parseInt(bufferedReader.readLine());
		
		// 입력2 : 비용 행렬을 입력받아 2차원 배열에 저장
		cities = new int[N][N];
		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < N; j++)
				cities[i][j] = Integer.parseInt(stringTokenizer.nextToken());
		}
		
		visit = new boolean[N];
		min = Integer.MAX_VALUE;
		
		// 출발하는 도시를 0으로 정하고 방문체크
		startCity = 0;
		visit[startCity] = true;
		
		// 외판원 순회 시작
		travelingSalesman(startCity, 1, 0);
		
		// 출력 : 외판원의 순회에 필요한 최소 비용
		System.out.println(min);
		
		bufferedReader.close();
	}
	
	/**
	 * 도시들을 순회 여행하는 함수
	 * @param currentCity 현재 머무르는 도시
	 * @param visitCitiesCount 현재까지 여행한 도시의 수
	 * @param costSum 현재까지 여행하는 데에 들었던 총 비용
	 */
	static void travelingSalesman(int currentCity, int visitCitiesCount, int costSum) {
		
		// 현재까지 여행 비용이 이미 최솟값을 넘었다면 더이상 탐색하지 않음
		if (costSum > min)
			return;
		
		// 모든 도시를 여행했고, 처음 출발했던 도시로 돌아갈 수 있다면
		if (visitCitiesCount == N && cities[currentCity][startCity] > 0) {
			// 총 여행비용과 돌아가는 비용의 합을 최솟값과 비교하여 갱신
			min = Math.min(min, costSum + cities[currentCity][startCity]);
			return;
		}
		
		// 가보지 않았던 도시 중(방문하지 않은), 갈 수 있는 도시(비용 존재)가 있다면 방문 체크 후 떠남
		for (int nextCity = 0; nextCity < cities.length; nextCity++) {
			if (visit[nextCity] == false && cities[currentCity][nextCity] > 0) {
				visit[nextCity] = true;
				travelingSalesman(nextCity, visitCitiesCount+1, costSum+cities[currentCity][nextCity]);
				visit[nextCity] = false;
				
			}
		}
	}
	
}
