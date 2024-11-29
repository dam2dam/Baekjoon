import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

class Solution {
    
    static int maxDistance, farNodeCount;
    static ArrayList<Integer>[] graph;
    
    public int solution(int n, int[][] edge) { 
        makeGraph(n, edge);
        bfs(n);
        
        return farNodeCount;
    }
    
	/**
	 * 그래프를 만드는 메서드
	 * @param n 노드의 개수
	 * @param edges 간선 정보
	 */
	static void makeGraph(int n, int[][] edges) {
		// 초기화
		graph = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}
		// 양방향 간선 연결
		for (int[] edge : edges) {
			graph[edge[0]].add(edge[1]);
			graph[edge[1]].add(edge[0]);
		}
	}

	/**
	 * 가장 먼 노드까지 너비 우선 탐색
	 * @param n 노드의 개수
	 */
	static void bfs(int n) {
		Queue<Node> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[n + 1];
		// 1번 간선에서부터 시작
		queue.offer(new Node(1, 0));
		visited[1] = true;

		while (!queue.isEmpty()) {
			Node node = queue.poll();
			int distance = node.distance;

			// 현재까지 가장 먼 간선보다 더 먼 간선이면 가장 먼 간선 수 초기화
			if (distance > maxDistance) {
				maxDistance = distance;
				farNodeCount = 1;
				// 가장 먼 간선 수 세기
			} else if (distance == maxDistance) {
				farNodeCount++;
			}
			// 현재 노드와 연결된 노드를 확인
			for (int next : graph[node.number]) {
				if (visited[next]) {
					continue;
				}
				visited[next] = true;
				queue.offer(new Node(next, distance + 1));
			}
		}
	}

	static class Node {
		int number;
		int distance;

		/**
		 * @param number 노드 번호
		 * @param distance 1번 노드와의 거리
		 */
		public Node(int number, int distance) {
			this.number = number;
			this.distance = distance;
		}
	}
}
