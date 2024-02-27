import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static final int LEFT = 0;
	static final int MID = 1;
	static final int RIGHT = 2;
	static final String BLANK = " ";
	static int N;
	static int[][] maxBoard, minBoard;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		StringBuilder answer = new StringBuilder();

		N = Integer.parseInt(bufferedReader.readLine());
		maxBoard = new int[N + 1][3];
		minBoard = new int[N + 1][3];

		int num1, num2, num3;
		for (int i = 1; i <= N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			num1 = Integer.parseInt(stringTokenizer.nextToken());
			num2 = Integer.parseInt(stringTokenizer.nextToken());
			num3 = Integer.parseInt(stringTokenizer.nextToken());
			calculateMax(i, num1, num2, num3);
			calculateMin(i, num1, num2, num3);
		}
		answer.append(Arrays.stream(maxBoard[N]).max().getAsInt())
			.append(BLANK)
			.append(Arrays.stream(minBoard[N]).min().getAsInt());

		System.out.println(answer);
		bufferedReader.close();
	}

	/**
	 * 최대 점수를 계산하고 기록한다
	 */
	static void calculateMax(int index, int number1, int number2, int number3) {

		int maxNumber1 = maxBoard[index - 1][LEFT];
		int maxNumber2 = maxBoard[index - 1][MID];
		int maxNumber3 = maxBoard[index - 1][RIGHT];

		int max1, max2, max3;
		
		max1 = Math.max(maxNumber1, maxNumber2);
		maxBoard[index][LEFT] = max1 + number1;

		max2 = Math.max(Math.max(maxNumber1, maxNumber2), maxNumber3);
		maxBoard[index][MID] = max2 + number2;

		max3 = Math.max(maxNumber2, maxNumber3);
		maxBoard[index][RIGHT] = max3 + number3;
	}

	/**
	 * 최소 점수를 계산하고 기록한다
	 */
	static void calculateMin(int index, int number1, int number2, int number3) {

		int minNumber1 = minBoard[index - 1][LEFT];
		int minNumber2 = minBoard[index - 1][MID];
		int minNumber3 = minBoard[index - 1][RIGHT];

		int min1, min2, min3;

		min1 = Math.min(minNumber1, minNumber2);
		minBoard[index][LEFT] = min1 + number1;

		min2 = Math.min(Math.min(minNumber1, minNumber2), minNumber3);
		minBoard[index][MID] = min2 + number2;

		min3 = Math.min(minNumber2, minNumber3);
		minBoard[index][RIGHT] = min3 + number3;
	}
}
