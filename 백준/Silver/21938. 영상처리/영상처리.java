import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static final int[] dr = {-1, 0, 1, 0};
	static final int[] dc = {0, 1, 0, -1};
	static int N, M, T;
	static int[][] display;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());

		display = new int[N][M];
		int R, G, B;
		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < M; j++) {
				R = Integer.parseInt(stringTokenizer.nextToken());
				G = Integer.parseInt(stringTokenizer.nextToken());
				B = Integer.parseInt(stringTokenizer.nextToken());
				display[i][j] = (R + G + B) / 3;
			}
		}
		T = Integer.parseInt(bufferedReader.readLine());
		visited = new boolean[N][M];

		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (display[i][j] >= T && !visited[i][j]) {
					search(i, j);
					count++;
				}
			}
		}
		System.out.println(count);
		bufferedReader.close();
	}

	private static void search(int r, int c) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(r, c));
		visited[r][c] = true;

		while (!queue.isEmpty()) {
			Point now = queue.poll();

			for (int d = 0; d < 4; d++) {
				int nr = now.r + dr[d];
				int nc = now.c + dc[d];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M
					|| display[nr][nc] < T || visited[nr][nc]) {
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
