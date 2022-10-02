
	/*
	
	[5656] [모의 SW 역량테스트] 벽돌 깨기
	구술을 쏘아 벽돌을 깨트리는 게임을 하려고 한다.
	구슬은 N번만 쏠 수 있고, 벽돌들의 정보는 아래와 같이 W x H 배열로 주어진다.
	게임의 규칙은 다음과 같다.
		1) 구슬은 좌, 우로만 움직일 수 있어서 항상 맨 위에 있는 벽돌만 깨트릴 수 있다.
		2) 벽돌은 숫자 1 ~ 9 로 표현되며, 구술이 명중한 벽돌은 상하좌우로 ( 벽돌에 적힌 숫자 - 1 ) 칸 만큼 같이 제거된다.
		3) 제거되는 범위 내에 있는 벽돌은 동시에 제거된다.
		4) 빈 공간이 있을 경우 벽돌은 밑으로 떨어지게 된다.
	N 개의 벽돌을 떨어트려 최대한 많은 벽돌을 제거하려고 한다.
	N, W, H, 그리고 벽돌들의 정보가 주어질 때, 남은 벽돌의 개수를 구하라
	
	1. 구슬을 쏠 위치를 중복 순열로 정한다
	2. 정해진 구슬의 위치에 따라 벽돌을 깨고, 그 벽돌의 상하좌우에 있는 (벽돌 숫자만큼의) 벽돌을 enqueue한다
	3. 구슬 한 개마다 제거범위 내에 있는 벽돌이 모두 깨졌으면 빈 공간이 있는 벽돌을 밑으로 떨어뜨린다
	4. 깰 수 있는 모든 벽돌을 깨뜨려 큐가 비어있게 되면 남아있는 벽돌의 수를 세어 최솟값을 갱신한다
	
	*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	static int T, N, W, H, min;
	static int[][] inputMap;
	static int[] select;
	static boolean[] selectVisited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		
		// 입력1 : 테스트 케이스의 수
		T = Integer.parseInt(bufferedReader.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			// 입력2 : N:구슬을 쏘는 횟수, W:가로길이, H:세로길이
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			N = Integer.parseInt(stringTokenizer.nextToken());
			W = Integer.parseInt(stringTokenizer.nextToken());
			H = Integer.parseInt(stringTokenizer.nextToken());
			
			// 입력3 : 벽돌의 정보를 입력받아 inputMap배열에 저장
			inputMap = new int[H][W];
			for (int i = 0; i < H; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				for (int j = 0; j < W; j++) {
					inputMap[i][j] = Integer.parseInt(stringTokenizer.nextToken());
				}
			}
			
			min = Integer.MAX_VALUE;	// 벽돌을 깨뜨렸을 때 남은 벽돌의 최솟값을 저장
			select = new int[N];	// 구슬을 쏘는 열을 저장할 배열
			selectPoint(0);			// 구슬을 쏘는 열 정하기 시작
			
			// 출력 : 남아있을 수 있는 벽돌의 최솟값
			System.out.printf("#%d %d\n", tc, min);
			
		}
	
	}
	
	/**
	 * 구슬을  쏠 열을 정하는 함수
	 * @param count 쏠 곳이 정해진 구슬의 개수
	 */
	static void selectPoint(int count) {
		
		// 모든 구슬의 위치가 정해졌다면
		if (count == N) {
			// 벽돌 정보 복사
			int[][] tempMap = new int[H][W];
			for (int i = 0; i < H; i++) 
				for (int j = 0; j < W; j++) 
					tempMap[i][j] = inputMap[i][j];

			// 각 구슬의 위치 순서대로 벽돌 깨기 
			for (int i = 0; i < select.length; i++)
				breakbricks(select[i], tempMap);
			
			// 남아있는 벽돌 세기
			countBricks(tempMap);
			return;
		}
		
		for (int i = 0; i < W; i++) {
			select[count] = i;
			selectPoint(count + 1);
		}
	}
	
	/**
	 * 벽돌을 깨뜨리는 함수
	 * @param currentSelectIndex 현재 구슬을 쏘는 열
	 * @param map 정해진 구슬의 위치에 따라 깨지는 현재 벽돌 정보
	 */
	static void breakbricks(int currentSelectIndex, int[][] map) {
		Queue<Brick> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[H][W];
		
		// 현재 구슬을 쏘려고 하는 열에서 가장 위에 있는 벽돌을 enqueue
		for (int i = 0; i < H; i++) {
			// 벽돌이 없다면 넘어감
			if (map[i][currentSelectIndex] == 0)
				continue;
			// 벽돌이 있다면 enqueue, 방문체크 후 break
			else {
				queue.offer(new Brick(i, currentSelectIndex, map[i][currentSelectIndex]));
				visited[i][currentSelectIndex] = true;
				break;
			}
		}
		
		while (queue.isEmpty() == false) {
			// 큐에서 꺼낸 벽돌 깨뜨리기
			Brick current = queue.poll();
			int y = current.y;
			int x = current.x;
			map[y][x] = 0;
			
			int ny = 0;
			int nx = 0;
			
			// 상 : 현재 벽돌에 적힌 숫자만큼 위에 있는 벽돌
			for (int i = 1; i < current.value; i++) {
				ny = y - i;
				nx = x;
				// 범위 안에 있고, 방문하지 않았고, 벽돌이 있다면
				if (0 <= ny && visited[ny][nx] == false && map[ny][nx] > 0) {
					// enqueue 후 방문체크
					queue.offer(new Brick(ny, nx, map[ny][nx]));
					visited[ny][nx] = true;
				}
			}
			
			// 하  : 현재 벽돌에 적힌 숫자만큼 아래에 있는 벽돌
			for (int i = 1; i < current.value; i++) {
				ny = y + i;
				nx = x;
				// 범위 안에 있고, 방문하지 않았고, 벽돌이 있다면
				if (ny < H && visited[ny][nx] == false && map[ny][nx] > 0) {
					// enqueue 후 방문체크
					queue.offer(new Brick(ny, nx, map[ny][nx]));
					visited[ny][nx] = true;
				}
			}
			
			// 좌 : 현재 벽돌에 적힌 숫자만큼 왼쪽에 있는 벽돌
			for (int i = 1; i < current.value; i++) {
				ny = y;
				nx = x - i;
				// 범위 안에 있고, 방문하지 않았고, 벽돌이 있다면
				if (0 <= nx && visited[ny][nx] == false && map[ny][nx] > 0) {
					// enqueue 후 방문체크
					queue.offer(new Brick(ny, nx, map[ny][nx]));
					visited[ny][nx] = true;
				}
			}
			
			// 우 : 현재 벽돌에 적힌 숫자만큼 오른쪽에 있는 벽돌
			for (int i = 1; i < current.value; i++) {
				ny = y;
				nx = x + i;
				// 범위 안에 있고, 방문하지 않았고, 벽돌이 있다면
				if (nx < W && visited[ny][nx] == false && map[ny][nx] > 0) {
					// enqueue 후 방문체크
					queue.offer(new Brick(ny, nx, map[ny][nx]));
					visited[ny][nx] = true;
				}
			}
		}
		// 빈 공간이 있을 경우 벽돌 떨어뜨리기
		moveBricks(map);
	}
	
	/**
	 * 벽돌을 떨어뜨리는 함수
	 * @param map 현재 벽돌 정보
	 */
	static void moveBricks(int[][] map) {
		int zeroCount;
		for (int j = 0; j < W; j++) {
			zeroCount = 0;
			// 마지막 행부터 위로 올라오면서
			for (int i = H-1; i >= 0; i--) {
				// 벽돌이 없다면 빈 공간의 수 count
				if (map[i][j] == 0) 
					zeroCount++;
				
				// 벽돌이 있다면
				else if (map[i][j] > 0 && zeroCount > 0) {
					// 빈 공간만큼 아래로 현재 벽돌을 내리고
					map[i+zeroCount][j] = map[i][j];
					// 현재 벽돌 없애기
					map[i][j] = 0;
				}
			}
		}
	}
	
	/**
	 * 남아있는 벽돌을 세는 함수
	 * @param map 현재 벽돌 정보
	 */
	static void countBricks(int[][] map) {
		int brickCount = 0;
		for (int i = 0; i < H; i++) 
			for (int j = 0; j < W; j++) 
				if (map[i][j] > 0) 
					brickCount++;

		// 남은 벽돌의 최솟값 갱신
		min = Math.min(min, brickCount);
	}
	
	/**
	 * 벽돌 정보를 저장하는 클래스
	 *
	 */
	static class Brick {
		int y, x, value;

		/**
		 * @param y 행좌표
		 * @param x 열좌표
		 * @param value	벽돌 숫자
		 */
		public Brick(int y, int x, int value) {
			super();
			this.y = y;
			this.x = x;
			this.value = value;
		}
	}
}
