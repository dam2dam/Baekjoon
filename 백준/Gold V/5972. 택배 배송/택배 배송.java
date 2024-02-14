import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static final int START = 1;
	static int N, M;
	static ArrayList<Barn>[] map;
	static int[] minCosts;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());

		map = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			map[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int from = Integer.parseInt(stringTokenizer.nextToken());
			int to = Integer.parseInt(stringTokenizer.nextToken());
			int cost = Integer.parseInt(stringTokenizer.nextToken());

			map[from].add(new Barn(to, cost));
			map[to].add(new Barn(from, cost));
		}
		minCosts = new int[N + 1];
		Arrays.fill(minCosts, 50_000 * 1_000);
		goHyunseo();
		System.out.println(minCosts[N]);
		bufferedReader.close();
	}

	static void goHyunseo() {
		PriorityQueue<Barn> priorityQueue = new PriorityQueue<>();
		boolean[] visited = new boolean[N + 1];

		minCosts[START] = 0;
		priorityQueue.offer(new Barn(START, 0));

		while (!priorityQueue.isEmpty()) {
			Barn now = priorityQueue.poll();
			if (now.number == N) {
				return;
			}
			if (visited[now.number]) {
				continue;
			}
			for (Barn next : map[now.number]) {
				if (minCosts[next.number] > minCosts[now.number] + next.cost) {
					minCosts[next.number] = minCosts[now.number] + next.cost;
					priorityQueue.offer(new Barn(next.number, minCosts[next.number]));
				}
			}
		}
	}

	static class Barn implements Comparable<Barn> {
		int number, cost;

		public Barn(int number, int cost) {
			this.number = number;
			this.cost = cost;
		}

		@Override
		public int compareTo(Barn barn) {
			return this.cost - barn.cost < 0 ? -1 : this.cost == barn.cost ? 0 : 1;
		}
	}

}
