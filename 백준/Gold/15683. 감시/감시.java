	/*
	
	[15683] 감시
	스타트링크의 사무실은 1×1크기의 정사각형으로 나누어져 있는 N×M 크기의 직사각형으로 나타낼 수 있다.
	사무실에는 총 K개의 CCTV가 설치되어져 있는데, CCTV는 5가지 종류가 있다. 
		1번 CCTV는 한 쪽 방향만 감시할 수 있다. 
		2번과 3번은 두 방향을 감시할 수 있는데, 2번은 감시하는 방향이 서로 반대방향이어야 하고, 3번은 직각 방향이어야 한다. 
		4번은 세 방향, 5번은 네 방향을 감시할 수 있다.
	CCTV는 감시할 수 있는 방향에 있는 칸 전체를 감시할 수 있다. 
	사무실에는 벽이 있는데, CCTV는 벽을 통과할 수 없다. CCTV가 감시할 수 없는 영역은 사각지대라고 한다.
	CCTV는 회전시킬 수 있는데, 회전은 항상 90도 방향으로 해야 하며, 감시하려고 하는 방향이 가로 또는 세로 방향이어야 한다.
	사무실의 크기와 상태, 그리고 CCTV의 정보가 주어졌을 때, CCTV의 방향을 적절히 정해서, 사각 지대의 최소 크기를 구하는 프로그램을 작성하시오.
	
	첫째 줄에 사각 지대의 최소 크기를 출력한다.
	
	1. cctv개수 크기로 네 방향에 대한 중복 순열을 만든다 : directionPermutation()
	2. 완성된 순열이 지시하는 방향에 따라 각 cctv가 감시하는 방법대로 감시한다 : howToObserve()
		2-1. ex) 지시 방향 0 기준 상 : 1번-상, 2번-상하, 3번-상우, 4번-상우하, 5번-상우하좌 
				  지시 방향 1 기준 우 : 1번-우, 2번-우좌, 3번-우하, 4번-우하좌, 5번-우하좌상 
				  지시 방향 2 기준 하 : 1번-하, 2번-상하, 3번-하좌, 4번-하좌상, 5번-하좌상우 
				  지시 방향 3 기준 좌 : 1번-좌, 2번-우좌, 3번-좌상, 4번-좌상우, 5번-좌상우하
	3. 완성된 순열마다 map을 copy하여 감시 영역을 표시한다 : observation()
	4. 사각지대를 count하여 최솟값을 갱신한다
	5. 모든 경우에 대해 감시가 끝나면 사각지대의 최소 크기를 출력한다
	
	*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, numberofCCTVs, numberofBlindSpots, min;
	static int[][] map, copyMap;
	static List<CCTV> cctvs;
	
	static int[] currentDirections;
	
	// 방향 배열 : 상 우 하 좌 (시계방향)
	final static int[] dy = {-1, 0, 1, 0};
	final static int[] dx = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringtokenizer = new StringTokenizer(bufferedReader.readLine());
		
		// 입력1 : N:사무실의 세로 크기, M:사무실의 가로 크기
		N = Integer.parseInt(stringtokenizer.nextToken());
		M = Integer.parseInt(stringtokenizer.nextToken());
		
		// 입력2 : 사무실 각 칸의 정보를 입력받아 배열에 저장
		map = new int[N][M];
		cctvs = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			stringtokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(stringtokenizer.nextToken());
				
				// cctv의 좌표와 종류를 리스트에 저장
				if (1 <= map[i][j] && map[i][j] <= 5)
					cctvs.add(new CCTV(i, j, map[i][j]));
			}
		}
		
		numberofCCTVs = cctvs.size();	// cctv의 개수
		currentDirections = new int[numberofCCTVs];	// cctv 방향 순열을 저장할 배열
		min = Integer.MAX_VALUE;
		
		// 방향 순열 생성
		directionPermutation(0);
		
		// 출력 : 사각지대의 최소 크기
		System.out.println(min);
		
		bufferedReader.close();
	}
	
	static void directionPermutation(int count) {
		
		// 순열이 완성되면
		if(count == numberofCCTVs) {
			// 각 상황에 따라 cctv가 감시하는 칸을 표시하기 위해 map copy
			copyMap = new int[N][M];
			for (int i = 0; i < N; i++)
				for (int j = 0; j < M; j++)
					copyMap[i][j] = map[i][j];
			
			numberofBlindSpots = 0;
			
			// 완성된 순열에 따른 방향을 해당 cctv로 감시
			for (int i = 0; i < numberofCCTVs; i++) {
				int directionNum = currentDirections[i];
				CCTV cctv = cctvs.get(i);
				howToObserve(cctv, directionNum);	
			}
			// 사각지대를 count하고 최솟값 갱신
			countBlindSpot();
			min = Math.min(min, numberofBlindSpots);
			
			return;
		}
		
		// 각 cctv의 방향에 따라 순열 생성
		for (int i = 0; i < 4; i++) {	// 4방
			currentDirections[count] = i;
			directionPermutation(count+1);
		}
	}
	
	/**
	 * 완성된 방향 순열에 따라 각 cctv가 어떻게 동작할지 정해주는 함수
	 * @param cctv 해당 cctv
	 * @param directionNum 방향 순열의 지시 방향
	 */
	static void howToObserve(CCTV cctv, int directionNum) {
		switch (cctv.num) {
		
			// 1번 cctv 한 방향 감시
			case 1:
				observation(cctv, directionNum);
				break;
				
			// 2번 cctv 반대인 두 방향 감시
			case 2:
				// 상하
				if (directionNum % 2 == 0) {
					observation(cctv, 0);
					observation(cctv, 2);
				}
				// 좌우
				else {
					observation(cctv, 1);
					observation(cctv, 3);
				}
				break;
			
			// 3번 cctv 직각인 두 방향 감시
			case 3:
				observation(cctv, directionNum);
				observation(cctv, (directionNum+1) % 4);	// 방향 배열 순환	
				break;
				
			// 4번 cctv 세 방향 감시
			case 4:
				observation(cctv, directionNum);
				observation(cctv, (directionNum+1) % 4);	// 방향 배열 순환
				observation(cctv, (directionNum+2) % 4);	// 방향 배열 순환
				break;
			
			// 5번 cctv 네 방향 감시
			case 5:
				observation(cctv, 0);
				observation(cctv, 1);
				observation(cctv, 2);
				observation(cctv, 3);
				break;

		}
	}
	
	/**
	 * cctv가 감시할 수 있는 영역을 표시하는 함수
	 * @param cctv 해당 cctv
	 * @param direction 방향 배열의 index
	 */
	static void observation(CCTV cctv, int direction) {
		int ny = cctv.y + dy[direction];
		int nx = cctv.x + dx[direction];
		
		// 감시하려고 하는 영역이 범위를 벗어나지 않고 벽이 아닐 경우
		while (0 <= ny && ny < N && 0 <= nx && nx < M && map[ny][nx] != 6) {
			// 감시하는 영역을 7로 표시하고 다음 영역 감시
			copyMap[ny][nx] = 7;
			ny += dy[direction];
			nx += dx[direction];
		}
	}
	
	/**
	 * 사각지대 크기를 구하는 함수
	 */
	static void countBlindSpot() {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if(copyMap[i][j] == 0)
					numberofBlindSpots++;
	}
	
	static class CCTV {
		int y, x, num;
		
		public CCTV(int y, int x, int num) {
			super();
			this.y = y;
			this.x = x;
			this.num = num;
		}
	}
	
}
