import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(bufferedReader.readLine());
		int[] wines = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			wines[i] = Integer.parseInt(bufferedReader.readLine());
		}

		int[] dp = new int[n + 1];
		dp[1] = wines[1];
		if (n >= 2) {
			dp[2] = wines[1] + wines[2];
		}
		for (int i = 3; i <= n; i++) {
			dp[i] = Math.max(dp[i - 1], Math.max(wines[i] + dp[i - 2], wines[i] + wines[i - 1] + dp[i - 3]));
		}
		System.out.println(dp[n]);
		bufferedReader.close();
	}
}
