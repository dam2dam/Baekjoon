import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static final String LINE_CHANGE = "\n";
	static final String PLUS = "+";
	static final String MINUS = "-";
	static final String BLANK = " ";
	static int N;
	static int[] numbers;
	static StringBuilder answer;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		answer = new StringBuilder();

		int T = Integer.parseInt(bufferedReader.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(bufferedReader.readLine());

			search(1, "1", 1, PLUS);
			answer.append(LINE_CHANGE);
		}
		System.out.println(answer);
		bufferedReader.close();
	}

	static void search(int number, String expression, int count, String symbol) {
		if (count == N) {
			if (number == 0) {
				answer.append(expression).append(LINE_CHANGE);
			}
			return;
		}
		int next = count + 1;
		if (symbol.equals(PLUS)) {
			search((number - count) + (count * 10 + next), expression + BLANK + next, count + 1, BLANK);
		} else if (symbol.equals(MINUS)) {
			search((number + count) - (count * 10 + next), expression + BLANK + next, count + 1, BLANK);
		}
		search(number + next, expression + PLUS + next, count + 1, PLUS);
		search(number - next, expression + MINUS + next, count + 1, MINUS);
	}
}
