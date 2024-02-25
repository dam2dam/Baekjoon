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
		int V = Integer.parseInt(stringTokenizer.nextToken());
		int E = Integer.parseInt(stringTokenizer.nextToken());
		Edge[] edges = new Edge[E];
		for (int i = 0; i < E; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int A = Integer.parseInt(stringTokenizer.nextToken());
			int B = Integer.parseInt(stringTokenizer.nextToken());
			int weight = Integer.parseInt(stringTokenizer.nextToken());
			edges[i] = new Edge(A, B, weight);
		}
		// 1. 가중치가 낮은 간선부터 선택할 수 있도록 미리 정렬한다
		Arrays.sort(edges);

		parents = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			parents[i] = i;
		}
		ranks = new int[V + 1];

		int sum = 0;
		for (int i = 0; i < E; i++) {
			// 2. 사이클이 발생하지 않는 간선이라면 선택한다.
			Edge edge = edges[i];
			if (union(edge.vertex1, edge.vertex2)) {
				sum += edge.weight;
			}
		}
		System.out.println(sum);

		bufferedReader.close();
	}

	static boolean union(int vertex1, int vertex2) {
		int root1 = find(vertex1);
		int root2 = find(vertex2);
		if (root1 == root2) {
			return false;
		}
		if (ranks[root1] < ranks[root2]) {
			parents[root1] = root2;
		} else {
			parents[root2] = root1;
		}

		if (ranks[root1] == ranks[root2]) {
			ranks[root1]++;
		}
		return true;
	}

	static int find(int vertex) {
		if (parents[vertex] == vertex) {
			return vertex;
		}
		return parents[vertex] = find(parents[vertex]);
	}

	static class Edge implements Comparable<Edge> {
		int vertex1, vertex2, weight;

		public Edge(int vertex1, int vertex2, int weight) {
			this.vertex1 = vertex1;
			this.vertex2 = vertex2;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge edge) {
			return this.weight < edge.weight ? -1 : this.weight == edge.weight ? 0 : 1;
		}
	}
}
