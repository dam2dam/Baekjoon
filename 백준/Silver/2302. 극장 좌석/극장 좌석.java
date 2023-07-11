import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bufferedReader.readLine());
		int M = Integer.parseInt(bufferedReader.readLine());
		int[] dp = new int[N + 1];
		dp[0] = dp[1] = 1;
		for (int i = 2; i <= N; i++) {
			dp[i] = dp[i - 2] + dp[i - 1];
		}
		int answer = 1;
		int previousVip = 0;
		int vip;
		for (int i = 0; i < M; i++) {
			vip = Integer.parseInt(bufferedReader.readLine());
			answer *= dp[vip - 1 - previousVip];    // (vip로 나뉜) 각 구간의 경우의 수의 곱
			previousVip = vip;
		}
		answer *= dp[N - previousVip];
		System.out.println(answer);
		bufferedReader.close();
	}
}
