import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	static final int[] dr = {-1, 0, 1, 0};
	static final int[] dc = {0, 1, 0, -1};
	static final int BOARD_SIZE = 3;
	static final char BLACK = '*';
	static final char WHITE = 0;
	static int T;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(bufferedReader.readLine());
		for (int t = 0; t < T; t++) {
			int board = 0;
			int index = 0;
			for (int i = 0; i < BOARD_SIZE; i++) {
				String input = bufferedReader.readLine();
				for (int j = 0; j < BOARD_SIZE; j++) {
					if (input.charAt(j) == BLACK) {
						index = (BOARD_SIZE * BOARD_SIZE - 1) - (i * BOARD_SIZE + j);
						board += (1 << index);
					}
				}
			}
			System.out.println(search(board));
		}
		bufferedReader.close();
	}

	static int search(int board) {
		Queue<Case> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[512];

		queue.offer(new Case(board, -1, 0, 0));    // 시작 칸 제외 방지를 위해 -1 표기
		visited[board] = true;

		while (!queue.isEmpty()) {
			Case current = queue.poll();
			// 모든 칸이 흰색이라면 클릭 횟수 반환
			if (current.board == WHITE) {
				return current.count;
			}
			for (int i = 0; i < BOARD_SIZE; i++) {
				for (int j = 0; j < BOARD_SIZE; j++) {
					// 직전에 눌렀던 칸 제외
					if (current.r == i && current.c == j) {
						continue;
					}
					int nextBoard = click(current.board, i, j);
					// 이미 봤던 형태의 보드라면 넘어감
					if (visited[nextBoard]) {
						continue;
					}
					queue.offer(new Case(nextBoard, i, j, current.count + 1));
					visited[nextBoard] = true;
				}
			}
		}
		return 0;
	}

	/**
	 * 클릭한 칸과, 인접한 네 칸을 뒤집어준다
	 */
	static int click(int board, int r, int c) {
		// 클릭한 칸 뒤집기
		int index = (BOARD_SIZE * BOARD_SIZE - 1) - (r * BOARD_SIZE + c);
		board ^= (1 << index);

		// 인접한 네 칸 뒤집기
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (nr < 0 || nr >= BOARD_SIZE || nc < 0 || nc >= BOARD_SIZE) {
				continue;
			}
			index = (BOARD_SIZE * BOARD_SIZE - 1) - (nr * BOARD_SIZE + nc);
			board ^= (1 << index);
		}
		return board;
	}

	static class Case {
		int board;
		int r, c;
		int count;

		public Case(int board, int r, int c, int count) {
			this.board = board;
			this.r = r;
			this.c = c;
			this.count = count;
		}
	}
}
