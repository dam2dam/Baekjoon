import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static final int HORIZONTAL_BLOCK = 0;
	static final int VERTICAL_BLOCK = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		int R = Integer.parseInt(stringTokenizer.nextToken());
		int C = Integer.parseInt(stringTokenizer.nextToken());

		int[][] map = new int[R][C];
		for (int i = 0; i < R; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}

		int N = Integer.parseInt(bufferedReader.readLine());
		int[] dr = new int[N];
		int[] dc = new int[N];
		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			dr[i] = Integer.parseInt(stringTokenizer.nextToken());
			dc[i] = Integer.parseInt(stringTokenizer.nextToken());
		}

		int answer = -1;
		PriorityQueue<Employee> priorityQueue = new PriorityQueue<>((e1, e2) -> e1.steps - e2.steps);
		boolean[][] visited = new boolean[R][C];

		for (int j = 0; j < C; j++) {
			if (map[0][j] == VERTICAL_BLOCK) {
				priorityQueue.offer(new Employee(0, j, 0));
				visited[0][j] = true;
			}
		}

		while (!priorityQueue.isEmpty()) {
			Employee employee = priorityQueue.poll();

			if (employee.r == R - 1) {
				answer = employee.steps;
				break;
			}

			for (int d = 0; d < N; d++) {
				int nr = employee.r + dr[d];
				int nc = employee.c + dc[d];

				if (nr < 0 || nr >= R || nc < 0 || nc >= C ||
					map[nr][nc] == HORIZONTAL_BLOCK || visited[nr][nc]) {
					continue;
				}

				priorityQueue.offer(new Employee(nr, nc, employee.steps + 1));
				visited[nr][nc] = true;
			}
		}

		System.out.println(answer);
		bufferedReader.close();
	}

	static class Employee {
		int r, c, steps;

		public Employee(int r, int c, int steps) {
			this.r = r;
			this.c = c;
			this.steps = steps;
		}
	}
}
