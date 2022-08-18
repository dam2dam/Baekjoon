import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int R, C, max;
	static int[][] map;
	static boolean[] visit;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

		// 입력1 : R:세로 칸 수, C:가로 칸 수
		R = Integer.parseInt(stringTokenizer.nextToken());
		C = Integer.parseInt(stringTokenizer.nextToken());
		
		// 입력2 : 보드에 적힌 알파벳을 int 배열에 저장한다. A:0 ~ Z:25
		map = new int[R+1][C+1];
		String input;
		for (int i = 1; i <= R; i++) {
			input = bufferedReader.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j+1] = input.charAt(j) - 'A';
			}
		}
		
		visit = new boolean[26];	// 배열의 크기:알파벳 대문자의 개수
		
		max = Integer.MIN_VALUE;
		move(1, 1, 1);	// 1행 1열부터 count하고 시작
		
		// 출력 : 말이 지날 수 있는 최대 칸 수
		System.out.println(max);
	}

	static void move(int y, int x, int count) {
		visit[map[y][x]] = true;	// 알파벳을 인덱스로 사용하여 방문 표시
		max = Math.max(max, count);	// 최댓값 비교 후 갱신
		
		for (int d = 0; d < 4; d++) {
			int nr = y + dr[d];
			int nc = x + dc[d];
			if (nr < 1 || nr > R || nc < 1 || nc > C || visit[map[nr][nc]] == true)
				continue;
			
			move(nr, nc, count+1);	// 칸을 세면서 이동
		}
		visit[map[y][x]] = false;	// 다른 길을 찾기 위해 방문 표시 풀기
	}

}
