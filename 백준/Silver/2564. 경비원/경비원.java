import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final int NORTH = 1;
	static final int SOUTH = 2;
	static final int WEST = 3;
	static final int EAST = 4;
	static final int STORE = 5;
	static final int DONGGEUN = 6;

	static final int[] dr = {0, 1, 0, -1};
	static final int[] dc = {1, 0, -1, 0};

	static int R, C;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		C = Integer.parseInt(stringTokenizer.nextToken());
		R = Integer.parseInt(stringTokenizer.nextToken());
		map = new int[R + 1][C + 1];
		int perimeter = (R * 2) + (C * 2);

		int S = Integer.parseInt(bufferedReader.readLine());
		int direction;
		int point;
		for (int i = 0; i < S; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			direction = Integer.parseInt(stringTokenizer.nextToken());
			point = Integer.parseInt(stringTokenizer.nextToken());
			checkMap(direction, point, STORE);
		}
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		direction = Integer.parseInt(stringTokenizer.nextToken());
		point = Integer.parseInt(stringTokenizer.nextToken());
		Point donggeun = checkMap(direction, point, DONGGEUN);

		int d;
		if (direction == NORTH) {
			d = 0;
		} else if (direction == EAST) {
			d = 1;
		} else {
			d = direction;
		}

		int r = donggeun.r;
		int c = donggeun.c;
		int step = 0;
		int answer = 0;
		while (true) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr < 0 || nr > R || nc < 0 || nc > C) {
				d = (d + 1) % 4;
				continue;
			}
			step++;
			if (step == perimeter) {
				break;
			}
			if (map[nr][nc] == STORE) {
				answer += Math.min(perimeter - step, step);
			}
			r = nr;
			c = nc;
		}
		System.out.println(answer);
		bufferedReader.close();
	}

	static Point checkMap(int direction, int point, int building) {
		Point here = new Point();
		switch (direction) {
			case NORTH:
				map[0][point] = building;
				here.r = 0;
				here.c = point;
				break;
			case SOUTH:
				map[R][point] = building;
				here.r = R;
				here.c = point;
				break;
			case WEST:
				map[point][0] = building;
				here.r = point;
				here.c = 0;
				break;
			case EAST:
				map[point][C] = building;
				here.r = point;
				here.c = C;
				break;
		}
		return here;
	}

	static class Point {
		int r, c;

		public Point() {
		}
	}
}
