import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static double answer;
	static Star[] stars;
	static ArrayList<Edge>[] edges;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		N = Integer.parseInt(bufferedReader.readLine());
		stars = new Star[N];
		edges = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			// 별에 번호를 지정하고 좌표 저장
			double x = Double.parseDouble(stringTokenizer.nextToken());
			double y = Double.parseDouble(stringTokenizer.nextToken());
			stars[i] = new Star(i, x, y);
			// 선 배열 초기화
			edges[i] = new ArrayList<>();
		}
		Star from, to;
		double cost;
		for (int i = 0; i < N; i++) {
			from = stars[i];
			for (int j = 0; j < N; j++) {
				// 자기 자신은 건너뛰기
				if (i == j) {
					continue;
				}
				to = stars[j];
				// 거리 계산 후 선 배열에 저장하기
				cost = Math.sqrt(Math.pow(from.x - to.x, 2) + Math.pow(from.y - to.y, 2));
				edges[from.number].add(new Edge(to.number, cost));
			}
		}
		answer = 0;
		searchConstellation();
		System.out.printf("%.2f", answer);

		bufferedReader.close();
	}

	/**
	 * 별자리를 이루는 선을 탐색한다
	 */
	static void searchConstellation() {
		PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
		boolean[] visited = new boolean[N];

		// 첫번째 별은 0번
		priorityQueue.offer(new Edge(0, 0));

		while (!priorityQueue.isEmpty()) {
			Edge now = priorityQueue.poll();
			// 방문체크
			if (visited[now.number]) {
				continue;
			}
			visited[now.number] = true;
			// 별자리에 포함시킨다
			answer += now.cost;
			// 현재 별과 이어진 별을 모두 우선순위 큐에 넣는다
			for (Edge next : edges[now.number]) {
				priorityQueue.offer(next);
			}
		}
	}

	static class Edge implements Comparable<Edge> {
		int number;
		double cost;

		public Edge(int number, double cost) {
			this.number = number;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge edge) {
			return this.cost < edge.cost ? -1 : this.cost == edge.cost ? 0 : 1;
		}
	}

	static class Star {
		int number;
		double x, y;

		public Star(int number, double x, double y) {
			this.number = number;
			this.x = x;
			this.y = y;
		}
	}
}
