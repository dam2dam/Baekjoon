import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static final char BLANK = '0';
	static final char WALL = '1';

	static int M, N, answer;
	static char[][] maze;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		M = Integer.parseInt(stringTokenizer.nextToken());
		N = Integer.parseInt(stringTokenizer.nextToken());

		maze = new char[N][M];
		for (int i = 0; i < N; i++) {
			maze[i] = bufferedReader.readLine().toCharArray();
		}

		go();
		System.out.println(answer);
		bufferedReader.close();
	}

	static void go() {
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};

		PriorityQueue<Room> priorityQueue = new PriorityQueue<>((r1, r2) -> r1.brokenWallCount - r2.brokenWallCount);
		boolean[][] visited = new boolean[N][M];

		priorityQueue.offer(new Room(0, 0, 0));
		visited[0][0] = true;

		while (!priorityQueue.isEmpty()) {
			Room current = priorityQueue.poll();
			if (current.r == N - 1 && current.c == M - 1) {
				answer = current.brokenWallCount;
				return;
			}

			for (int d = 0; d < 4; d++) {
				int nr = current.r + dr[d];
				int nc = current.c + dc[d];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc]) {
					continue;
				}
				if (maze[nr][nc] == WALL) {
					priorityQueue.offer(new Room(nr, nc, current.brokenWallCount + 1));
				} else {
					priorityQueue.offer(new Room(nr, nc, current.brokenWallCount));
				}
				visited[nr][nc] = true;
			}
		}
	}

	static class Room {
		int r, c, brokenWallCount;

		public Room(int r, int c, int brokenWallCount) {
			this.r = r;
			this.c = c;
			this.brokenWallCount = brokenWallCount;
		}
	}
}

