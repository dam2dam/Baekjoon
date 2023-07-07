import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static final String ENTER = "\n";

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bufferedReader.readLine());
		int[] testCase = new int[T];
		int max = 0;
		for (int tc = 0; tc < T; tc++) {
			testCase[tc] = Integer.parseInt(bufferedReader.readLine());
			if (testCase[tc] > max) {
				max = testCase[tc];
			}
		}
		long[] dp = new long[max + 1];
		for (int i = 1; i <= max; i++) {
			if (i < 3) {
				dp[i] = 1;
				continue;
			}
			dp[i] = dp[i - 3] + dp[i - 2];
		}

		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < T; i++) {
			stringBuilder.append(dp[testCase[i]]).append(ENTER);
		}
		System.out.println(stringBuilder);
		bufferedReader.close();
	}
}
