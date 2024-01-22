import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final int UNVISITED = -1;
	static final int[] dr = {-1, 0, 1, 0};
	static final int[] dc = {0, 1, 0, -1};
	static int M, N;
	static int[][] map, dp;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		M = Integer.parseInt(stringTokenizer.nextToken());
		N = Integer.parseInt(stringTokenizer.nextToken());

		map = new int[M][N];
		dp = new int[M][N];

		for (int i = 0; i < M; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
				dp[i][j] = UNVISITED; // 방문하지 않은 곳 표시를 위해 초기화
			}
		}
		System.out.println(search(0, 0));

		bufferedReader.close();
	}

	private static int search(int r, int c) {

		// 도착하면 경로 하나 완성
		if (r == M - 1 && c == N - 1) {
			return 1;
		}

		// 방문하지 않았다면
		if (dp[r][c] == UNVISITED) {
			dp[r][c] = 0; // 방문 표시
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (nr < 0 || nr >= M || nc < 0 || nc >= N) {
					continue;
				}
				// 내리막길이면
				if (map[r][c] > map[nr][nc]) {
					dp[r][c] += search(nr, nc); // 다음 지점의 경우의 수를 현재 지점에 합하여 표기
				}
			}
		}
		return dp[r][c];
	}
}
