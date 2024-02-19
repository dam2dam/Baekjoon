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
		// 투 포인터로 답을 구하기 위해 정렬
		Arrays.sort(numbers);

		int goodCount = 0;
		for (int now = 0; now < N; now++) {
			int target = numbers[now];
			int left = 0;
			int right = N - 1;

			while (left < right) {
				int sum = numbers[left] + numbers[right];
				// 합이 현재 값보다 크면 더 작은 수를 만들기 위해 오른쪽 값 내리기
				if (sum > target) {
					right--;
					continue;
				}
				// 합이 현재 값보다 작으면 더 큰 수를 만들기 위해 왼쪽 값 올리기
				if (sum < target) {
					left++;
					continue;
				}
				// 합이 현재 값과 같다면
				if (sum == target) {
					// 자기 자신과 같은 위치는 거르기
					if (left == now) {
						left++;
					} else if (right == now) {
						right--;
					} else {
						// 조건을 충족했다면 좋은 수
						goodCount++;
						break;
					}
				}
			}
		}
		System.out.println(goodCount);
		bufferedReader.close();
	}
}
