import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static final int FINISH = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		StringBuilder stringBuilder = new StringBuilder();
		int N;
		int problemNumber = 0;
		int answer;
		int[][] map;
		boolean[][] visited;
		PriorityQueue<Link> priorityQueue;
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};

		while (true) {
			N = Integer.parseInt(bufferedReader.readLine());
			if (N == FINISH) {
				break;
			}
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
				}
			}

			// for (int[] is : map) {
			// 	System.out.println(Arrays.toString(is));
			// }

			answer = 0;
			priorityQueue = new PriorityQueue<>((l1, l2) -> l1.rupee - l2.rupee);
			visited = new boolean[N][N];

			priorityQueue.offer(new Link(0, 0, map[0][0]));
			visited[0][0] = true;
			while (!priorityQueue.isEmpty()) {
				Link current = priorityQueue.poll();
				if (current.r == N - 1 && current.c == N - 1) {
					answer = current.rupee;
					break;
				}

				for (int d = 0; d < 4; d++) {
					int nr = current.r + dr[d];
					int nc = current.c + dc[d];
					if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] == true) {
						continue;
					}
					priorityQueue.offer(new Link(nr, nc, current.rupee + map[nr][nc]));
					visited[nr][nc] = true;
				}
			}
			stringBuilder.append("Problem ").append(++problemNumber).append(": ").append(answer).append("\n");
		}
		System.out.println(stringBuilder);
		bufferedReader.close();
	}

	static class Link {
		int r;
		int c;
		int rupee;

		public Link(int r, int c, int rupee) {
			this.r = r;
			this.c = c;
			this.rupee = rupee;
		}

		@Override
		public String toString() {
			return "Link{" +
				"r=" + r +
				", c=" + c +
				", rupee=" + rupee +
				'}';
		}
	}
}
