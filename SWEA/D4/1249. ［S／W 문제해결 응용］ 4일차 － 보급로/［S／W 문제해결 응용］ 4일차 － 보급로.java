
	/*
	
	[1249] [S/W 문제해결 응용] 4일차 - 보급로
	공병대는 출발지(S) 에서 도착지(G)까지 가기 위한 도로 복구 작업을 빠른 시간 내에 수행하려고 한다.
	출발지는 좌상단의 칸(S)이고 도착지는 우하단의 칸(G)가 된다.
	지도 정보에는 각 칸마다 파여진 도로의 깊이가 주어진다. 현재 위치한 칸의 도로를 복구해야만 다른 곳으로 이동할 수 있다.
	깊이가 1이라면 복구에 드는 시간이 1이라고 가정한다.
	이동 경로는 상하좌우 방향으로 진행할 수 있으며, 한 칸씩 움직일 수 있다.
	이동하는 시간에 비해 복구하는데 필요한 시간은 매우 크다고 가정한다.
	따라서, 출발지에서 도착지까지 거리에 대해서는 고려할 필요가 없다.
	출발지에서 도착지까지 가는 경로 중에 복구 시간이 가장 짧은 경로에 대한 총 복구 시간을 구하시오.
	
	주어진 입력에서 출발지에서 도착지까지 가는 경로 중에 복구 작업에 드는 시간이 가장 작은 경로의 복구 시간을 출력하시오.
	
	1. 출발지부터 시작하여 bfs로 탐색한다
	2. 가장 짧은 복구시간을 구하기 위해 '깊이 오름차순'으로 정렬된 우선순위 큐를 사용한다
	3. enqueue할 때마다 해당 도로까지의 복구 시간 누적합을 저장한다
	4. 도착지에 도달했을 때의 누적합을 출력한다
	
	*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Solution {
	
	static int T, N, min;
	static int[][] map;
	static boolean[][] visited;
	static PriorityQueue<Road> priorityQueue;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
		// 입력 1 : 테스트 케이스의 수
		T = Integer.parseInt(bufferedReader.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			// 입력 2: 지도의 크기 N *N
			N = Integer.parseInt(bufferedReader.readLine());
			
			// 입력 3 : 지도 정보를 입력받아 map배열에 저장
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				String input = bufferedReader.readLine();
				for (int j = 0; j < N; j++) 
					map[i][j] = input.charAt(j) - '0';
			}
			
			// 가장 짧은 복구시간을 구하기 위해 깊이 오름차순으로 정렬된 우선순위 큐 사용
			priorityQueue = new PriorityQueue<>((r1, r2) -> { return r1.depth - r2.depth; });
			visited = new boolean[N][N];
			min = 0;
			
			// 출발지부터 탐색시작
			priorityQueue.offer(new Road(0, 0, map[0][0]));
			
			while (priorityQueue.isEmpty() == false) {
				Road currunt = priorityQueue.poll();
				
				// 큐에서 꺼낸 도로가 도착지이면 종료
				if (currunt.r == N-1 && currunt.c == N-1) {
					min = currunt.depth;
					break;
				}
				
				visited[currunt.r][currunt.c] = true;
				
				for (int d = 0; d < 4; d++) {
					int nr = currunt.r + dr[d];
					int nc = currunt.c + dc[d];
					
					if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] == true) 
						continue;
					
					// 도로의 좌표와 해당 도로까지의 복구 시간 누적합을 저장하여 우선순위 큐에 enqueue
					priorityQueue.offer(new Road(nr, nc, currunt.depth + map[nr][nc]));
				}

			}
			// 출력 : 복구 작업에 드는 시간이 가장 작은 경로의 복구 시간
			System.out.printf("#%d %d\n", tc, min);
			
		}
        bufferedReader.close();
		
	}
	
	/**
	 * 도로의 좌표와 파여진 깊이를 저장하는 클래스
	 * 
	 */
	static class Road implements Comparable<Road> {
		int r, c, depth;

		public Road(int r, int c, int depth) {
			super();
			this.r = r;
			this.c = c;
			this.depth = depth;
		}

		@Override
		public int compareTo(Road o) {
			return this.depth - o.depth;
		}
		
	}

}
