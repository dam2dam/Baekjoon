import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final int HORIZONTAL = 0;
	static final int VERTICAL = 1;
	static final int DIAGONAL = 2;
	static final int WALL = 1;
	static int N;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		N = Integer.parseInt(bufferedReader.readLine());
		map = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}

		int[][][] dp = new int[N + 1][N + 1][3];
		dp[1][2][HORIZONTAL] = WALL;
		for (int i = 1; i <= N; i++) {
			for (int j = 3; j <= N; j++) {
				if (map[i][j] != WALL && map[i][j - 1] != WALL) {
					dp[i][j][HORIZONTAL] = dp[i][j - 1][HORIZONTAL] + dp[i][j - 1][DIAGONAL];
				}
				if (map[i][j] != WALL && map[i - 1][j] != WALL) {
					dp[i][j][VERTICAL] = dp[i - 1][j][VERTICAL] + dp[i - 1][j][DIAGONAL];
				}
				if (map[i][j] != WALL && map[i][j - 1] != WALL && map[i - 1][j] != WALL && map[i - 1][j - 1] != WALL) {
					dp[i][j][DIAGONAL] =
						dp[i - 1][j - 1][HORIZONTAL] + dp[i - 1][j - 1][VERTICAL] + dp[i - 1][j - 1][DIAGONAL];
				}
			}
		}
		System.out.println(dp[N][N][HORIZONTAL] + dp[N][N][VERTICAL] + dp[N][N][DIAGONAL]);
		bufferedReader.close();
	}
}
