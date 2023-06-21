import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		int N = Integer.parseInt(bufferedReader.readLine());
		int[][] map = new int[N + 1][N + 1];
		int[][] sumMap = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
				sumMap[i][j] = map[i][j] + sumMap[i - 1][j] + sumMap[i][j - 1] - sumMap[i - 1][j - 1];
			}
		}

		int limit;
		int benefit;
		int maxBenefit = -1000;
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				limit = Math.min(N - r, N - c);
				for (int k = 0; k <= limit; k++) {
					benefit = sumMap[r + k][c + k] - sumMap[r - 1][c + k] - sumMap[r + k][c - 1] + sumMap[r - 1][c - 1];
					maxBenefit = Math.max(maxBenefit, benefit);
				}
			}
		}
		System.out.println(maxBenefit);
		bufferedReader.close();
	}
}
