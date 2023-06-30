import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static final char WALL = '1';
	static final int[] dr = {-1, 0, 1, 0};
	static final int[] dc = {0, 1, 0, -1};

	static int N, M, minMoveCount;
	static char[][] map;
	static boolean[][][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());

		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = bufferedReader.readLine().toCharArray();
		}
		visited = new boolean[N][M][2];
		minMoveCount = Integer.MAX_VALUE;
		bfs();
		if (minMoveCount == Integer.MAX_VALUE) {
			minMoveCount = -1;
		}
		System.out.println(minMoveCount);

		bufferedReader.close();
	}

	private static void bfs() {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(0, 0, 1, false));
		visited[0][0][0] = true;

		while (!queue.isEmpty()) {
			Point current = queue.poll();

			if (current.r == N - 1 && current.c == M - 1) {
				minMoveCount = Math.min(minMoveCount, current.moveCount);
				continue;
			}
			for (int d = 0; d < 4; d++) {
				int nr = current.r + dr[d];
				int nc = current.c + dc[d];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
					continue;
				}
				if (map[nr][nc] == WALL) {
					if (!current.isBreak && !visited[nr][nc][1]) {
						queue.offer(new Point(nr, nc, current.moveCount + 1, !current.isBreak));
						visited[nr][nc][1] = true;
					}
				} else {
					if (!current.isBreak && !visited[nr][nc][0]) {
						queue.offer(new Point(nr, nc, current.moveCount + 1, current.isBreak));
						visited[nr][nc][0] = true;

					} else if (current.isBreak && !visited[nr][nc][1]) {
						queue.offer(new Point(nr, nc, current.moveCount + 1, current.isBreak));
						visited[nr][nc][1] = true;
					}
				}
			}
		}
	}

	static class Point {
		int r, c, moveCount;
		boolean isBreak;

		public Point(int r, int c, int moveCount, boolean isBreak) {
			this.r = r;
			this.c = c;
			this.moveCount = moveCount;
			this.isBreak = isBreak;
		}
	}
}
