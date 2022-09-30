import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int K, W, H;
	static int[][] map;
	
	static int[] drMonkey = {-1, 0, 1, 0};
	static int[] dcMonkey = {0, 1, 0, -1};
	static int[] drHorse = {-2, -1, 1, 2, 2, 1, -1, -2};
	static int[] dcHorse = {1, 2, 2, 1, -1, -2, -2, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		K = Integer.parseInt(bufferedReader.readLine());
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		W = Integer.parseInt(stringTokenizer.nextToken());
		H = Integer.parseInt(stringTokenizer.nextToken());
		
		map = new int[H][W];
		for (int i = 0; i < H; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}
		
//		for (int[] is : map) {
//			System.out.println(Arrays.toString(is));
//		}
		
		bfs(0, 0);
		
		System.out.println(moveCount);
		
	}
	static int moveCount;
	static void bfs(int y, int x) {
		Queue<Monkey> queue = new LinkedList();
		boolean[][][] visited = new boolean[H][W][K+1];
		queue.offer(new Monkey(y, x, 0));
		visited[y][x][0] = true;
		
		int cnt = 0;
		while (queue.isEmpty() == false) {
			int queueSize = queue.size();
			
			while (queueSize-- > 0) {
				Monkey current = queue.poll();
				
				if (current.r == H-1 && current.c == W-1) {
					moveCount = cnt;
					return;
				}
				
				int nr, nc;
				for (int d = 0; d < 4; d++) {
					nr = current.r + drMonkey[d];
					nc = current.c + dcMonkey[d];
					
					if (nr < 0 || nr >= H || nc < 0 || nc >= W || map[nr][nc] == 1
							|| visited[nr][nc][current.jump] == true) 
						continue;

					visited[nr][nc][current.jump] = true;
					queue.offer(new Monkey(nr, nc, current.jump));
				}
				
				for (int d = 0; d < 8; d++) {
					nr = current.r + drHorse[d];
					nc = current.c + dcHorse[d];
					
					if (nr < 0 || nr >= H || nc < 0 || nc >= W || map[nr][nc] == 1
							|| current.jump >= K || visited[nr][nc][current.jump+1] == true)
						continue;

					visited[nr][nc][current.jump+1] = true;
					queue.offer(new Monkey(nr, nc, current.jump+1));
				}
			}
			cnt++;
		}
		moveCount = -1;
	}
	
	static class Monkey {
		int r, c, jump;

		public Monkey(int r, int c, int jump) {
			super();
			this.r = r;
			this.c = c;
			this.jump = jump;
		}
		
	}
	
}
