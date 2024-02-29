import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final int NO = 0;
	static final char UP = 'U';
	static final char DOWN = 'D';
	static final char LEFT = 'L';
	static final char RIGHT = 'R';
	static final int[] dr = {-1, 1, 0, 0};
	static final int[] dc = {0, 0, -1, 1};

	static int visitNumber, savezoneCount;
	static int[][] map, visited;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		int N = Integer.parseInt(stringTokenizer.nextToken());
		int M = Integer.parseInt(stringTokenizer.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String input = bufferedReader.readLine();
			for (int j = 0; j < M; j++) {
				switch (input.charAt(j)) {
					case UP:
						map[i][j] = 0;
						break;
					case DOWN:
						map[i][j] = 1;
						break;
					case LEFT:
						map[i][j] = 2;
						break;
					case RIGHT:
						map[i][j] = 3;
						break;
				}
			}
		}
		visited = new int[N][M];
		visitNumber = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j] == NO) {
					move(i, j);
				}
			}
		}
		System.out.println(savezoneCount);
		bufferedReader.close();
	}

	static void move(int r, int c) {
		// 방문체크
		visited[r][c] = visitNumber;

		int nr = r + dr[map[r][c]];
		int nc = c + dc[map[r][c]];
		// 현재 칸이 가리키는 방향 칸을 방문한 적이 없다면 간다.
		if (visited[nr][nc] == NO) {
			move(nr, nc);
		} else {
			// 현재 시점과 같은 시점에 방문했다면 사이클을 이루므로 SAFE ZONE 추가하기
			// (다른 시점에 방문했다면 현재 회원들과 합쳐 하나의 그룹이 되므로 추가하지 않음)
			if (visited[nr][nc] == visitNumber) {
				savezoneCount++;
			}
			// 다음 시점의 방문 번호
			visitNumber++;
		}
	}
}
