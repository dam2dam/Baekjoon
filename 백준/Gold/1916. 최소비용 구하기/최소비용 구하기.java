import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static ArrayList<City>[] cities;
	static int[] minCosts;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		N = Integer.parseInt(bufferedReader.readLine());
		M = Integer.parseInt(bufferedReader.readLine());
		cities = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			cities[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int from = Integer.parseInt(stringTokenizer.nextToken());
			int to = Integer.parseInt(stringTokenizer.nextToken());
			int cost = Integer.parseInt(stringTokenizer.nextToken());
			cities[from].add(new City(to, cost));
		}
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		int start = Integer.parseInt(stringTokenizer.nextToken());
		int end = Integer.parseInt(stringTokenizer.nextToken());

		// 각 도시까지 가는 데 드는 최소 비용을 저장하는 배열, 최댓값으로 초기화 해줌
		minCosts = new int[N + 1];
		Arrays.fill(minCosts, 100_000_000);

		go(start, end);
		System.out.println(minCosts[end]);

		bufferedReader.close();
	}

	static void go(int start, int end) {
		PriorityQueue<City> priorityQueue = new PriorityQueue<>();
		boolean[] visited = new boolean[N + 1];

		// 시작점
		priorityQueue.offer(new City(start, 0));
		minCosts[start] = 0;

		while (!priorityQueue.isEmpty()) {
			City now = priorityQueue.poll();
			// 현재 도시가 도착점이라면 반환
			if (now.number == end) {
				return;
			}
			if (visited[now.number]) {
				continue;
			}
			visited[now.number] = true;
			// 현재 도시에서 다음 도시로 가는 버스가 있다면 확인
			for (City next : cities[now.number]) {
				// (다음 도시의 최소 비용)보다 (현재 도시까지 최소 비용 + 다음 도시로 가는 버스 비용)이 더 작다면 최소 비용 갱신
				if (minCosts[next.number] > minCosts[now.number] + next.cost) {
					minCosts[next.number] = minCosts[now.number] + next.cost;
					priorityQueue.offer(new City(next.number, minCosts[next.number]));
				}
			}
		}
	}

	static class City implements Comparable<City> {
		int number, cost;

		public City(int number, int cost) {
			this.number = number;
			this.cost = cost;
		}

		@Override
		public int compareTo(City city) {
			return this.cost < city.cost ? -1 : this.cost == city.cost ? 0 : 1;
		}
	}
}
