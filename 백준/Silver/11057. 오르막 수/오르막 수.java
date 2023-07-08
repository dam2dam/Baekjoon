import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static final int MOD = 10_007;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bufferedReader.readLine());
		int[][] dp = new int[N][10];
		Arrays.fill(dp[0], 1);
		int answer = 10;
		for (int i = 1; i < N; i++) {
			dp[i][0] = 1;
			answer++;
			for (int j = 1; j < 10; j++) {
				dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % MOD;
				answer += dp[i][j];
			}
			answer -= dp[i][9];
		}
		System.out.println(answer % MOD);
		bufferedReader.close();
	}
}
