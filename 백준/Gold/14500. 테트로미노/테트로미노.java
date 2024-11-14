import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final int[] dr = {-1, 0, 1, 0};
	static final int[] dc = {0, 1, 0, -1};
	static final int CENTER = 2;
	static final int FINISH = 4;

	static int N, M, max;
	static int[][] paper;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 종이의 각 칸에 쓰인 정수 입력
		paper = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		max = 0;
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				dfs(1, paper[i][j], i, j);
				visited[i][j] = false;
			}
		}
		System.out.println(max);

		br.close();
	}

	/**
	 * 4칸을 탐색하는 메서드
	 * @param count 현재까지 탐색한 칸의 수
	 * @param sum 현재까지 탐색한 칸에 쓰여있던 정수의 합
	 * @param r 현재 행
	 * @param c 현재 열
	 */
	static void dfs(int count, int sum, int r, int c) {
		// 4개의 칸을 탐색했다면
		if (count == FINISH) {
			// 최댓값 갱신 후 반환
			if (sum > max) {
				max = sum;
			}
			return;
		}
		// (상 -> 우 -> 하 -> 좌) 탐색
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc]) {
				continue;
			}
			visited[nr][nc] = true;
			// (ㅗ ㅏ ㅜ ㅓ)모양의 경우 한 줄로 네 칸을 모두 탐색할 수 없으므로 중간 칸에서 다른 방향을 다시 탐색
			if (count == CENTER) {
				dfs(count + 1, sum + paper[nr][nc], r, c);
			}
			// 다른 모양은 모두 한 줄로 탐색해서 만들 수 있으므로 계속 탐색
			dfs(count + 1, sum + paper[nr][nc], nr, nc);
			visited[nr][nc] = false;
		}
	}
}

