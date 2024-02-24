import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int[] parents, ranks;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		int N = Integer.parseInt(stringTokenizer.nextToken());
		int M = Integer.parseInt(stringTokenizer.nextToken());
		Road[] roads = new Road[M];
		for (int i = 0; i < M; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int A = Integer.parseInt(stringTokenizer.nextToken());
			int B = Integer.parseInt(stringTokenizer.nextToken());
			int cost = Integer.parseInt(stringTokenizer.nextToken());
			roads[i] = new Road(A, B, cost);
		}
		// 최소 간선을 선택해나가기 위해 미리 정렬한다
		Arrays.sort(roads);

		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		ranks = new int[N + 1];

		int sum = 0;
		int max = 0;
		for (int i = 0; i < M; i++) {
			Road now = roads[i];
			// 사이클이 형성되지 않았다면 길을 선택한다
			if (union(now.house1, now.house2)) {
				// 선택된 길의 유지비 합 갱신
				sum += now.cost;
				// 선택된 길 중, 가장 유지비가 많이 드는 길 기록
				max = Math.max(max, now.cost);
			}
		}
		// 길을 최소한으로 남기고, 그 중 가장 유지비가 많이 드는 길을 없애서 마을을 나눈다
		System.out.println(sum - max);

		bufferedReader.close();
	}

	static boolean union(int house1, int house2) {
		int root1 = find(house1);
		int root2 = find(house2);
		// 사이클이 발생한다면 false 반환
		if (root1 == root2) {
			return false;
		}
		// 항상 rank가 더 높은 트리 밑에 더 낮은 트리를 넣는다
		if (ranks[root1] < ranks[root2]) {
			parents[root1] = root2;
		} else {
			parents[root2] = root1;
		}
		// 같은 rank라면 부모가 된 트리의 rank를 증가시켜준다
		if (ranks[root1] == ranks[root2]) {
			ranks[root1]++;
		}
		return true;
	}

	static int find(int house) {
		if (parents[house] == house) {
			return house;
		}
		return parents[house] = find(parents[house]);
	}

	static class Road implements Comparable<Road> {
		int house1, house2, cost;

		public Road(int house1, int house2, int cost) {
			this.house1 = house1;
			this.house2 = house2;
			this.cost = cost;
		}

		@Override
		public int compareTo(Road road) {
			return this.cost < road.cost ? -1 : this.cost == road.cost ? 0 : 1;
		}
	}
}
