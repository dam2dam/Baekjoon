import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static final int INF = Integer.MAX_VALUE;
	static int N, M, partyVillage;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());
		partyVillage = Integer.parseInt(stringTokenizer.nextToken());

		ArrayList<ArrayList<Village>> road = new ArrayList<>();
		ArrayList<ArrayList<Village>> reverseRoad = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			road.add(new ArrayList<>());
			reverseRoad.add(new ArrayList<>());
		}
		int from;
		int to;
		int cost;
		for (int i = 0; i < M; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			from = Integer.parseInt(stringTokenizer.nextToken());
			to = Integer.parseInt(stringTokenizer.nextToken());
			cost = Integer.parseInt(stringTokenizer.nextToken());

			road.get(from).add(new Village(to, cost));
			reverseRoad.get(to).add(new Village(from, cost));
		}

		int[] goParty = dijkstra(reverseRoad);
		int[] goHome = dijkstra(road);

		int answer = Integer.MIN_VALUE;
		int sum;
		for (int i = 1; i <= N; i++) {
			sum = goParty[i] + goHome[i];
			if (answer < sum) {
				answer = sum;
			}
		}
		System.out.println(answer);
		bufferedReader.close();
	}

	private static int[] dijkstra(ArrayList<ArrayList<Village>> road) {
		int[] minCosts = new int[N + 1];
		Arrays.fill(minCosts, INF);

		PriorityQueue<Village> queue = new PriorityQueue<>();
		boolean[] visited = new boolean[N + 1];
		queue.offer(new Village(partyVillage, 0));
		minCosts[partyVillage] = 0;

		while (!queue.isEmpty()) {
			int nowVillage = queue.poll().number;

			if (visited[nowVillage]) {
				continue;
			}
			visited[nowVillage] = true;

			for (Village next : road.get(nowVillage)) {
				if (!visited[next.number] &&
					minCosts[next.number] > minCosts[nowVillage] + next.cost) {
					// 다음 마을까지 최소비용보다, 현재마을까지 최소비용에 다음마을로 가는 비용을 더한 것이 작다면 갱신해주기
					minCosts[next.number] = minCosts[nowVillage] + next.cost;
					queue.offer(new Village(next.number, minCosts[next.number]));
				}
			}
		}
		return minCosts;
	}

	static class Village implements Comparable<Village> {
		int number, cost;

		public Village(int number, int cost) {
			this.number = number;
			this.cost = cost;
		}

		@Override
		public int compareTo(Village v) {
			return this.cost < v.cost ? -1 : this.cost == v.cost ? 0 : 1;
		}
	}
}
