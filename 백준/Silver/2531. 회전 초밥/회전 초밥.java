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
		int kindsofSushi = Integer.parseInt(stringTokenizer.nextToken());
		int continuousPlates = Integer.parseInt(stringTokenizer.nextToken());
		int coupon = Integer.parseInt(stringTokenizer.nextToken());

		// 회전 초밥 벨트를 펼친 형태로 표현
		int[] belt = new int[N + continuousPlates - 1];
		for (int i = 0; i < N; i++) {
			belt[i] = Integer.parseInt(bufferedReader.readLine());
		}
		// (연속 접시 개수 - 1) 만큼 벨트 뒤쪽에, 처음 접시부터 반복해서 적어두기
		for (int i = 0; i < continuousPlates - 1; i++) {
			belt[N + i] = belt[i];
		}
		int[] sushi = new int[kindsofSushi + 1];
		// 쿠폰 적용
		sushi[coupon]++;
		int count = 1;
		// 첫 번째 접시부터 연속해서 먹어봄
		for (int i = 0; i < continuousPlates; i++) {
			sushi[belt[i]]++;
			if (sushi[belt[i]] == 1) {
				count++;
			}
		}
		int max = count;
		// 두 번째 접시부터 연속해서 먹어봄 
		for (int i = 1; i < N; i++) {
			// 이전 연속 접시에서 다음 접시 추가
			sushi[belt[i + continuousPlates - 1]]++;
			if (sushi[belt[i + continuousPlates - 1]] == 1) {
				count++;
			}
			// 이전 연속 접시에서 직전 접시 제외
			sushi[belt[i - 1]]--;
			if (sushi[belt[i - 1]] == 0) {
				count--;
			}
			// 최댓값 갱신
			max = Math.max(max, count);
		}
		System.out.println(max);
		bufferedReader.close();
	}
}
