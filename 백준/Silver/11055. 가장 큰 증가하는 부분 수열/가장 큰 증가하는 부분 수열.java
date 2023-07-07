import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		int N = Integer.parseInt(bufferedReader.readLine());
		int[] numbers = new int[N + 1];
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for (int i = 1; i <= N; i++) {
			numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
		}
		int[] dp = new int[N + 1];
		int max = 0;
		for (int i = 1; i <= N; i++) {
			dp[i] = numbers[i];
			for (int j = 1; j < i; j++) {
				if (numbers[i] > numbers[j]) {
					dp[i] = Math.max(dp[j] + numbers[i], dp[i]);
				}
			}
			max = Math.max(max, dp[i]);
		}
		System.out.println(max);
		bufferedReader.close();
	}
}
