import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final int MAX = 1000;
	static final int FIRST = 0;
	static final int SECOND = 1;
	static final int THIRD = 2;
	static final String DOT = ". ";
	static final String ENTER = "\n";

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		StringBuilder stringBuilder = new StringBuilder();

		int N;
		int testCase = 0;
		while ((N = Integer.parseInt(bufferedReader.readLine())) != 0) {
			testCase++;
			int[][] dp = new int[N][3];
			for (int i = 0; i < N; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				for (int j = 0; j < 3; j++) {
					dp[i][j] = Integer.parseInt(stringTokenizer.nextToken());
				}
			}
			dp[0][0] = MAX;
			dp[0][2] += dp[0][1];
			for (int i = 1; i < N; i++) {
				dp[i][FIRST] += Math.min(dp[i - 1][FIRST], dp[i - 1][FIRST + 1]);
				dp[i][SECOND] += Math.min(dp[i - 1][SECOND],
					Math.min(dp[i - 1][SECOND + 1], Math.min(dp[i - 1][SECOND - 1], dp[i][SECOND - 1])));
				dp[i][THIRD] += Math.min(dp[i - 1][THIRD], Math.min(dp[i - 1][THIRD - 1], dp[i][THIRD - 1]));
			}
			stringBuilder.append(testCase).append(DOT)
				.append(dp[N - 1][SECOND]).append(ENTER);
		}
		System.out.println(stringBuilder);
		bufferedReader.close();
	}
}
