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
		StringBuilder answer = new StringBuilder();

		int N = Integer.parseInt(bufferedReader.readLine());
		int[] cards = new int[N];
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for (int i = 0; i < N; i++) {
			cards[i] = Integer.parseInt(stringTokenizer.nextToken());
		}
		Arrays.sort(cards);
		int M = Integer.parseInt(bufferedReader.readLine());
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for (int i = 0; i < M; i++) {
			int number = Integer.parseInt(stringTokenizer.nextToken());
			answer.append(upperBound(cards, number) - lowerBound(cards, number)).append(BLANK);
		}
		System.out.println(answer);
		bufferedReader.close();
	}

	static int lowerBound(int[] array, int targetNumber) {
		int low = 0;
		int high = array.length;
		int mid;

		while (low < high) {
			mid = (low + high) / 2;

			if (targetNumber <= array[mid]) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}

	static int upperBound(int[] array, int targetNumber) {
		int low = 0;
		int high = array.length;
		int mid;

		while (low < high) {
			mid = (low + high) / 2;

			if (targetNumber < array[mid]) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return high;
	}
}
