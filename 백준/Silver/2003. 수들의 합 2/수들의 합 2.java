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
		int M = Integer.parseInt(stringTokenizer.nextToken());

		int[] numbers = new int[N];
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
		}

		int start = 0;
		int end = 0;
		int sum = 0;
		int count = 0;
		while (start <= end) {
			// 합이 목표수보다 크거나 같으면 줄이기
			if (sum >= M) {
				sum -= numbers[start++];
			} else if (end == N) {
				// 합이 목표수보다 작은데 끝까지 왔다면 끝내기
				break;
			} else {
				// 합이 목표수보다 작으면 늘리기
				sum += numbers[end++];
			}

			// 합이 목표수와 같으면 count
			if (sum == M) {
				count++;
			}
		}
		System.out.println(count);
		bufferedReader.close();
	}
}
