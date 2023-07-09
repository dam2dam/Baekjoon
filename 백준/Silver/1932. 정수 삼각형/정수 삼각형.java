import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		int n = Integer.parseInt(bufferedReader.readLine());
		int[][] dp = new int[n][n + 1];
		dp[0][1] = Integer.parseInt(bufferedReader.readLine());
		int max = 0;
		if (n == 1) {
			max = dp[0][1];
		}
		for (int i = 1; i < n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 1; j <= i + 1; j++) {
				dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + Integer.parseInt(stringTokenizer.nextToken());
				if (i == n - 1) {
					max = Math.max(max, dp[i][j]);
				}
			}
		}
		System.out.println(max);
		bufferedReader.close();
	}
}
