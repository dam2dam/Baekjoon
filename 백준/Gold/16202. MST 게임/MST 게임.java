import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static final String BLANK = " ";
	static final int ZERO = 0;
	static int N, score, allEdgesSize;
	static int[] parents;
	static PriorityQueue<Edge> allEdges;
	static Queue<Edge> MSTedges, notMSTedges;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		StringBuilder answer = new StringBuilder();

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		N = Integer.parseInt(stringTokenizer.nextToken());
		int M = Integer.parseInt(stringTokenizer.nextToken());
		int K = Integer.parseInt(stringTokenizer.nextToken());

		allEdges = new PriorityQueue<>();
		for (int weight = 1; weight <= M; weight++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int A = Integer.parseInt(stringTokenizer.nextToken());
			int B = Integer.parseInt(stringTokenizer.nextToken());
			allEdges.offer(new Edge(A, B, weight));
		}
		MSTedges = new ArrayDeque<>();
		notMSTedges = new ArrayDeque<>();
		parents = new int[N + 1];

		int remainder = 0;
		// 게임 K턴
		for (int i = 0; i < K; i++) {
			// 매 턴마다 게임 점수, 현재 간선 리스트 길이, parents배열을 초기화 한다
			init();

			for (int j = 0; j < allEdgesSize; j++) {
				Edge edge = allEdges.poll();
				// 사이클이 만들어지지 않으면
				if (union(edge.vertex1, edge.vertex2)) {
					// 현재 간선을 선택한다
					MSTedges.offer(edge);
					score += edge.weight;
				} else {
					// 선택되지 않은 간선은 따로 간선 리스트에 넣는다
					notMSTedges.offer(edge);
				}
			}
			// 선택된 간선의 수가 스패닝 트리를 구성하는 간선의 수(N-1 개)를 만족하면 점수를 기록한다
			if (MSTedges.size() == N - 1) {
				answer.append(score).append(BLANK);
			} else {
				// MST를 만들 수 없다면, (이후 점수는 0점이므로) 게임을 끝낸다
				remainder = K - i;
				break;
			}
			// 현재 턴에서 구한 MST에서 가장 가중치가 작은 간선 하나를 제거한다
			removeEdge();
		}
		// MST를 만들 수 없어서 게임이 끝났다면, 남은 턴은 모두 0점을 기록한다
		for (int i = 0; i < remainder; i++) {
			answer.append(ZERO).append(BLANK);
		}
		System.out.println(answer);

		bufferedReader.close();
	}

	/**
	 * 게임 점수, 현재 간선 리스트 길이, parents배열을 초기화 한다
	 */
	static void init() {
		score = 0;
		allEdgesSize = allEdges.size();
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}

	/**
	 * MST에서 가장 가중치가 작은 간선 하나를 제거하고 나머지 간선은 다시 간선 리스트에 넣는다
	 */
	static void removeEdge() {
		MSTedges.poll();

		while (!MSTedges.isEmpty()) {
			allEdges.offer(MSTedges.poll());
		}
		while (!notMSTedges.isEmpty()) {
			allEdges.offer(notMSTedges.poll());
		}
	}

	static boolean union(int vertex1, int vertex2) {
		int root1 = find(vertex1);
		int root2 = find(vertex2);
		if (root1 == root2) {
			return false;
		}
		if (root1 > root2) {
			parents[root1] = root2;
		} else {
			parents[root2] = root1;
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
