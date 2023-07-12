import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		int N = Integer.parseInt(stringTokenizer.nextToken());
		int K = Integer.parseInt(stringTokenizer.nextToken());
		int[] dp = new int[K + 1];
		dp[0] = 1;
		int coin;
		for (int i = 0; i < N; i++) {
			coin = Integer.parseInt(bufferedReader.readLine());
			for (int value = coin; value <= K; value++) {
				dp[value] += dp[value - coin];
			}
		}
		System.out.println(dp[K]);
		bufferedReader.close();
	}
}
