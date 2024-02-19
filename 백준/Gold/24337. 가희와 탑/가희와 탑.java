import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final String BLANK = " ";
	static final int IMPOSSIBLE = -1;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		int N = Integer.parseInt(stringTokenizer.nextToken());
		int a = Integer.parseInt(stringTokenizer.nextToken());
		int b = Integer.parseInt(stringTokenizer.nextToken());

		if (N + 1 < a + b) {
			System.out.println(IMPOSSIBLE);
			System.exit(0);
		}

		int[] buildings = new int[N];

		// 가장 높은 건물부터 배치
		int buildingNumber = Math.max(a, b);

		// 가장 큰 건물부터 세움
		int tallest = 0;
		if (a == 1) {
			// 가희가 볼 수 있는 건물이 1개라면 맨 왼쪽에 세워야 함
			buildings[tallest] = buildingNumber;
		} else {
			// 가희가 볼 수 있는 건물이 1개보다 많다면 최대한 오른쪽에 세워야 함
			tallest = N - b;
			buildings[tallest] = buildingNumber;
		}

		// 가희(왼쪽)가 볼 수 있는 건물을 세움
		for (int i = 1; i < a; i++) {
			buildings[tallest - a + i] = i;
		}
		// 단비(오른쪽)가 볼 수 있는 건물을 세움
		for (int i = 1; i < b; i++) {
			buildings[N - i] = i;
		}
		// 나머지 건물은 모두 1층 건물로 세움
		for (int i = 0; i < N; i++) {
			if (buildings[i] == 0) {
				buildings[i] = 1;
			}
		}
		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < N; i++) {
			answer.append(buildings[i]).append(BLANK);
		}
		System.out.println(answer);
		bufferedReader.close();
	}
}
