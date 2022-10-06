
	/*
	
	[3307] 최장 증가 부분 수열
	주어진 두 수열의 최장 증가 부분 수열(Longest Increasing Subsequence)의 길이를 계산하는 프로그램을 작성하시오.
	수열 { A1, A2, ... , AN }의 최장 증가 부분 수열 B는 다음과 같이 정의된다.
	{ B1, B2, ... , BK }에서 0≤K≤N, B1 ≤ B2 ≤ ... ≤ BK이고,
	AB1 ≤ AB2 ≤ ... ≤ ABK인 최대 K로 구성된 수열이다.
	예를 들어, 수열이 { 1, 3, 2, 5, 4, 7 } 이라면, 최장 부분 증가 수열의 길이는 4가 된다.
	
	최장 증가 부분 수열의 길이를 출력한다.
	
	1. 수열을 입력받아 sequence배열에 저장하고 각 인덱스까지의 최장 증가 부분 수열의 길이를 저장할 LIS배열을 생성한다
	2. 각 인덱스마다 본인만 고려한 수열 길이 1로 초기화 후 탐색을 시작한다
	3. 직전까지의 요소를 확인하여 고려할 앞의 요소보다 현재 요소가 크고, 
		'현재까지 고려해본 수열의 길이 값'보다 '현재 고려할 앞의 수열 길이에 현재 요소를 붙인 길이'가 더 길다면 수열의 길이를 갱신한다
	4. LIS배열의 인덱스가 완성될 때마다 최댓값을 갱신하여 모든 탐색 완료 후 최댓값을 출력한다
	
	*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int T, N, max;
	static int[] sequence, LIS;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		
		// 입력1 : 테스트 케이스의 수
		T = Integer.parseInt(bufferedReader.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			// 입력2 : 수열의 길이
			N = Integer.parseInt(bufferedReader.readLine());
			
			// 입력3 : 수열을 입력받아 sequence배열에 저장
			sequence = new int[N];
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int i = 0; i < N; i++) 
				sequence[i] = Integer.parseInt(stringTokenizer.nextToken());
			
			// 각 인덱스까지의 최장 증가 부분 수열의 길이를 저장할 배열
			LIS = new int[N];
			max = 0;
			for (int i = 0; i < N; i++) {
				// 초기화 : 본인만 고려한 수열 길이 1
				LIS[i] = 1;
				// 직전까지의 요소를 확인
				for (int j = 0; j < i; j++)
					// 고려할 앞의 요소보다 현재 요소가 크고, 
					// '현재까지 고려해본 수열의 길이 값'보다 '현재 고려할 앞의 수열 길이에 현재 요소를 붙인 길이'가 더 길다면
					if (sequence[j] < sequence[i] && LIS[i] < LIS[j] + 1) 
						// 수열의 길이 갱신
						LIS[i] = LIS[j] + 1;
				// 수열 길이 배열의 요소가 완성될 때마다 최댓값 갱신
				max = Math.max(max, LIS[i]);
			}
			// 출력 : 최장 증가 부분 수열의 길이
			System.out.printf("#%d %d\n", tc, max);
		}
		bufferedReader.close();
	}
}
