import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final int END_POINT = -1;
	static final String BLANK = " ";
	static final String BRACKET_OPEN = "(";
	static final String BRACKET_CLOSE = ")";
	static final String COMMA = ",";
	static final String EQUAL_SIGN = "=";
	static final String FUCNTION_NAME = "w";
	static final String ENTER = "\n";

	static int a, b, c, answer;
	static int[][][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		StringBuilder stringBuilder = new StringBuilder();

		dp = new int[21][21][21];
		while (true) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			a = Integer.parseInt(stringTokenizer.nextToken());
			b = Integer.parseInt(stringTokenizer.nextToken());
			c = Integer.parseInt(stringTokenizer.nextToken());

			if (a == END_POINT && b == END_POINT && c == END_POINT) {
				break;
			}

			answer = w(a, b, c);

			stringBuilder
				.append(FUCNTION_NAME).append(BRACKET_OPEN)
				.append(a).append(COMMA).append(BLANK)
				.append(b).append(COMMA).append(BLANK)
				.append(c).append(BRACKET_CLOSE).append(BLANK)
				.append(EQUAL_SIGN).append(BLANK)
				.append(answer)
				.append(ENTER);
		}
		System.out.println(stringBuilder);
		bufferedReader.close();
	}

	private static int w(int a, int b, int c) {

		if (rangeCheck(a, b, c) && dp[a][b][c] != 0) {
			return dp[a][b][c];
		}

		if (a <= 0 || b <= 0 || c <= 0) {
			return dp[0][0][0] = 1;
		}

		if (a > 20 || b > 20 || c > 20) {
			return dp[20][20][20] = w(20, 20, 20);
		}

		if (a < b && b < c) {
			return dp[a][b][c] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);
		}

		return dp[a][b][c] = w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1);
	}

	private static boolean rangeCheck(int a, int b, int c) {
		return a >= 0 && b >= 0 && c >= 0 && a <= 20 && b <= 20 && c <= 20;
	}

}
