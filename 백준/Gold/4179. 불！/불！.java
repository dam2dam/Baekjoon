import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static final char WALL = '#';
	static final char JIHOON = 'J';
	static final char FIRE = 'F';
	static final int VISITED_FIRE = 1;
	static final int VISITED_JIHOON = 2;
	static final int[] dr = {-1, 0, 1, 0};
	static final int[] dc = {0, 1, 0, -1};
	static final String DONT_ESCAPE = "IMPOSSIBLE";

	static int R, C;
	static char[][] map;
	static Queue<Point> fireQueue, jihoonQueue;
	static int[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		R = Integer.parseInt(stringTokenizer.nextToken());
		C = Integer.parseInt(stringTokenizer.nextToken());
		map = new char[R][C];

		fireQueue = new ArrayDeque<>();
		jihoonQueue = new ArrayDeque<>();
		visited = new int[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = bufferedReader.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if (map[i][j] == JIHOON) {
					jihoonQueue.offer(new Point(i, j));
					visited[i][j] = VISITED_JIHOON;
				}
				if (map[i][j] == FIRE) {
					fireQueue.offer(new Point(i, j));
					visited[i][j] = VISITED_FIRE;
				}
			}
		}
		int time = escape();
		if (time == -1) {
			System.out.println(DONT_ESCAPE);
		} else {
			System.out.println(time);
		}

		bufferedReader.close();
	}

	private static int escape() {

		int time = 0;
		while (!jihoonQueue.isEmpty()) {
			time++;

			// 불 이동
			int fireQueueSize = fireQueue.size();
			for (int i = 0; i < fireQueueSize; i++) {
				Point fire = fireQueue.poll();

				for (int d = 0; d < 4; d++) {
					int nr = fire.r + dr[d];
					int nc = fire.c + dc[d];

					if (!inRange(nr, nc) || visited[nr][nc] == VISITED_FIRE || map[nr][nc] == WALL) {
						continue;
					}
					fireQueue.offer(new Point(nr, nc));
					visited[nr][nc] = VISITED_FIRE;
				}
			}

			// 지훈 이동
			int jihoonQueueSize = jihoonQueue.size();
			for (int i = 0; i < jihoonQueueSize; i++) {
				Point jihoon = jihoonQueue.poll();

				for (int d = 0; d < 4; d++) {
					int nr = jihoon.r + dr[d];
					int nc = jihoon.c + dc[d];

					if (!inRange(nr, nc)) {
						return time;
					}
					if (visited[nr][nc] > 0 || map[nr][nc] == WALL) {
						continue;
					}
					jihoonQueue.offer(new Point(nr, nc));
					visited[nr][nc] = VISITED_JIHOON;
				}
			}
		}
		return -1;
	}

	private static boolean inRange(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}

	static class Point {
		int r, c;

		public Point() {
		}

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
