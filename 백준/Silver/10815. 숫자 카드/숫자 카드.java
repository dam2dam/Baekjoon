import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static final String BLANK = " ";

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		StringBuilder stringBuilder = new StringBuilder();

		int N = Integer.parseInt(bufferedReader.readLine());
		int[] numbers = new int[N];
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
		}
		Arrays.sort(numbers);
		int M = Integer.parseInt(bufferedReader.readLine());
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		int number;
		for (int i = 0; i < M; i++) {
			number = Integer.parseInt(stringTokenizer.nextToken());
			stringBuilder
				.append(findNumber(numbers, number))
				.append(BLANK);
		}
		System.out.println(stringBuilder);
		bufferedReader.close();
	}

	private static int findNumber(int[] numbers, int number) {
		int start = 0;
		int end = numbers.length - 1;
		int middle;

		while (start <= end) {
			middle = (start + end) / 2;

			if (numbers[middle] < number) {
				start = middle + 1;
			} else if (number < numbers[middle]) {
				end = middle - 1;
			} else {
				return 1;
			}
		}
		return 0;
	}
}
