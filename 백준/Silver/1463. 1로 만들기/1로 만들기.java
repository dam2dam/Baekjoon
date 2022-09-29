
	/*
	
	[1463] 1로 만들기
	정수 X에 사용할 수 있는 연산은 다음과 같이 세 가지 이다.
		1) X가 3으로 나누어 떨어지면, 3으로 나눈다.
		2) X가 2로 나누어 떨어지면, 2로 나눈다.
		3) 1을 뺀다.
	정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다. 연산을 사용하는 횟수의 최솟값을 출력하시오.
	
	첫째 줄에 연산을 하는 횟수의 최솟값을 출력한다.
	
	1. 1은 연산횟수가 0이므로 2부터 연산횟수를 dp배열에 저장한다
		1-1. 일단 1을 빼는 연산을 수행한다
		1-2. 2로 나누어 떨어지면 2로 나누고 1회를 더한 후, 위의 1을 빼는 연산과 횟수를 비교하여 최솟값을 갱신한다
		1-3. 3으로 나누어 떨어지면 3으로 나누고 1회를 더한 후, 위에서 갱신된 최솟값과 횟수를 비교하여 최솟값을 갱신한다
	2. 연산을 하는 횟수의 최솟값인 dp배열에 마지막 인덱스 값을 출력한다
	
	*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int N;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
		// 입력 : 연산할 정수
		N = Integer.parseInt(bufferedReader.readLine());
		
		// 각 수의 최소 연산을 저장할 배열
		dp = new int[N+1];
		
		for (int i = 2; i <= N; i++) {
			
			// 1을 빼는 연산(+1회)
			dp[i] = dp[i - 1] + 1;
			
			// 2로 나누어 떨어지면, 2로 나누는 연산을 한 후(+1회), 위의 연산을 끝낸 횟수와 비교하여 최소 횟수 저장
			if (i % 2 == 0)
				dp[i] = Math.min(dp[i], dp[i / 2] + 1);
			
			// 3으로 나누어 떨어지면, 3으로 나누는 연산을 한 후(+1회), 위의 연산을 끝낸 횟수와 비교하여 최소 횟수 저장
			if (i % 3 == 0)
				dp[i] = Math.min(dp[i], dp[i / 3] + 1);
		}
		
		// 출력 : 연산을 하는 횟수의 최솟값
		System.out.println(dp[N]);
	}
}
