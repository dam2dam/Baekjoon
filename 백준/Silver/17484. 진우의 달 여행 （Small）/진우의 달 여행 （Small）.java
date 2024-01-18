import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

	static int M, N, minFuel;
	static int[][] space;
	static final int[] dr = {1, 1, 1};
	static final int[] dc = {-1, 0, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());

		space = new int[N][M];
		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < M; j++) {
				space[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}

		minFuel = Integer.MAX_VALUE;
		goToMoon();

		System.out.println(minFuel);
	}

	private static void goToMoon() {
		ArrayDeque<Spaceship> queue = new ArrayDeque<>();

		// 첫 행 모든 열에서 시작
		for (int i = 0; i < M; i++) {
			queue.offer(new Spaceship(0, i, space[0][i], -1));
		}
		while (!queue.isEmpty()) {
			Spaceship spaceship = queue.poll();

			// 마지막 행이면 연료 양 최솟값 기록
			if (spaceship.r == N - 1) {
				minFuel = Math.min(minFuel, spaceship.fuelCount);
				continue;
			}
			for (int d = 0; d < 3; d++) {

				// 이전 사용 방향 못감
				if (d == spaceship.previousDirection) {
					continue;
				}
				// 맨 왼쪽이면 1번방향 못감
				if (spaceship.c == 0 && d == 0) {
					continue;
				}
				// 맨 오른족이면 3번방향 못감
				if (spaceship.c == M - 1 && d == 2) {
					continue;
				}
				int nr = spaceship.r + dr[d];
				int nc = spaceship.c + dc[d];
				// 가면서 연료 양 체크
				queue.offer(new Spaceship(nr, nc, spaceship.fuelCount + space[nr][nc], d));
			}
		}

	}

	static class Spaceship {
		int r, c, fuelCount, previousDirection;

		public Spaceship(int r, int c, int fuelCount, int previousDirection) {
			this.r = r;
			this.c = c;
			this.fuelCount = fuelCount;
			this.previousDirection = previousDirection;
		}
	}
}
