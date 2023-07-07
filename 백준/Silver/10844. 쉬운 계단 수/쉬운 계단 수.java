import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static final long MOD = 1_000_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bufferedReader.readLine());
		long[][] stairNumber = new long[N + 1][10];
		Arrays.fill(stairNumber[1], 1);
		stairNumber[1][0] = 0;
		for (int i = 2; i <= N; i++) {
			stairNumber[i][0] = stairNumber[i - 1][1] % MOD;
			for (int j = 1; j < 9; j++) {
				stairNumber[i][j] = (stairNumber[i - 1][j - 1] + stairNumber[i - 1][j + 1]) % MOD;
			}
			stairNumber[i][9] = stairNumber[i - 1][8] % MOD;
		}
		long answer = 0;
		for (int i = 0; i < 10; i++) {
			answer += stairNumber[N][i];
		}
		System.out.println(answer % MOD);
		bufferedReader.close();
	}
}
