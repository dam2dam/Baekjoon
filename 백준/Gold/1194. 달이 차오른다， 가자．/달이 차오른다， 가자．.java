
	/*
	
	[1194] 달이 차오른다, 가자.
	민식이는 지금 미로 속에 있다. 미로는 직사각형 모양이고, 여행길을 떠나기 위해 미로를 탈출하려고 한다.
	미로는 다음과 같이 구성되어져있다.
		빈 칸: 언제나 이동할 수 있다. ('.')
		벽: 절대 이동할 수 없다. ('#')
		열쇠: 언제나 이동할 수 있다. 이 곳에 처음 들어가면 열쇠를 집는다. ('a', 'b', 'c', 'd', 'e', 'f')
		문: 대응하는 열쇠가 있을 때만 이동할 수 있다. ('A', 'B', 'C', 'D', 'E', 'F')
		민식이의 현재 위치: 빈 곳이고, 민식이가 현재 서 있는 곳이다. ('0')
		출구: 달이 차오르기 때문에, 민식이가 가야하는 곳이다. 이 곳에 오면 미로를 탈출한다. ('1')
	한 번의 움직임은 현재 위치에서 수평이나 수직으로 한 칸 이동하는 것이다.
	민식이가 미로를 탈출하는데 걸리는 이동 횟수의 최솟값을 구하는 프로그램을 작성하시오.
	
	첫째 줄에 민식이가 미로를 탈출하는데 드는 이동 횟수의 최솟값을 출력한다. 
	만약 민식이가 미로를 탈출 할 수 없으면, -1을 출력한다.
	
	1. 미로의 모양을 입력받아 maze배열에 저장하고 민식이의 현재위치를 따로 저장한 후, maze에서 지운다
	2. visited[보유키][행][열] 배열에 현재까지 보유한 (비트마스킹)키마다 방문 체크한다
	3. 이동 횟수 1회 마다 조건에 대해 처리 후 출구를 만나면 return 한다
	
	*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	final static char BLANK = '.';
	final static char WALL = '#';
	final static char MINSIK = '0';
	final static char EXIT = '1';
	
	static Point minsik = new Point();
	static int N, M;
	static char[][] maze;

	public static void main(String[] args) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		
		// 입력1: N:세로크기, M:가로크기
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());
		
		// 입력2 : 미로의 모양을 입력받아 maze배열에 저장
		maze = new char[N][M];
		for (int i = 0; i < N; i++) {
			String input = bufferedReader.readLine();
			for (int j = 0; j < M; j++) {
				maze[i][j] = input.charAt(j);
				
				// 민식이의 현재위치를 저장한 후, maze에서 지움
				if (maze[i][j] == MINSIK) {
					minsik.r = i;
					minsik.c = j;
					maze[i][j] = BLANK;
				}
			}
		}
		
		// 출력 : 민식이가 미로를 탈출하는데 드는 이동 횟수의 최솟값
		System.out.println(bfs(minsik));
		
		bufferedReader.close();
	}
	
	static int bfs(Point minsik) {
		
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		
		Queue<Point> queue = new ArrayDeque<Point>();
		// visited[보유키][행][열] // 키 (000000 ~ 111111)
		boolean[][][] visited = new boolean[64][N][M];
		
		// 민식이의 초기 위치를 enqueue, 방문체크 (획득키 0)
		queue.offer(minsik);
		visited[0][minsik.r][minsik.c] = true;
		
		int moveCount = 1;	// 이동 횟수
		while (queue.isEmpty() == false) {
			int queueSize = queue.size();
			
			// 이동 횟수 1회 마다
			while (queueSize-- > 0) {
				Point current = queue.poll();
				
				for (int d = 0; d < 4; d++) {
					int nr = current.r + dr[d];
					int nc = current.c + dc[d];
					
					// 범위 체크
					if (nr < 0 || nr >= N || nc < 0 || nc >= M)
						continue;
					
					// 다음 칸
					char next = maze[nr][nc];
					
					// 방문했거나 벽이면 continue
					if (visited[current.key][nr][nc] == true || next == WALL)
						continue;
					
					// 다음 칸이 출구이면 미로 탈출
					if (next == EXIT)
						return moveCount;
					
					// 다음 칸이 열쇠이면
					else if ('a' <= next && next <= 'f') {
						// 획득한 열쇠 저장
						int nextKey = getKey(current.key, next);
						// enqueue, 방문체크
						queue.offer(new Point(nr, nc, nextKey));
						visited[nextKey][nr][nc] = true;
					}
					
					// 다음 칸이 문이면
					else if ('A' <= next && next <= 'F') {
						// 열쇠 보유 여부에 따라, enqueue or continue
						if (haveKey(current.key, next) == true) {
							// enqueue, 방문체크
							queue.offer(new Point(nr, nc, current.key));
							visited[current.key][nr][nc] = true;
						}
					}
					// 다음 칸이 빈 칸이면
					else if (next == BLANK ) {
						// enqueue, 방문체크
						queue.offer(new Point(nr, nc, current.key));
						visited[current.key][nr][nc] = true;
					}
				}
			}
			// 이동 횟수 증가
			moveCount++;
		}
		// 미로를 탈출할 수 없으면 return -1
		return -1;
	}

	/**
	 * 키를 저장하는 함수
	 * @param key 현재까지 보유 키
	 * @param next 다음 칸 획득 키
	 * @return 현재 키에 획득 키를 더한 값
	 */
	static int getKey(int key, char next) {
		return key | (1 << (next - 'a'));
	}

	/**
	 * 문에 대응하는 키 보유 여부를 확인하는 함수
	 * @param key 현재까지 보유 키
	 * @param next 키 보유 여부를 확인할 문
	 * @return 키 보유 여부
	 */
	static boolean haveKey(int key, char next) {
		return (key & (1 << (next - 'A'))) > 0;
	}
	
	static class Point {
		int r, c, key;

		public Point() {
			super();
		}
		/**
		 * @param r 행 좌표
		 * @param c 열 좌표
		 * @param key 현재 보유하고 있는 키
		 */
		public Point(int r, int c, int key) {
			super();
			this.r = r;
			this.c = c;
			this.key = key;
		}
	}

}
