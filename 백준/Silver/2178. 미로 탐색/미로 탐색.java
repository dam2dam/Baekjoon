	/*
	
	[2178] 미로 탐색
	N×M크기의 배열로 표현되는 미로가 있다.
	미로에서 1은 이동할 수 있는 칸을 나타내고, 0은 이동할 수 없는 칸을 나타낸다. 
	이러한 미로가 주어졌을 때, (1, 1)에서 출발하여 (N, M)의 위치로 이동할 때 지나야 하는 최소의 칸 수를 구하는 프로그램을 작성하시오. 
	한 칸에서 다른 칸으로 이동할 때, 서로 인접한 칸으로만 이동할 수 있다.
	
	첫째 줄에 지나야 하는 최소의 칸 수를 출력한다. 항상 도착위치로 이동할 수 있는 경우만 입력으로 주어진다.
	
	1. 현재 위치와 이동한 칸의 수를 나타내는 클래스를 생성한다 : Point
	2. bfs로 탐색하며 queue와 Point객체를 사용한다
	3. 한 칸 이동할 때마다 count가 증가하고 도착 위치에 도달하면 count를 출력한다
	
	*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[][] maze;
	static boolean[][] visit;
	final static int[] dy = {-1, 1, 0, 0};
	final static int[] dx = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		
		// 입력1 : N:행의 수, M:열의 수
		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());
		
		// 입력2 : 미로를 입력받아 배열에 저장
		maze = new int[N+1][M+1];
		for (int i = 1; i <= N; i++) {
			String input = bufferedReader.readLine();
			for (int j = 1; j <= M; j++) {
				maze[i][j] = input.charAt(j-1) - '0';
			}
		}
		visit = new boolean[N+1][M+1];
		
		// bfs 탐색을 위한 큐
		Queue<Point> queue = new LinkedList<Point>();
		// 시작 위치를 enqueue, 방문 체크
		queue.offer(new Point(1, 1, 1));	// (행좌표, 열좌표, 시작 위치부터 칸을 세고 시작)
		visit[1][1] = true;
		
		while(queue.isEmpty() == false) {
			Point current = queue.poll();	// 현재 위치 = dequeue
			
			// 도착 위치에 도달하면 출력
			if (current.x == M && current.y == N) {	
				System.out.println(current.count);
				break;
			}
			
			// 사방 탐색
			for (int d = 0; d < 4; d++) {
				int ny = current.y + dy[d];
				int nx = current.x + dx[d];
				if (ny < 1 || ny > N || nx < 1 || nx > M ||		// 미로 밖으로 나가거나,
						visit[ny][nx] == true || maze[ny][nx] == 0)	// 이미 방문했거나, 이동할 수 없는 칸이면 넘어감
					continue;
				
				queue.offer(new Point(ny, nx, current.count+1));	// 다음 탐색을 위해 enqueue
				visit[ny][nx] = true;
			}
		}
		
	}
	
	static class Point {
		int y, x;
		int count;
		
		public Point(int y, int x, int count) {
			this.y = y;
			this.x = x;
			this.count = count;
		}
	}

}
