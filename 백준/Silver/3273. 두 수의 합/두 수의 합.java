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
		int[] numbers = new int[N];
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
		}
		int X = Integer.parseInt(bufferedReader.readLine());

		Arrays.sort(numbers);
		int pairCount = 0;
		int start = 0;
		int end = N - 1;
		while (start < end) {
			if (numbers[start] + numbers[end] > X) {
				end--;
				continue;
			}
			if (numbers[start] + numbers[end] < X) {
				start++;
				continue;
			}
			pairCount++;
			start++;
			end--;
		}
		System.out.println(pairCount);
		bufferedReader.close();
	}
}
