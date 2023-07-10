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
		int[] coins = new int[N];
		for (int i = 0; i < N; i++) {
			coins[i] = Integer.parseInt(bufferedReader.readLine());
		}
		int min = 0;
		for (int i = N - 1; i >= 0; i--) {
			if (K <= 0) {
				break;
			}
			if (coins[i] > K) {
				continue;
			}
			min += K / coins[i];
			K = K % coins[i];
		}
		System.out.println(min);
		bufferedReader.close();
	}
}
