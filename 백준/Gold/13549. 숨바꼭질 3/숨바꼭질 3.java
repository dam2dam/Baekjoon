import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static final int MAX_POINT = 100_000;
	static int N, K, minTime;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		N = Integer.parseInt(stringTokenizer.nextToken());
		K = Integer.parseInt(stringTokenizer.nextToken());

		minTime = Integer.MAX_VALUE;
		search();
		System.out.println(minTime);
		bufferedReader.close();
	}

	private static void search() {
		PriorityQueue<Point> queue = new PriorityQueue<>();
		boolean[] visited = new boolean[MAX_POINT + 1];
		queue.offer(new Point(N, 0));

		int nx;
		while (!queue.isEmpty()) {
			Point current = queue.poll();
			visited[current.x] = true;

			if (current.x == K) {
				minTime = Math.min(minTime, current.time);
			}
			nx = current.x * 2;
			if (nx <= MAX_POINT && !visited[nx]) {
				queue.offer(new Point(nx, current.time));
			}
			nx = current.x + 1;
			if (nx <= MAX_POINT && !visited[nx]) {
				queue.offer(new Point(nx, current.time + 1));
			}
			nx = current.x - 1;
			if (nx >= 0 && !visited[nx]) {
				queue.offer(new Point(nx, current.time + 1));
			}
		}
	}

	static class Point implements Comparable<Point> {
		int x, time;

		public Point(int x, int time) {
			this.x = x;
			this.time = time;
		}

		@Override
		public int compareTo(Point p) {
			return this.time < p.time ? -1 : this.time == p.time ? 0 : 1;
		}
	}
}
