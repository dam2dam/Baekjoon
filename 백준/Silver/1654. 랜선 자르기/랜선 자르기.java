import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		int K = Integer.parseInt(stringTokenizer.nextToken());
		int N = Integer.parseInt(stringTokenizer.nextToken());

		int[] LANCables = new int[K];
		// 하한값
		long low = 1;
		long high = 0;
		for (int i = 0; i < K; i++) {
			int input = Integer.parseInt(bufferedReader.readLine());
			LANCables[i] = input;
			// 상한값
			if (input > high) {
				high = input;
			}
		}
		// 상한값은 초과한 첫 길이
		high++;
		while (low < high) {
			// 중간값 (랜선의 길이)
			long mid = (low + high) / 2;

			// 잘라서 만든 랜선의 수
			long count = 0;
			for (int i = 0; i < K; i++) {
				count += LANCables[i] / mid;
			}

			// 필요한 랜선의 수보다 적으면 랜선의 길이 줄이기 (상한값 내리기)
			if (count < N) {
				high = mid;
			} else {
				// 필요한 랜선의 수와 같거나 많으면 랜선의 길이 늘리기 (하한값 올리기)
				low = mid + 1;
			}
		}
		// 랜선의 최대 길이는 상한값(최대 개수를 초과한 첫 길이) - 1
		System.out.println(high - 1);

		bufferedReader.close();
	}
}
