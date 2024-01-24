import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static final int BLANK = 0;
	static final int FOREST = 1;
	static final int[] dr = {-1, 0, 1, 0};
	static final int[] dc = {0, 1, 0, -1};

	static int N, M;
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}

		visited = new boolean[N][M];
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == BLANK && !visited[i][j]) {
					bfs(i, j);
					count++;
				}
			}
		}
		System.out.println(count);
		bufferedReader.close();
	}

	private static void bfs(int r, int c) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(r, c));
		visited[r][c] = true;

		while (!queue.isEmpty()) {
			Point now = queue.poll();

			for (int d = 0; d < 4; d++) {
				int nr = now.r + dr[d];
				int nc = now.c + dc[d];

				if (nr < 0) {
					nr = N - 1;
				}
				if (nr >= N) {
					nr -= N;
				}
				if (nc < 0) {
					nc = M - 1;
				}
				if (nc >= M) {
					nc -= M;
				}
				if (map[nr][nc] == FOREST || visited[nr][nc]) {
					continue;
				}
				queue.offer(new Point(nr, nc));
				visited[nr][nc] = true;
			}
		}
	}

	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
