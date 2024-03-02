import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final int IMPOSSIBLE = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		int N = Integer.parseInt(stringTokenizer.nextToken());
		int S = Integer.parseInt(stringTokenizer.nextToken());

		int[] numbers = new int[N];
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
		}
		int left = 0;
		int right = 0;
		int sum = numbers[left];
		int min = N + 1;

		while (right < N) {
			// 합이 목표값보다 작거나, 왼쪽 수가 오른쪽 수를 넘어가면
			if (sum < S || left > right) {
				// 오른쪽 수가 맨 끝에 있다면 더이상 부분합을 늘릴 수 없으니 끝내기
				if (right == N - 1) {
					break;
				}
				// 부분합 늘리기
				sum += numbers[++right];
			} else {
				// 합이 목표값과 크거나 같으면
				// 길이 최솟값 갱신 후, 가장 짧은 길이를 구하기 위해 부분합 줄이기
				min = Math.min(min, right - left + 1);
				sum -= numbers[left++];
			}
		}
		// 최솟값이 초기값 그대로라면, 불가능
		if (min == N + 1) {
			System.out.println(IMPOSSIBLE);
		} else {
			System.out.println(min);
		}
		bufferedReader.close();
	}
}
