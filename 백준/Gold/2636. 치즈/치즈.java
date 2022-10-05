
	/*
	
	[2636] 치즈
	정사각형 칸들로 이루어진 사각형 모양의 판이 있고, 그 위에 얇은 치즈가 놓여 있다. 
	판의 가장자리에는 치즈가 놓여 있지 않으며 치즈에는 하나 이상의 구멍이 있을 수 있다.
	이 치즈를 공기 중에 놓으면 녹게 되는데 공기와 접촉된 칸은 한 시간이 지나면 녹아 없어진다.
	치즈의 구멍 속에는 공기가 없지만 구멍을 둘러싼 치즈가 녹아서 구멍이 열리면 구멍 속으로 공기가 들어가게 된다. 
	입력으로 사각형 모양의 판의 크기와 한 조각의 치즈가 판 위에 주어졌을 때, 
	공기 중에서 치즈가 모두 녹아 없어지는 데 걸리는 시간과, 모두 녹기 한 시간 전에 남아있는 치즈조각이 놓여 있는 칸의 개수를 구하는 프로그램을 작성하시오.
	
	첫째 줄에는 치즈가 모두 녹아서 없어지는 데 걸리는 시간을 출력하고, 
	둘째 줄에는 모두 녹기 한 시간 전에 남아있는 치즈조각이 놓여 있는 칸의 개수를 출력한다.
	
	1. 판의 모양을 입력받아 cheese배열에 저장하고 초기 상태의 판에서 치즈 칸의 개수를 센다
	2. 녹은 치즈를 표시하기 위해 새로운 판에 현재 판을 복사하고 치즈 판을 탐색하여 빈 칸과 맞닿은 치즈를 녹임 표시한다
	3. 녹지 않은 치즈 칸이 아직 남아있다면 녹임 표시한 치즈를 제거하고 현재 판의 상태를 원본에 덮어씌운다
	4. 2번,3번을 반복하고 녹지 않은 치즈 칸이 없다면, 경과 시간과 (3번의 덮어 씌우기 직전 상태의 판에서) 치즈 칸의 개수를 출력한다
	
	*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	final static int BLANK = 0;
	final static int CHEESE = 1;
	final static int MELT = 2;

	static int R, C, cheeseSize;
	static int[][] cheese;
	static int[][] newCheese;

	public static void main(String[] args) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		// 입력1 : R:판의 세로길이, C:판의 가로길이
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		R = Integer.parseInt(stringTokenizer.nextToken());
		C = Integer.parseInt(stringTokenizer.nextToken());

		// 입력2 : 판의 모양을 입력받아 cheese배열에 저장
		cheese = new int[R][C];
		for (int i = 0; i < R; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < C; j++) 
				cheese[i][j] = Integer.parseInt(stringTokenizer.nextToken());
		}
		
		// 경과 시간
		int time = 0;
		
		// 초기 상태의 치즈 칸 count
		cheeseSize = countCheese(cheese);
		
		// 치즈가 남아있으면 반복
		while (cheeseSize > 0) {
			// 경과 시간 증가
			time++;
			
			// 녹은 치즈를 표시하기 위해 새로운 판에 현재 판 복사
			newCheese = new int[R][C];
			copy(cheese, newCheese);
			
			// 치즈 녹이기 bfs
			meltCheese(newCheese);
			
			// 녹인 후 남은 치즈 개수 count
			cheeseSize = countCheese(newCheese);
			
			// 치즈가 모두 녹았다면 출력 후 종료
			if (cheeseSize <= 0) {
				// 출력1 : 치즈가 모두 녹아 없어지는 데 걸리는 시간
				System.out.println(time);
				// 출력2 : 치즈가 모두 녹기 한 시간 전에 치즈가 남아있는 칸의 개수
				System.out.println(countCheese(cheese));
				break;
			}
			
			// 녹은 치즈 판에서 제거
			removeMeltCheese(newCheese);
			
			// 다음 탐색을 위해 현재 판을 원본으로 복사
			copy(newCheese, cheese);
			
		}
	}

	/**
	 * 빈 칸에 맞닿은 치즈를 탐색하여 녹이는 함수
	 * @param newCheese 녹인 치즈를 표시할 새로운 판
	 */
	static void meltCheese(int[][] newCheese) {

		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};

		Queue<Point> queue = new ArrayDeque<Point>();
		boolean[][] visited = new boolean[R][C];

		// 맨 윗줄 첫째 칸부터 탐색 시작 (가장자리이므로 항상 빈 칸)
		queue.offer(new Point(0, 0));
		visited[0][0] = true;

		while (queue.isEmpty() == false) {
			Point current = queue.poll();

			for (int d = 0; d < 4; d++) {
				int nr = current.r + dr[d];
				int nc = current.c + dc[d];

				// 범위 체크, 방문 체크
				if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc] == true)
					continue;

				// 빈 칸이면 계속 탐색
				if (cheese[nr][nc] == BLANK) {
					queue.offer(new Point(nr, nc));
					visited[nr][nc] = true;
				}
				
				// 빈 칸에 맞닿은 치즈에 녹임 표시
				else if (cheese[nr][nc] == CHEESE)
					newCheese[nr][nc] = MELT;
			}
		}

	}
	
	/**
	 * 녹은 치즈를 판에서 제거하는 함수
	 * @param currentCheese 현재 판
	 */
	static void removeMeltCheese(int[][] currentCheese) {
		for (int i = 0; i < R; i++) 
			for (int j = 0; j < C; j++) 
				// 치즈가 녹아있다면 빈 칸으로 바꿈
				if (currentCheese[i][j] == MELT) 
					currentCheese[i][j] = BLANK;
	}
	
	/**
	 * 치즈 칸을 세는 함수
	 * @param currentCheese 현재 판
	 * @return 치즈 칸의 수
	 */
	static int countCheese(int[][] currentCheese) {
		int count = 0;
		for (int i = 0; i < R; i++) 
			for (int j = 0; j < C; j++) 
				if (currentCheese[i][j] == CHEESE) 
					count++;
		return count;
	}
	

	/**
	 * 배열을 복사하는 함수
	 * @param origin 원본 배열
	 * @param copied 복사된 배열
	 */
	 static void copy(int[][] origin, int[][] copied) {
		for (int i = 0; i < R; i++) 
			for (int j = 0; j < C; j++) 
				copied[i][j] = origin[i][j];
	 }
	 

	 static class Point {
		 int r, c;
		 /**
		  * @param r 행 좌표
		  * @param c 열 좌표
		  */
		 public Point(int r, int c) {
			 super();
			 this.r = r;
			 this.c = c;
		 }
	 }
	 
}
