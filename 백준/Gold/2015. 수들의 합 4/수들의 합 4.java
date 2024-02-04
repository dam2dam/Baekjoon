import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		int N = Integer.parseInt(stringTokenizer.nextToken());
		int K = Integer.parseInt(stringTokenizer.nextToken());

		int[] sums = new int[N + 1];
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		// 배열에 (0 ~ i) 까지의 누적합 저장
		for (int i = 1; i <= N; i++) {
			sums[i] = sums[i - 1] + Integer.parseInt(stringTokenizer.nextToken());
		}

		long count = 0;
		HashMap<Integer, Long> map = new HashMap<>();
		for (int i = 1; i <= N; i++) {
			// (0 ~ i) 까지의 누적합에서 K와 같은 수 세기
			if (sums[i] == K) {
				count++;
			}
			// (어떤 수 ~ i) 까지의 누적합에서 K와 같은 수 세기
			if (map.containsKey(sums[i] - K)) {
				count += map.get(sums[i] - K);
			}
			// 해시 맵에 누적합 개수 저장
			map.put(sums[i], map.getOrDefault(sums[i], 0L) + 1);
		}
		System.out.println(count);
		bufferedReader.close();
	}
}
