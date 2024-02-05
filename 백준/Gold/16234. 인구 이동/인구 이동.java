import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static final int[] dr = {-1, 0, 1, 0};
	static final int[] dc = {0, 1, 0, -1};
	static final boolean CLOSE = false;
	static final boolean OPEN = true;
	static int N, L, R;
	static Country[][] map;
	static boolean[][] moved;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		N = Integer.parseInt(stringTokenizer.nextToken());
		L = Integer.parseInt(stringTokenizer.nextToken());
		R = Integer.parseInt(stringTokenizer.nextToken());

		map = new Country[N][N];
		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < N; j++) {
				int input = Integer.parseInt(stringTokenizer.nextToken());
				map[i][j] = new Country(input, new boolean[] {CLOSE, CLOSE, CLOSE, CLOSE});
			}
		}
		int day = 0;
		// 열린 국경선이 있다면
		while (openBorder()) {
			moved = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!moved[i][j]) {
						// 인구 이동
						move(i, j);
					}
				}
			}
			day++;
		}
		System.out.println(day);
		bufferedReader.close();
	}

	static boolean openBorder() {
		boolean result = false;

		boolean[][] opened = new boolean[N][N];
		int nr, nc;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				for (int d = 0; d < 4; d++) {
					opened[r][c] = true;

					nr = r + dr[d];
					nc = c + dc[d];
					if (nr < 0 || nr >= N || nc < 0 || nc >= N || opened[nr][nc]) {
						continue;
					}
					int difference = Math.abs(map[r][c].population - map[nr][nc].population);
					if (difference < L || difference > R) {
						continue;
					}
					// 해당 방향의 국경선 열기
					map[r][c].direction[d] = OPEN;
					// 해당 방향에 있는 나라의 국경선도 열기
					map[nr][nc].direction[(d + 2) % 4] = OPEN;

					// 국경선이 하나라도 열리면 return true
					result = true;
				}
			}
		}
		return result;
	}

	static void move(int r, int c) {
		int sum = 0;
		int count = 0;

		Queue<Point> queue = new ArrayDeque<>();

		// 연합 나라 리스트
		ArrayList<Point> unions = new ArrayList<>();
		unions.add(new Point(r, c));
		// 연합 인구 수의 합
		sum += map[r][c].population;
		// 연합 나라 수
		count++;

		queue.offer(new Point(r, c));
		moved[r][c] = true;

		int nr, nc;
		while (!queue.isEmpty()) {
			Point current = queue.poll();

			for (int d = 0; d < 4; d++) {
				nr = current.r + dr[d];
				nc = current.c + dc[d];

				// 범위체크, 방문체크, 국경선 열렸는지 체크
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || moved[nr][nc]
					|| !map[current.r][current.c].direction[d] || !map[nr][nc].direction[(d + 2) % 4]) {
					continue;
				}
				unions.add(new Point(nr, nc));
				sum += map[nr][nc].population;
				count++;

				queue.offer(new Point(nr, nc));
				moved[nr][nc] = true;
			}
		}

		// 연합 나라 없이 단독 국가라면 인구 수 갱신하지 않음
		if (unions.size() <= 1) {
			return;
		}
		for (Point current : unions) {
			// 연합 나라의 인구 수 갱신
			map[current.r][current.c].population = sum / count;
			for (int d = 0; d < 4; d++) {
				// 국경선 닫기
				map[current.r][current.c].direction[d] = CLOSE;
			}
		}
	}

	static class Country {
		int population;
		// { north, east, south, west }
		boolean[] direction;

		public Country(int population, boolean[] direction) {
			this.population = population;
			this.direction = direction;
		}
	}

	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
