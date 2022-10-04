import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	final static int BLANK = 0;
	final static int WALL = 1;
	final static int VIRUS = 2;
	
	static int N, M, max;
	static int[][] map;
	static List<Point> blanks;
	static int[] selectPoint;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		// 입력1 : N:세로크기, M:가로크기
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());
		
		map = new int[N][M];
		blanks = new ArrayList<Point>();
		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
				if (map[i][j] == 0) {
					blanks.add(new Point(i, j));
				}
			}
		}
		
//		for (int[] is : map) {
//			System.out.println(Arrays.toString(is));
//		}
		
		selectPoint = new int[3];
		max = Integer.MIN_VALUE;
		selectWall(0, 0);
		System.out.println(max);
		bufferedReader.close();
	}
	
	static void selectWall(int start, int count) {
		
		if (count == 3) {
			
			int[][] newMap = new int[N][M];
			copyArray(newMap);
			
			buildWall(newMap, selectPoint);
			
			boolean[][] visited = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (newMap[i][j] == VIRUS && visited[i][j] == false) {
						spreadVirus(newMap, visited, i, j);
					}
				}
			}
			
			updateAreaMaxCount(newMap);
			return;
		}
		
		for (int i = start; i < blanks.size(); i++) {
			selectPoint[count] = i;
			selectWall(i+1, count+1);
		}
	}
	
	static void buildWall(int[][] copiedMap, int[] selectPoint) {
		
		for (int point : selectPoint) {
			Point current = blanks.get(point);
			copiedMap[current.r][current.c] = WALL;
		}
		
	}

	static void spreadVirus(int[][] copiedMap, boolean[][] visited, int r, int c) {
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		
		Queue<Point> queue = new ArrayDeque<Point>();
		
		queue.offer(new Point(r, c));
		visited[r][c] = true;
		
		while (queue.isEmpty() == false) {
			Point currentVirus = queue.poll();
			
			for (int d = 0; d < 4; d++) {
				int nr = currentVirus.r + dr[d];
				int nc = currentVirus.c + dc[d];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= M || 
						visited[nr][nc] == true || copiedMap[nr][nc] == WALL) {
					continue;
				}
				
				if (copiedMap[nr][nc] == BLANK) {
					copiedMap[nr][nc] = VIRUS;
					visited[nr][nc] = true;
					queue.offer(new Point(nr, nc));
				}
			}
		}
		
	}

	static void updateAreaMaxCount(int[][] newMap) {
		int areaCount = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (newMap[i][j] == BLANK) {
					areaCount++;
				}
			}
		}
		
		max = Math.max(max, areaCount);
	}
	
	static void copyArray(int[][] newMap) {
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				newMap[i][j] = map[i][j];
			}
		}

	}
	static class Point {
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
}
