import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static final int NOTHING = 0;
	static final int LEFT = 1;
	static final int RIGHT = 2;
	static final int MOD = 9901;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bufferedReader.readLine());
		int[][] dp = new int[N][3];
		Arrays.fill(dp[0], 1);
		for (int i = 1; i < N; i++) {
			dp[i][NOTHING] = (dp[i - 1][NOTHING] + dp[i - 1][LEFT] + dp[i - 1][RIGHT]) % MOD;
			dp[i][LEFT] = (dp[i - 1][NOTHING] + dp[i - 1][RIGHT]) % MOD;
			dp[i][RIGHT] = (dp[i - 1][NOTHING] + dp[i - 1][LEFT]) % MOD;
		}
		int answer = 0;
		for (int i = 0; i < 3; i++) {
			answer += dp[N - 1][i];
		}
		System.out.println(answer % MOD);
		bufferedReader.close();
	}
}
