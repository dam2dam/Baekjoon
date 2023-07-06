import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		int N = Integer.parseInt(bufferedReader.readLine());
		int[] dp = new int[N + 1];
		int currentNumber;

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for (int i = 1; i <= N; i++) {
			currentNumber = Integer.parseInt(stringTokenizer.nextToken());
			dp[i] = Math.max(dp[i - 1] + currentNumber, currentNumber);
		}
		dp[0] = Integer.MIN_VALUE;
		System.out.println(Arrays.stream(dp).max().getAsInt());
		bufferedReader.close();
	}
}
