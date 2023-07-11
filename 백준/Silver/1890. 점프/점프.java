import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		int N = Integer.parseInt(bufferedReader.readLine());
		long[][] dp = new long[N][N];
		dp[0][0] = 1;
		int jump;
		for (int r = 0; r < N; r++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int c = 0; c < N; c++) {
				jump = Integer.parseInt(stringTokenizer.nextToken());
				if (jump == 0 || dp[r][c] == 0) {
					continue;
				}
				// 오른쪽
				if (c + jump < N) {
					dp[r][c + jump] += dp[r][c];
				}
				// 아래
				if (r + jump < N) {
					dp[r + jump][c] += dp[r][c];
				}
			}
		}
		System.out.println(dp[N - 1][N - 1]);
		bufferedReader.close();
	}
}
