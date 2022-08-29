	/*
	
	[2667] 단지 번호 붙이기
	정사각형 모양의 지도가 있다. 1은 집이 있는 곳을, 0은 집이 없는 곳을 나타낸다.
	철수는 이 지도를 가지고 연결된 집의 모임인 단지를 정의하고, 단지에 번호를 붙이려 한다.
	여기서 연결되었다는 것은 어떤 집이 좌우, 혹은 아래위로 다른 집이 있는 경우를 말한다. 
	지도를 입력하여 단지수를 출력하고, 각 단지에 속하는 집의 수를 오름차순으로 정렬하여 출력하는 프로그램을 작성하시오.
	
	첫 번째 줄에는 총 단지수를 출력하시오. 그리고 각 단지내 집의 수를 오름차순으로 정렬하여 한 줄에 하나씩 출력하시오.
	
	1. dfs로 모든 집을 탐색한다
	2. 각 단지를 탐색할 때마다 단지 수를 증가시키며 붙여준다
	3. 각 집을 탐색할 때마다 집의 수를 count한다
	
	*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	static int N, apartmentNumber, houseCount;
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<Integer> numberOfHouses;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
		// 입력1 : 지도의 크기  (N * N)
		N = Integer.parseInt(bufferedReader.readLine());
		
		// 입력2 : 지도를 입력받아 배열에 저장
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String input = bufferedReader.readLine();
			for (int j = 0; j < N; j++)
				map[i][j] = input.charAt(j) - '0';
		}
		
		visited = new boolean[N][N];
		apartmentNumber = 1;	// 단지의 번호
		numberOfHouses = new ArrayList<>();	// 각 단지에 속하는 집의 수 저장할 리스트
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (map[i][j] == 1 && visited[i][j] == false) {
					houseCount = 0;	// 각 단지의 집의 수
					dfs(i, j);
					numberOfHouses.add(houseCount);
					apartmentNumber++;	// 아파트 단지 번호 증가
				}
		
		// 출력을 위해 오름차순 정렬
		Collections.sort(numberOfHouses);
		
		// 출력
		System.out.println(apartmentNumber-1);
		for (int numberOfHouse : numberOfHouses)
			System.out.println(numberOfHouse);
		
		bufferedReader.close();
	}

	private static void dfs(int y, int x) {
		// 아파트 단지의 번호를 더해주고 방문처리
		map[y][x] += apartmentNumber;
		visited[y][x] = true;
		houseCount++;	// 집의 수 count
		
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			
			if (ny < 0 || ny >= N || nx < 0 || nx >= N || visited[ny][nx] == true)
				continue;
			
			if (map[ny][nx] == 1)	// 집이 있다면 탐색
				dfs(ny, nx);
		}
	}

}
