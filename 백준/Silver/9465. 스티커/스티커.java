import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final int LINE_1 = 0;
	static final int LINE_2 = 1;
	static final String ENTER = "\n";

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer line1, line2;
		StringBuilder stringBuilder = new StringBuilder();

		int T = Integer.parseInt(bufferedReader.readLine());
		for (int tc = 0; tc < T; tc++) {
			int n = Integer.parseInt(bufferedReader.readLine());
			int[][] dp = new int[2][n + 1];
			line1 = new StringTokenizer(bufferedReader.readLine());
			line2 = new StringTokenizer(bufferedReader.readLine());

			int stickerofLine1, stickerofLine2;
			for (int column = 1; column <= n; column++) {
				stickerofLine1 = Integer.parseInt(line1.nextToken());
				stickerofLine2 = Integer.parseInt(line2.nextToken());
				if (column == 1) {
					dp[LINE_1][column] = stickerofLine1;
					dp[LINE_2][column] = stickerofLine2;
					continue;
				}
				dp[LINE_1][column] = Math.max(dp[LINE_2][column - 2], dp[LINE_2][column - 1]) + stickerofLine1;
				dp[LINE_2][column] = Math.max(dp[LINE_1][column - 2], dp[LINE_1][column - 1]) + stickerofLine2;
			}
			stringBuilder.append(Math.max(dp[LINE_1][n], dp[LINE_2][n])).append(ENTER);
		}
		System.out.println(stringBuilder);
		bufferedReader.close();
	}
}
