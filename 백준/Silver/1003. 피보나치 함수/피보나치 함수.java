import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static final int ZERO_CALL = 0;
	static final int ONE_CALL = 1;
	static final String BLANK = " ";
	static final String ENTER = "\n";

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder stringBuilder = new StringBuilder();

		int T = Integer.parseInt(bufferedReader.readLine());
		int[] testCase = new int[T];

		int max = 0;
		for (int tc = 0; tc < T; tc++) {
			testCase[tc] = Integer.parseInt(bufferedReader.readLine());
			if (testCase[tc] > max) {
				max = testCase[tc];
			}
		}
		int[][] fibonacci = new int[max + 1][2];
		for (int number = 0; number <= max; number++) {
			if (number == 0) {
				fibonacci[number][ZERO_CALL] = 1;
				continue;
			}
			if (number == 1) {
				fibonacci[number][ONE_CALL] = 1;
				continue;
			}
			fibonacci[number][ZERO_CALL] = fibonacci[number - 2][ZERO_CALL] + fibonacci[number - 1][ZERO_CALL];
			fibonacci[number][ONE_CALL] = fibonacci[number - 2][ONE_CALL] + fibonacci[number - 1][ONE_CALL];
		}
		for (int index = 0; index < T; index++) {
			stringBuilder
				.append(fibonacci[testCase[index]][ZERO_CALL])
				.append(BLANK)
				.append(fibonacci[testCase[index]][ONE_CALL])
				.append(ENTER);
		}
		System.out.println(stringBuilder);
		bufferedReader.close();
	}
}
