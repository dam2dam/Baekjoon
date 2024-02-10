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
		int X = Integer.parseInt(stringTokenizer.nextToken());

		int sum = 0;
		int days = X;
		int[] visitors = new int[N];
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for (int i = 0; i < N; i++) {
			visitors[i] = Integer.parseInt(stringTokenizer.nextToken());
			// 첫 X기간 동안 방문자 수 기록
			if (days-- > 0) {
				sum += visitors[i];
			}
		}
		// 초기값을 최댓값으로 설정
		int max = sum;
		int count = 1;
		for (int i = 1; i <= N - X; i++) {
			// 현재 기간 전날의 방문자 수 빼기
			sum -= visitors[i - 1];
			// 과거 기간 다음날의 방문자 수 더하기
			sum += visitors[i + X - 1];
			// 최대 방문자 수인 기간 세기
			if (sum == max) {
				count++;
			}
			// 최대 방문자 수 갱신하고, 기간 개수 초기화
			if (sum > max) {
				max = sum;
				count = 1;
			}
		}
		// 최대 방문자 수가 0명이면 SAD
		if (max == 0) {
			System.out.println("SAD");
		} else {
			System.out.println(max);
			System.out.println(count);
		}
		bufferedReader.close();
	}
}
