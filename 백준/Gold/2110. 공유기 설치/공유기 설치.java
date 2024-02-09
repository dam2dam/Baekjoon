import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

		int N = Integer.parseInt(stringTokenizer.nextToken());
		int C = Integer.parseInt(stringTokenizer.nextToken());

		int[] houses = new int[N];
		for (int i = 0; i < N; i++) {
			houses[i] = Integer.parseInt(bufferedReader.readLine());
		}
		Arrays.sort(houses);

		// 최소 간격
		int minGap = 1;
		// 최대 간격
		int maxGap = houses[N - 1] - houses[0];

		int midGap = 0;
		int result = 0;
		while (minGap <= maxGap) {
			int installCount = 1;
			int previousInstall = 0;
			midGap = (minGap + maxGap) / 2;

			// 직전에 설치한 집과 현재 집의 간격이 중간값보다 크거나 같으면 설치
			for (int now = 1; now < N; now++) {
				if (houses[now] - houses[previousInstall] < midGap) {
					continue;
				}
				installCount++;
				previousInstall = now;
			}
			// 설치한 공유기의 개수가 목표를 넘지 못했다면 상한값 내리기
			if (installCount < C) {
				maxGap = midGap - 1;
			} else {
				// 설치한 공유기의 개수가 목표보다 크거나 같다면 하한값 올리기 (최적의 거리를 찾기 위해)
				minGap = midGap + 1;
				result = midGap;
			}
		}
		System.out.println(result);
		bufferedReader.close();
	}
}
