import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static final char GOLD = '#';
	static final char START = 'S';
	static final char END = 'E';
	static final int INPUT_END = 0;
	static final String YEEEEEES_FIRST = "Escaped in ";
	static final String YEEEEEES_LAST = " minute(s).";
	static final String NOOOOOOO = "Trapped!";

	static final int[] dl = {0, 0, 0, 0, 1, -1};
	static final int[] dr = {-1, 0, 1, 0, 0, 0};
	static final int[] dc = {0, 1, 0, -1, 0, 0};

	static int L, R, C;
	static char[][][] map;
	static boolean[][][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		String input;
		while ((input = bufferedReader.readLine()) != null) {
			stringTokenizer = new StringTokenizer(input);
			L = Integer.parseInt(stringTokenizer.nextToken());
			R = Integer.parseInt(stringTokenizer.nextToken());
			C = Integer.parseInt(stringTokenizer.nextToken());

			if (L == INPUT_END && R == INPUT_END && C == INPUT_END) {
				break;
			}
			map = new char[L][R][C];
			Point start = new Point();
			Point end = new Point();
			for (int i = 0; i < L; i++) {
				for (int j = 0; j < R; j++) {
					map[i][j] = bufferedReader.readLine().toCharArray();

					for (int k = 0; k < C; k++) {
						if (map[i][j][k] == START) {
							start.l = i;
							start.r = j;
							start.c = k;
							continue;
						}
						if (map[i][j][k] == END) {
							end.l = i;
							end.r = j;
							end.c = k;
						}
					}
				}
				bufferedReader.readLine();
			}
			visited = new boolean[L][R][C];
			int time = bfs(start, end);
			System.out.println(answer(time));
		}
		bufferedReader.close();
	}

	private static String answer(int time) {
		if (time == -1) {
			return NOOOOOOO;
		}
		return YEEEEEES_FIRST + time + YEEEEEES_LAST;
	}

	private static int bfs(Point start, Point end) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(start);
		visited[start.l][start.r][start.c] = true;

		while (!queue.isEmpty()) {
			Point current = queue.poll();

			if (current.l == end.l && current.r == end.r && current.c == end.c) {
				return current.time;
			}
			for (int d = 0; d < 6; d++) {
				int nl = current.l + dl[d];
				int nr = current.r + dr[d];
				int nc = current.c + dc[d];

				if (inRange(nl, nr, nc)) {
					queue.offer(new Point(nl, nr, nc, current.time + 1));
					visited[nl][nr][nc] = true;
				}
			}
		}
		return -1;
	}

	private static boolean inRange(int nl, int nr, int nc) {
		if (nl < 0 || nl >= L || nr < 0 || nr >= R || nc < 0 || nc >= C ||
			visited[nl][nr][nc] || map[nl][nr][nc] == GOLD) {
			return false;
		}
		return true;
	}

	static class Point {
		int l, r, c, time;

		public Point() {
		}

		public Point(int l, int r, int c, int time) {
			this.l = l;
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}
}
