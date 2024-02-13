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
		int K = Integer.parseInt(stringTokenizer.nextToken());

		int[] sequence = new int[N];
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for (int i = 0; i < N; i++) {
			sequence[i] = Integer.parseInt(stringTokenizer.nextToken());
		}
		// 수열에 들어있는 각 원소의 개수
		int[] numbers = new int[100_001];
		int start = 0;
		int end = 0;
		int max = 0;
		while (end < N) {
			// 현재 부분 수열의 가장 끝 숫자가 한계값보다 적게 쓰였다면
			if (numbers[sequence[end]] < K) {
				// 최댓값 갱신 후 연속 부분 수열 늘리기
				max = Math.max(max, end - start + 1);
				numbers[sequence[end]]++;
				end++;
			} else {
				// 현재 부분 수열의 가장 끝 숫자가 한계값보다 많거나 같게 쓰였다면
				// 연속 부분 수열 줄이기
				numbers[sequence[start]]--;
				start++;
			}
		}
		System.out.println(max);
		bufferedReader.close();
	}
}
