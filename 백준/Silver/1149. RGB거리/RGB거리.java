	
	/*
	
	[1149] RGB거리
	RGB거리에는 집이 N개 있다. 거리는 선분으로 나타낼 수 있고, 1번 집부터 N번 집이 순서대로 있다.
	집은 빨강, 초록, 파랑 중 하나의 색으로 칠해야 한다. 
		1번 집의 색은 2번 집의 색과 같지 않아야 한다.
		N번 집의 색은 N-1번 집의 색과 같지 않아야 한다.
		i(2 ≤ i ≤ N-1)번 집의 색은 i-1번, i+1번 집의 색과 같지 않아야 한다.
	각각의 집을 빨강, 초록, 파랑으로 칠하는 비용이 주어졌을 때, 규칙을 만족하면서 모든 집을 칠하는 비용의 최솟값을 구해보자.
	
	첫째 줄에 모든 집을 칠하는 비용의 최솟값을 출력한다.
	
	1. i-1번 집에서, 'i번 집에 칠하는 색과는 다른 두 가지 색'의 비용을 비교하여 더 작은 값을 i집에 칠하는 색의 비용과 더하고 저장한다
	2. 배열에 비용을 누적하여 저장했기 때문에, N번째 인덱스의 세 가지 색의 값의 최소값이 모든 집을 칠하는 비용의 최솟값이 된다
	
	*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	final static int RED = 0;
	final static int GREEN = 1;
	final static int BLUE = 2;
	
	static int N, answer;
	static int[][] paintCost;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		
		// 입력1 : 집의 수
		N = Integer.parseInt(bufferedReader.readLine());
		
		// 입력2 : 각 집을 빨강, 초록, 파랑으로 칠하는 비용
		paintCost = new int[N+1][3];
		for (int i = 1; i <= N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			
			// i-1번 집에서, i집에 칠하는 색과는 다른 두 가지 색의 비용을 비교하여 i집에 칠하는 색의 비용과 더하고 저장
			paintCost[i][RED] = Integer.parseInt(stringTokenizer.nextToken()) + Math.min(paintCost[i-1][GREEN], paintCost[i-1][BLUE]);
			paintCost[i][GREEN] = Integer.parseInt(stringTokenizer.nextToken()) + Math.min(paintCost[i-1][RED], paintCost[i-1][BLUE]);
			paintCost[i][BLUE] = Integer.parseInt(stringTokenizer.nextToken()) + Math.min(paintCost[i-1][RED], paintCost[i-1][GREEN]);
		}
		
		// 배열에 비용을 누적하여 저장했기 때문에, N번째 인덱스의 세 가지 색의 값의 최소값이 모든 집을 칠하는 비용의 최솟값
		answer = Math.min(Math.min(paintCost[N][RED], paintCost[N][GREEN]), paintCost[N][BLUE]);
		
		// 출력 : 모든 집을 칠하는 비용의 최솟값
		System.out.println(answer);
	}
}
