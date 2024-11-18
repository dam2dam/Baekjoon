class Solution {
    
    static final int BOMB = 1;
    static final int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
	static final int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};
    static int safeZoneCount;
    
    public int solution(int[][] board) {
        int N = board.length;

		safeZoneCount = checkSafeZone(board, N);
		if (safeZoneCount > 0) {
			boolean[][] visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (board[i][j] != BOMB) {
						continue;
					}
					makeNotSafeZone(i, j, N, board, visited);
				}
			}
		}
        return safeZoneCount;
    }
    static int checkSafeZone(int[][] board, int N) {
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == BOMB) {
					continue;
				}
				count++;
			}
		}
		return count;
	}

	static void makeNotSafeZone(int row, int col, int N, int[][] board, boolean[][] visited) {
		for (int d = 0; d < 8; d++) {
			int nr = row + dr[d];
			int nc = col + dc[d];
			if (checkRange(nr, nc, N)) {
				continue;
			}
			if (board[nr][nc] == BOMB || visited[nr][nc]) {
				continue;
			}
			visited[nr][nc] = true;
			safeZoneCount--;
		}
	}

	static boolean checkRange(int nr, int nc, int N) {
		return nr < 0 || nr >= N || nc < 0 || nc >= N;
	}
}