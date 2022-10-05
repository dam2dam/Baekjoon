
	/*
	
	[17143] 낚시왕
	낚시왕은 처음에 1번 열의 한 칸 왼쪽에 있다. 
	다음은 1초 동안 일어나는 일이며, 아래 적힌 순서대로 일어난다. 낚시왕은 가장 오른쪽 열의 오른쪽 칸에 이동하면 이동을 멈춘다.
		1. 낚시왕이 오른쪽으로 한 칸 이동한다.
		2. 낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다. 상어를 잡으면 격자판에서 잡은 상어가 사라진다.
		3. 상어가 이동한다.
	상어는 입력으로 주어진 속도로 이동하고, 속도의 단위는 칸/초이다. 
	상어가 이동하려고 하는 칸이 격자판의 경계를 넘는 경우에는 방향을 반대로 바꿔서 속력을 유지한채로 이동한다.
	상어가 이동을 마친 후에 한 칸에 상어가 두 마리 이상 있을 수 있다. 이때는 크기가 가장 큰 상어가 나머지 상어를 모두 잡아먹는다.
	낚시왕이 상어 낚시를 하는 격자판의 상태가 주어졌을 때, 낚시왕이 잡은 상어 크기의 합을 구해보자.
	
	낚시왕이 잡은 상어 크기의 합을 출력한다.
	
	1. 상어의 정보를 입력받아 sharks 배열에 저장하고, 입력이 들어온 순서대로 상어에게 번호를 부여하여 map배열의 해당 상어 위치에 상어 번호를 저장한다
	2. 낚시왕의 위치에서 땅에서부터 가까운 순서대로 탐색하여 상어가 있다면 잡은 상어의 크기를 누적하고 상어를 제거한다
	3. 1초마다 새로운 맵에 상어의 이동을 기록하고 상어의 위치가 겹치는 칸이 있다면 잡아먹힌 상어를 제거한다
	4. 1초마다 기록된 새로운 맵을 다시 원본 맵에 복사하고 2,3,4번을 반복한다
	
	*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { 
	
	final static int UP = 1;
	final static int DOWN = 2;
	final static int RIGHT = 3;
	final static int LEFT = 4;
	
	static int R, C, numberOfShark, sharkR, sharkC, speed, direction, size, totalFishing;
	static Shark[] sharks;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		
		// 입력1 : R*C:격자판의 크기, 상어의 수
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		R = Integer.parseInt(stringTokenizer.nextToken());
		C = Integer.parseInt(stringTokenizer.nextToken());
		numberOfShark = Integer.parseInt(stringTokenizer.nextToken());
		
		// 입력2 : 상어의 정보를 입력받아 sharks 배열에 저장
		sharks = new Shark[numberOfShark+1];
		map = new int[R][C];
		for (int i = 0; i < numberOfShark; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			sharkR = Integer.parseInt(stringTokenizer.nextToken());		// 상어의 행 좌표
			sharkC = Integer.parseInt(stringTokenizer.nextToken());		// 상어의 열 좌표
			speed = Integer.parseInt(stringTokenizer.nextToken());		// 상어의 속력
			direction = Integer.parseInt(stringTokenizer.nextToken());	// 상어의 이동 방향
			size = Integer.parseInt(stringTokenizer.nextToken());		// 상어의 크기
			sharks[i+1] = new Shark(i+1, sharkR-1, sharkC-1, speed, direction, size);
			
			// 입력이 들어온 순서대로 상어에게 번호를 부여하여, map배열의 해당 상어 위치에 상어 번호 저장
			map[sharkR-1][sharkC-1] = i+1;
		}

		// 낚시왕이 이동하는 동안
		for (int time = 0; time < C; time++) {
			
			// 상어 잡기
			fishing(time);
			
			// 1초마다 상어의 움직임을 저장할 새로운 배열 생성
			int[][] newMap = new int[R][C];
			
			// map에 있는 상어 한 마리씩 이동
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (map[i][j] == 0)
						continue;

					int sharkNumber = map[i][j];
					move(sharks[sharkNumber], newMap);
				}
			}
			
			// 이동을 기록한 newMap을 현재 시점으로 사용하기 위해 원본 map에 복사
			copyMap(newMap, map);
		}
		
		// 출력 : 낚시왕이 잡은 상어 크기의 합
		System.out.println(totalFishing);
		
	}
	
	/**
	 * 상어를 잡는 함수
	 * @param time 낚시왕의 현재 이동 시간(위치)
	 */
	private static void fishing(int time) {
		
		// 땅에서부터 가까운 순서대로 탐색
		for (int r = 0; r < R; r++) {
			// 상어가 있다면
			if (map[r][time] > 0) {
				int sharkNumber = map[r][time];
				Shark currentShark = sharks[sharkNumber];
				
				// 잡은 상어의 크기를 누적하고
				totalFishing += currentShark.size;
				
				// 상어 배열과 맵에서 상어 제거 후 return
				currentShark = null;
				map[r][time] = 0;
				
				return;
			}
		}
	}

	/**
	 * 상어의 이동을 기록하는 함수
	 * @param shark 현재 이동하는 상어
	 * @param newMap 상어의 이동을 기록할 맵
	 */
	private static void move(Shark shark, int[][] newMap) {
		// 상어의 속력만큼 반복(초당 이동거리)
		for (int i = 0; i < shark.speed; i++) {
			
			switch (shark.direction) {
			// 상향 이동
			case UP:
				shark.r--;
				// 범위 밖으로 나갔다면 반대 방향으로 이동
				if (shark.r < 0) {
					shark.direction = DOWN;
					shark.r += 2;
				}
				break;
				
			// 하향 이동
			case DOWN:
				shark.r++;
				// 범위 밖으로 나갔다면 반대 방향으로 이동
				if (shark.r >= R) {
					shark.direction = UP;
					shark.r -= 2;
				}
				break;
				
			// 우향 이동
			case RIGHT:
				shark.c++;
				// 범위 밖으로 나갔다면 반대 방향으로 이동
				if (shark.c >= C) {
					shark.direction = LEFT;
					shark.c -= 2;
				}
				break;
				
			// 좌향 이동
			case LEFT:
				shark.c--;
				// 범위 밖으로 나갔다면 반대 방향으로 이동
				if (shark.c < 0) {
					shark.direction = RIGHT;
					shark.c += 2;
				}
				break;
			}
		}
		
		// 상어가 이동을 마친 후, 칸이 겹치는 상어가 있다면
		if (newMap[shark.r][shark.c] > 0) {
			int firstSharkNumber = newMap[shark.r][shark.c];	// 먼저 온 상어의 번호
			
			// 먼저 온 상어가 더 크다면 나중에 온 상어를 상어 배열에서 제거
			if (sharks[firstSharkNumber].size > shark.size)
				sharks[shark.number] = null;
			
			// 먼저 온 상어가 더 작다면 먼저 온 상어를 상어 배열에서 제거하고 현재 이동한 상어를 맵에 기록
			else {
				sharks[firstSharkNumber] = null;
				newMap[shark.r][shark.c] = shark.number;
			}
		}
		// 상어가 이동을 마친 후, 칸이 겹치는 상어가 없다면
		else 
			// 현재 이동한 상어를 맵에 기록
			newMap[shark.r][shark.c] = shark.number;
		
		
	}

	/**
	 * 맵을 복사하는 함수
	 * @param originMap 복사할 맵
	 * @param copyMap 복사된 맵
	 */
	static void copyMap(int[][] originMap, int[][] copyMap) {
		for (int i = 0; i < R; i++) 
			for (int j = 0; j < C; j++) 
				copyMap[i][j] = originMap[i][j];
	}

	static class Shark {
		int number, r, c, speed, direction, size;
		/**
		 * @param number 상어의 번호
		 * @param r 상어의 행 좌표
		 * @param c 상어의 열 좌표
		 * @param speed 상어의 속력
		 * @param direction 상어의 이동 방향
		 * @param size 상어의 크기
		 */
		public Shark(int number, int r, int c, int speed, int direction, int size) {
			super();
			this.number = number;
			this.r = r;
			this.c = c;
			this.speed = speed;
			this.direction = direction;
			this.size = size;
		}
	}
	
}
