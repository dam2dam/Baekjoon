import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static final int IMPOSSIBLE = 0;
	static final int POSSIBLE = 1;
	static final int TARGET = 2;
	static final String BLANK = " ";
	static final String ENTER = "\n";
	static int n, m;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());

		map = new int[n][m];
		int r = 0;
		int c = 0;
		for (int i = 0; i < n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < m; j++) {
				int input = Integer.parseInt(stringTokenizer.nextToken());
				if (input == POSSIBLE) {
					map[i][j] = -1;
				}
				if (input == TARGET) {
					r = i;
					c = j;
				}
			}
		}

		dfs(r, c);

		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				answer.append(map[i][j]).append(BLANK);
			}
			answer.append(ENTER);
		}
		System.out.println(answer);
		bufferedReader.close();
	}

	private static void dfs(int r, int c) {
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};

		Queue<Point> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[n][m];

		queue.offer(new Point(r, c, 0));
		visited[r][c] = true;

		while (!queue.isEmpty()) {
			Point current = queue.poll();
			map[current.r][current.c] = current.distance;

			for (int d = 0; d < 4; d++) {
				int nr = current.r + dr[d];
				int nc = current.c + dc[d];

				if (nr < 0 || nr >= n || nc < 0 || nc >= m || visited[nr][nc] || map[nr][nc] == IMPOSSIBLE) {
					continue;
				}
				queue.offer(new Point(nr, nc, current.distance + 1));
				visited[nr][nc] = true;
			}
		}
	}

	static class Point {
		int r, c, distance;

		public Point(int r, int c, int distance) {
			this.r = r;
			this.c = c;
			this.distance = distance;
		}
	}
}
