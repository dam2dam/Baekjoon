	/*
	
	[1753] 최단경로
	방향그래프가 주어지면 주어진 시작점에서 다른 모든 정점으로의 최단 경로를 구하는 프로그램을 작성하시오. 
	단, 모든 간선의 가중치는 10 이하의 자연수이다.
	
	첫째 줄부터 V개의 줄에 걸쳐, i번째 줄에 i번 정점으로의 최단 경로의 경로값을 출력한다. 
	시작점 자신은 0으로 출력하고, 경로가 존재하지 않는 경우에는 INF를 출력하면 된다.
	
	1. 방향 그래프를 입력받아 인접 리스트에 저장하고, 경로값 배열을 최댓값으로 모두 초기화한다
	2. 시작점을 우선순위 큐에 넣고 시작점의 경로값은 0으로 설정한다
	3. 큐에서 뺀 정점을 경유지로 하여 거쳐가는 것이 유리하다면 경로값을 갱신하고, 다음 경유지로 하여 탐색하기 위해 큐에 삽입한다
	4. 모든 정점으로의 최단 경로를 구했다면 시작점으로부터 각 정점까지 최단 경로의 경로값을 출력한다
	
	*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	final static String notExist = "INF";
	static int V, E, start, u, v, w, end;
	static ArrayList<Edge>[] graph;
	static boolean[] visit;
	static int[] distance;
	static PriorityQueue<Edge> priorityQueue;
	
	public static void main(String[] args) throws IOException{
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		
		// 입력1 : V:정점의 개수, E:간선의 개수
		V = Integer.parseInt(stringTokenizer.nextToken());
		E = Integer.parseInt(stringTokenizer.nextToken());
		
		// 입력2 : 시작 정점의 번호
		start = Integer.parseInt(bufferedReader.readLine());
		
		// 인접리스트 배열생성
		graph = new ArrayList[V+1];
		for (int i = 1; i <= V; i++)
			graph[i] = new ArrayList<>();
		
		visit = new boolean[V+1];
		
		for (int i = 0; i < E; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			
			// 입력3 : u에서 v로가는 가중치 w인 간선이 존재. 인접리스트에 저장
			u = Integer.parseInt(stringTokenizer.nextToken());
			v = Integer.parseInt(stringTokenizer.nextToken());
			w = Integer.parseInt(stringTokenizer.nextToken());
			
			graph[u].add(new Edge(v, w));
		}
		
		// 경로의 가중치를 모두 최댓값으로 초기화
		distance = new int[V+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		// 우선순위 큐에 시작점을 넣고 본인의 경로값은 0으로 설정
		priorityQueue = new PriorityQueue<>();		
		priorityQueue.offer(new Edge(start, 0));
		distance[start] = 0;
		
		// 모든 정점으로의 최단 경로
		for (int end = 1; end <= V; end++) {
			
			while(priorityQueue.isEmpty() == false) {
				// 이전 정점으로부터 경로값이 최소인 (경유지가 될) 정점 꺼냄
				Edge currentEdge = priorityQueue.poll();	
				
				// 이미 경로값을 구했다면 넘어감
				if (visit[currentEdge.vertex] == true)
					continue;
			
				// 방문체크
				visit[currentEdge.vertex] = true;

				// 현재 정점과 연결된 모든 정점에 대해
				for (Edge edge : graph[currentEdge.vertex]) {
					
					// 연결된 정점의 경로값을 갱신하지 않았고(방문하지 않았고),
					// 현재 경로값보다 현재 정점을 경유해서 가는 것이 더 유리하다면 (경로값이 최소라면)
					if (visit[edge.vertex] == false && 
							distance[edge.vertex] > distance[currentEdge.vertex] + edge.weight) {
						// 최솟값을 갱신하고 큐에 삽입(다음 경유지가 됨)
						distance[edge.vertex] = distance[currentEdge.vertex] + edge.weight;
						priorityQueue.offer(new Edge(edge.vertex, distance[edge.vertex]));
					}
				}
				
			}
		}
		
		// 출력
		for (int i = 1; i <= V; i++) {
			// 경로가 존재하지 않는 경우 (경로값으로 초기화 한 최댓값이 갱신되지 않음)
			if (distance[i] == Integer.MAX_VALUE) {
				System.out.println(notExist);	// INF출력
				continue;
			}
			// 시작점으로부터 각 정점까지 최단 경로의 경로값 출력
			System.out.println(distance[i]);
		}
		
	}
	
	static class Edge implements Comparable<Edge> {
		int vertex, weight;

		public Edge(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge edge) {
			return this.weight - edge.weight;
		}
	}
	
}
