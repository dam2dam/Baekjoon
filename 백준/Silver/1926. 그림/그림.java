import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static final int NOT_COLOR = 0;
	static final int COLOR = 1;

	static final int[] dr = {-1, 0, 1, 0};
	static final int[] dc = {0, 1, 0, -1};

	static int n, m, paintingCount, maxPaintingArea;
	static int[][] paper;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());

		paper = new int[n][m];
		for (int i = 0; i < n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < m; j++) {
				paper[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}
		visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (paper[i][j] == COLOR && !visited[i][j]) {
					bfs(i, j);
					paintingCount++;
				}
			}
		}
		System.out.println(paintingCount);
		System.out.println(maxPaintingArea);
		bufferedReader.close();
	}

	private static void bfs(int r, int c) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(r, c));
		visited[r][c] = true;
		int paintingArea = 1;

		while (!queue.isEmpty()) {
			Point current = queue.poll();

			for (int d = 0; d < 4; d++) {
				int nr = current.r + dr[d];
				int nc = current.c + dc[d];

				if (nr < 0 || nr >= n || nc < 0 || nc >= m ||
					visited[nr][nc] || paper[nr][nc] == NOT_COLOR) {
					continue;
				}

				queue.offer(new Point(nr, nc));
				visited[nr][nc] = true;
				paintingArea++;
			}
		}
		renewMax(paintingArea);
	}

	private static void renewMax(int paintingArea) {
		if (paintingArea > maxPaintingArea) {
			maxPaintingArea = paintingArea;
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
