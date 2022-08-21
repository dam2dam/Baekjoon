import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bufferedReader.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			int N = Integer.parseInt(bufferedReader.readLine());
			
			int[][] numbers = new int[N][N];
			for (int i = 0; i < N; i++) {
				String input = bufferedReader.readLine();
				for (int j = 0; j < N; j++)
					numbers[i][j] = input.charAt(j) - '0';
			}
			
			int sum = 0;
			
			int[] dx = {-1, 0, 1, 0};
			int[] dy = {0, 1, 0, -1};
			
			Queue<Point> queue = new LinkedList<>();
			boolean[][] visit = new boolean[N][N];
			
			queue.offer(new Point(N/2, N/2));
			visit[N/2][N/2] = true;
			sum += numbers[N/2][N/2];
			
			while(queue.isEmpty() == false) {
				Point current = queue.poll();
				
				for (int d = 0; d < 4; d++) {
					int nx = current.x + dx[d];
					int ny = current.y + dy[d];
					int distance = Math.abs(nx - N/2) + Math.abs(ny - N/2);
					
					if (nx < 0 || nx >= N || ny < 0 || ny >= N || visit[nx][ny] == true || distance > N/2)
						continue;
					if (distance <= N/2) {
						queue.offer(new Point(nx, ny));
						visit[nx][ny] = true;
						sum += numbers[nx][ny];
					}
				}
			}
			
			System.out.printf("#%d %d\n", tc, sum);
		}
		bufferedReader.close();
	}
	
}
