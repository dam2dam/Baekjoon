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
		
		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());
		
		maze = new int[N+1][M+1];
		visit = new boolean[N+1][M+1];
		for (int i = 1; i <= N; i++) {
			String input = bufferedReader.readLine();
			for (int j = 1; j <= M; j++) {
				maze[i][j] = input.charAt(j-1) - '0';
			}
		}
		
		Queue<Point> queue = new LinkedList<Point>();
		queue.offer(new Point(1, 1, 1));
		visit[1][1] = true;
		
		while(queue.isEmpty() == false) {
			Point current = queue.poll();
			
			if (current.x == M && current.y == N) {
				System.out.println(current.count);
				break;
			}
			
			for (int d = 0; d < 4; d++) {
				int ny = current.y + dy[d];
				int nx = current.x + dx[d];
				if (ny < 1 || ny > N || nx < 1 || nx > M || 
						visit[ny][nx] == true || maze[ny][nx] == 0) {
					continue;
				}
				queue.offer(new Point(ny, nx, current.count+1));
				visit[ny][nx] = true;
			}
		}
		
	}
	static class Point {
		int y;
		int x;
		int count;
		public Point(int y, int x, int count) {
			super();
			this.y = y;
			this.x = x;
			this.count = count;
		}
	}

}
