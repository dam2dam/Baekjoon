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
		int kindofSushi = Integer.parseInt(stringTokenizer.nextToken());
		int continuousPlate = Integer.parseInt(stringTokenizer.nextToken());
		int coupon = Integer.parseInt(stringTokenizer.nextToken());

		// 회전 초밥 벨트를 펼친 형태로 표현한다
		int[] belt = new int[N + continuousPlate - 1];
		for (int i = 0; i < N; i++) {
			belt[i] = Integer.parseInt(bufferedReader.readLine());
		}
		// (연속 접시 개수 - 1) 만큼 벨트 뒤쪽에, 처음 접시부터 반복해서 적어둔다
		for (int i = N; i < belt.length; i++) {
			belt[i] = belt[i - N];
		}

		int[] sushi = new int[kindofSushi + 1];
		// 쿠폰 적용
		sushi[coupon]++;
		int count = 1;
		// 처음 연속 접시 먹기
		for (int i = 0; i < continuousPlate; i++) {
			sushi[belt[i]]++;
			if (sushi[belt[i]] == 1) {
				count++;
			}
		}

		int max = count;
		// 두 번째 접시부터 연속으로 먹기
		int start = 1;    // 연속 접시의 시작 인덱스
		int end = continuousPlate;    // 연속 접시의 끝 인덱스
		for (int i = 1; i < N; i++) {
			// 이전 연속 접시에서 다음 접시 추가
			sushi[belt[end]]++;
			if (sushi[belt[end]] == 1) {
				count++;
			}
			// 이전 연속 접시에서 직전 접시 제외
			sushi[belt[start - 1]]--;
			if (sushi[belt[start - 1]] == 0) {
				count--;
			}
			// 최댓값 갱신
			max = Math.max(max, count);

			start++;
			end++;
		}
		System.out.println(max);

		bufferedReader.close();
	}
}
