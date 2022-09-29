
/*

[1953] [모의 SW 역량테스트] 탈주범 검거
교도소로 이송 중이던 흉악범이 탈출하는 사건이 발생하여 수색에 나섰다.
탈주범은 탈출한 지 한 시간 뒤, 맨홀 뚜껑을 통해 지하터널의 어느 한 지점으로 들어갔으며,
터널끼리 연결이 되어 있는 경우 이동이 가능하므로 탈주범이 있을 수 있는 위치의 개수를 계산하여야 한다.
탈주범은 시간당 1의 거리를 움직일 수 있다.
지하 터널은 총 7 종류의 터널 구조물로 구성되어 있으며 각 구조물 별 설명은 다음과 같다.
	1) 상, 하, 좌, 우에 있는 터널과 연결
	2) 상, 하에 있는 터널과 연결
	3) 좌, 우에 있는 터널과 연결
	4) 상, 우에 있는 터널과 연결
	5) 하, 우에 있는 터널과 연결
	6) 하, 좌에 있는 터널과 연결
	7) 상, 좌에 있는 터널과 연결
	
지하 터널 지도와 맨홀 뚜껑의 위치, 경과된 시간이 주어질 때 탈주범이 위치할 수 있는 장소의 개수를 계산하는 프로그램을 작성하라.

1. 지하 터널 지도 정보를 입력받아 Tunnel배열에 저장한다
	1-1. Tunnel(터널 여부, 상 연결여부, 우 연결여부, 하 연결여부, 좌 연결여부, 행 위치, 열 위치)
2. 맨홀 뚜껑 위치의 터널을 큐에 넣고 탐색을 시작한다
3. 이동 가능한 조건을 만족하면 큐에 넣으면서 1시간 단위로 탐색한다
4. 주어진 경과 시간에 도달하면 탐색을 종료하고 탈주범이 위치할 수 있는 장소의 계수를 계산하여 출력한다

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	static int T, N, M, R, C, L, time, possiblePlace;
	static Tunnel[][] map;
	static Tunnel tunnel;
	static Queue<Tunnel> queue;
	static boolean[][] visited;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	// 델타 배열 인덱스가 의미하는 방향
	final static int UP = 0;
	final static int RIGHT = 1;
	final static int DOWN = 2;
	final static int LEFT = 3;	

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		
		// 입력1 : 테스트 케이스의 개수
		T = Integer.parseInt(bufferedReader.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			// 입력2 : 지하터널 지도의 크기, 맨홀뚜껑의 위치, 탈출 후 소요된 시간 
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			N = Integer.parseInt(stringTokenizer.nextToken());	// 지하터널 지도의 세로 크기
			M = Integer.parseInt(stringTokenizer.nextToken());	// 지하터널 지도의 가로 크기
			R = Integer.parseInt(stringTokenizer.nextToken());	// 맨홀 뚜껑의 세로 위치
			C = Integer.parseInt(stringTokenizer.nextToken());	// 맨홀 뚜껑의 가로 위치
			L = Integer.parseInt(stringTokenizer.nextToken());	// 탈출 후 소요된 시간
			
			// 입력3 : 지하 터널 지도 정보를 입력받아 Tunnel배열에 저장
			map = new Tunnel[N][M];
			for (int i = 0; i < N; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				for (int j = 0; j < M; j++) {
					int input = Integer.parseInt(stringTokenizer.nextToken());
					
					// Tunnel(터널 여부, 상 연결여부, 우 연결여부, 하 연결여부, 좌 연결여부, 행 위치, 열 위치)
					
					// 터널이 없는 장소 (터널여부 false)
					if (input == 0)
						tunnel = new Tunnel(false, false, false, false, false, i, j);
					
					// 터널이 있는 장소 (터널여부 true)
					else
						switch (input) { // 상 우 하 좌
						case 1:
							tunnel = new Tunnel(true, true, true, true, true, i, j);
							break;
						case 2:
							tunnel = new Tunnel(true, true, false, true, false, i, j);
							break;
						case 3:
							tunnel = new Tunnel(true, false, true, false, true, i, j);
							break;
						case 4:
							tunnel = new Tunnel(true, true, true, false, false, i, j);
							break;
						case 5:
							tunnel = new Tunnel(true, false, true, true, false, i, j);
							break;
						case 6:
							tunnel = new Tunnel(true, false, false, true, true, i, j);
							break;
						case 7:
							tunnel = new Tunnel(true, true, false, false, true, i, j);
							break;
						}
					
					map[i][j] = tunnel;
				}
			}
			
//			for (Tunnel[] ts : map) {
//				for (Tunnel t : ts) {
//					System.out.print(t + " ");
//				}
//				System.out.println();
//			}
			
			queue = new ArrayDeque<>();		// 탈출 가능한 터널을 저장할 큐
			visited = new boolean[N][M];	// 방문 체크할 배열
			
			queue.offer(map[R][C]);	// 맨홀 뚜껑의 위치부터 탐색 시작
			time = 0;	// 소요 시간
			
			// 탈출이 가능할 때까지, 경과된 시간이 될 때까지 반복
			while (queue.isEmpty() == false && time != L) {
				int currentQueueSize = queue.size();
				
				// 1 시간 단위로 탐색
				while (currentQueueSize-- > 0) {
					Tunnel currentTunnel = queue.poll();
					visited[currentTunnel.r][currentTunnel.c] = true;
					
					for (int d = 0; d < 4; d++) {
						int nr = currentTunnel.r + dr[d];
						int nc = currentTunnel.c + dc[d];
						
						// 이동하려는 위치가 범위를 넘으면 continue
						if (nr < 0 || nr >= N || nc < 0 || nc >= M ) 
							continue;
						
						// 이동하려는 위치에 있는 터널에 이미 방문했거나 터널이 아니면 continue
						Tunnel nextTunnel = map[nr][nc];
						if (visited[nr][nc] == true || nextTunnel.isTunnel == false)
							continue;
						
						// 상향 이동하려고 할 때, 현재 터널의 상향과 위쪽 터널의 하향이 뚫려있으면 이동 가능 
						if (d == UP && currentTunnel.up == true && nextTunnel.down == true)
							queue.offer(nextTunnel);
						
						// 우향 이동하려고 할 때, 현재 터널의 우향과 오른쪽 터널의 좌향이 뚫려있으면 이동 가능 
						else if (d == RIGHT && currentTunnel.right == true && nextTunnel.left == true)
							queue.offer(nextTunnel);
						
						// 하향 이동하려고 할 때, 현재 터널의 하향과 아래쪽 터널의 상향이 뚫려있으면 이동 가능 
						else if (d == DOWN && currentTunnel.down == true && nextTunnel.up == true)
							queue.offer(nextTunnel);
						
						// 좌향 이동하려고 할 때, 현재 터널의 좌향과 왼쪽 터널의 우향이 뚫려있으면 이동 가능 
						else if (d == LEFT && currentTunnel.left == true && nextTunnel.right == true)
							queue.offer(nextTunnel);
					}
				}
				// 1시간 지나면 time count
				time++;
				
				// 주어진 경과 시간과 같아지면 탐색 종료
				if (time == L) {
					break;
				}
			}
			// 출력 : 탈주범이 위치할 수 있는 장소의 개수를 계산하고 출력
			countPlace();
			System.out.printf("#%d %d\n", tc, possiblePlace);
//			for (boolean[] ts : visited) {
//				for (boolean t : ts) {
//					System.out.print(t + " ");
//				}
//				System.out.println();
//			}
		}
		bufferedReader.close();
	}
	
	/**
	 * 탈주범이 위치할 수 있는 장소의 개수를 계산
	 */
	private static void countPlace() {
		possiblePlace = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 방문가능했던 모든곳을 count
				if (visited[i][j] == true) {
					possiblePlace++;
				}
			}
		}
		
	}

	static class Tunnel {
		boolean isTunnel, up, right, down, left;
		int r, c;
		
		public Tunnel(boolean isTunnel, boolean up, boolean right, boolean down, boolean left, int r, int c) {
			super();
			this.isTunnel = isTunnel;
			this.up = up;
			this.right = right;
			this.down = down;
			this.left = left;
			this.r = r;
			this.c = c;
		}
	}
	
}
