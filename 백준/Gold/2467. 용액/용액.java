import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final String BLANK = " ";

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		StringBuilder answer = new StringBuilder();

		int N = Integer.parseInt(bufferedReader.readLine());
		int[] solutions = new int[N];

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for (int i = 0; i < N; i++) {
			solutions[i] = Integer.parseInt(stringTokenizer.nextToken());
		}
		int left = 0;
		int right = N - 1;
		int closeToZero = 2_000_000_000;
		int solution1 = 0;
		int solution2 = 0;
		while (left < right) {
			// 혼합 용액의 특성값
			int mix = solutions[left] + solutions[right];
			// 특성값의 절댓값으로 최솟값 갱신
			if (closeToZero > Math.abs(mix)) {
				closeToZero = Math.abs(mix);
				solution1 = left;
				solution2 = right;
			}
			// 혼합 용액의 특성값이 0이면 바로 정답!
			if (mix == 0) {
				break;
			}
			// 특성값이 양수이면 큰 값 감소, 음수이면 작은 값 증가
			if (mix > 0) {
				right--;
			} else if (mix < 0) {
				left++;
			}
		}
		answer.append(solutions[solution1]).append(BLANK).append(solutions[solution2]);
		System.out.println(answer);
		bufferedReader.close();
	}
}
