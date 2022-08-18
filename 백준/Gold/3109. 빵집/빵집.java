	/*
	
	[3109] 빵집
	빵집이 있는 곳은 R*C 격자로 표현할 수 있다.
	첫째 열은 근처 빵집의 가스관이고, 마지막 열은 원웅이의 빵집이다.
	원웅이는 가스관과 빵집을 연결하는 파이프를 설치하려고 한다.건물이 있는 경우에는 파이프를 놓을 수 없다.
	가스관과 빵집을 연결하는 모든 파이프라인은 첫째 열에서 시작해야 하고, 마지막 열에서 끝나야 한다.
	각 칸은 오른쪽, 오른쪽 위 대각선, 오른쪽 아래 대각선으로 연결할 수 있고, 각 칸의 중심끼리 연결하는 것이다.
	가스관과 빵집을 연결하는 파이프라인을 여러 개 설치할 것이다. 각 칸을 지나는 파이프는 하나이어야 한다.
	원웅이 빵집의 모습이 주어졌을 때, 원웅이가 설치할 수 있는 가스관과 빵집을 연결하는 파이프라인의 최대 개수를 구하는 프로그램을 작성하시오.
	
	첫째 줄에 원웅이가 놓을 수 있는 파이프라인의 최대 개수를 출력한다.
	
	1. 현재 위치에서 오른쪽 위, 오른쪽, 오른쪽 아래 중 맵을 벗어나지 않고 빈 칸이면 진행한다	: pipeline()
		1-1. 진행한 칸은 더이상 가지 못한다는 의미에서 빌딩 표시로 막아둔다
	2. 원웅이 빵집의 가스관에 도착하면 라인을 하나 센다
	3. 라인을 찾았다는 의미로 true를 반환하여 더 이상 길을 찾지 않고 돌아가서 다른 길을 찾는다
	4. 첫째 열의 모든 행에서 라인을 찾았보았다면 파이프라인의 최대 개수를 출력한다
	
	*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int r, c;
	static char[][] map;
	static int pipelineCount;
	
	static int dy[] = {-1, 0, 1};
	
	final static char BLANK = '.';
	final static char BUILDING = 'x';

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		
		// 입력1 : r:행, c:열
		r = Integer.parseInt(stringTokenizer.nextToken());
		c = Integer.parseInt(stringTokenizer.nextToken());
		
		// 입력2 : 빵집 근처의 모습을 입력받아 2차원 배열에 저장
		map = new char[r][c];
		String input;
		for (int i = 0; i < r; i++) {
			input = bufferedReader.readLine();
			for (int j = 0; j < c; j++)
				map[i][j] = input.charAt(j);
		}
		
		// 첫째 열의 모든 행에서 파이프라인 찾아보기
		for (int i = 0; i <	r; i++)
			pipeline(i, 0);
		
		// 출력 : 파이프라인 최대 개수
		System.out.println(pipelineCount);
		
		bufferedReader.close();
	}
	
	static boolean pipeline(int y, int x) {
		
		// 원웅이 빵집의 가스관에 도착하면 파이프라인 개수를 count하고 찾았다는 true반환
		if (x == c-1) {
			pipelineCount++;
			return true;
		}
		
		for (int d = 0; d < 3; d++) {
			int ny = y + dy[d];	// 위, 옆, 아래
			int nx = x + 1;		// 오른쪽
			if (ny < 0 || ny >= r)	// 맵을 벗어나는 곳은 가지 않음
				continue;
			
			if (map[ny][nx] == BLANK) {
				map[ny][nx] = BUILDING;	// 방문 표시 (빌딩 표시로 막아 갈 수 없음)
				if(pipeline(ny, nx) == true)	// 라인을 찾았으면 길을 더 찾지 않고 돌아감
					return true;
			}
		}
		return false;
	}
}
