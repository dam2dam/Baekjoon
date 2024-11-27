import java.util.ArrayDeque;

class Solution {
    
    static final char START = 'R';
	static final char GOAL = 'G';
	static final char BLOCK = 'D';
	static final int[] dr = {-1, 0, 1, 0};
	static final int[] dc = {0, 1, 0, -1};

	static int R, C;
	static char[][] map;
	static boolean[][] visited;
    
    public int solution(String[] board) {
    	R = board.length;
		C = board[0].length();
		map = new char[R][C];
        
		int startR = 0;
		int startC = 0;
        
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				char space = board[i].charAt(j);
				map[i][j] = space;
				// 로봇의 처음 위치 저장
				if (space == START) {
					startR = i;
					startC = j;
				}
			}
		}
		visited = new boolean[R][C];
		int answer = move(startR, startC);
        return answer;
    }
    
   
	/**
	 * 로봇이 이동하는 메서드
	 * @param row 로봇 시작 위치 행
	 * @param col 로봇 시작 위치 열
	 * @return 최소 이동 횟수
	 */
	static int move(int row, int col) {
		ArrayDeque<Robot> queue = new ArrayDeque<>();
		queue.offer(new Robot(row, col, 0));
		visited[row][col] = true;

		while (!queue.isEmpty()) {
			Robot robot = queue.poll();
			int r = robot.r;
			int c = robot.c;

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				// 범위를 넘어가지 않고, 장애물이 없다면 이동
				while (inRange(nr, nc) && map[nr][nc] != BLOCK) {
					nr += dr[d];
					nc += dc[d];
				}
				// 현재 위치 복귀
				nr -= dr[d];
				nc -= dc[d];

				// 목표 위치에 도달했다면
				if (map[nr][nc] == GOAL) {
					// 이동 횟수 반환
					return robot.count + 1;
				}

				// 이미 방문했으면 넘어감
				if (visited[nr][nc]) {
					continue;
				}
				queue.offer(new Robot(nr, nc, robot.count + 1));
				visited[nr][nc] = true;
			}
		}
		return -1;
	}

	/**
	 * 범위 체크하는 메서드
	 * @param r 행
	 * @param c 열
	 * @return 범위 안에 드는지 여부
	 */
	static boolean inRange(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}

	static class Robot {
		int r;
		int c;
		int count;

		/**
		 * @param r 현재 행
		 * @param c 현재 열
		 * @param count 현재까지 이동 횟수
		 */
		public Robot(int r, int c, int count) {
			this.r = r;
			this.c = c;
			this.count = count;
		}
	}
}
