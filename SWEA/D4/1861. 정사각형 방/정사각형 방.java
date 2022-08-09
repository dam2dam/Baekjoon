
	/*
	
	[1861] 정사각형 방
	N2개의 방이 N×N형태로 늘어서 있다.
	위에서 i번째 줄의 왼쪽에서 j번째 방에는 1이상 N2 이하의 수 Ai,j가 적혀 있으며, 이 숫자는 모든 방에 대해 서로 다르다.
	당신이 어떤 방에 있다면, 상하좌우에 있는 다른 방으로 이동할 수 있다.
	물론 이동하려는 방이 존재해야 하고, 이동하려는 방에 적힌 숫자가 현재 방에 적힌 숫자보다 정확히 1 더 커야 한다.
	처음 어떤 수가 적힌 방에서 있어야 가장 많은 개수의 방을 이동할 수 있는지 구하는 프로그램을 작성하라.
	
	처음에 출발해야 하는 방 번호와 최대 몇 개의 방을 이동할 수 있는지를 공백으로 구분하여 출력한다.
	이동할 수 있는 방의 개수가 최대인 방이 여럿이라면 그 중에서 적힌 수가 가장 작은 것을 출력한다.
	
	1. 방을 2차원 배열에 입력받는다
	2. 모든 방을 방문하며 각 방마다 이동 가능한 방의 수를 count한다
	3. 방 번호를 index로 하여 최대 이동 수를 배열에 저장한다
	4. 배열에서 최댓값과 index(방 번호)를 찾아 출력한다
	
	*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int n;
	static int[][] room;
	static int[] roomCount;
	static int count;
	static int max;
	static int maxIndex;
	final static int[] deltaR = {-1, 1, 0, 0};
	final static int[] deltaC = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		// 입력1 : 테스트 케이스의 수
		int T = Integer.parseInt(bufferedReader.readLine());
		for (int tc = 1; tc <= T; tc++) {
			// 입력2 : n*n:전체 방의 개수 (1 ≤ n ≤ 1000)
			n = Integer.parseInt(bufferedReader.readLine());
			
			// 방 번호를 입력받아 String 배열에 넣는다
			String [][] input = new String[n][n];
			for (int i = 0; i < n; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				for (int j = 0; j < n; j++)
					input[i][j] = stringTokenizer.nextToken();
			}
			
			// 입력받은 String 배열을 int 배열로 바꿔준다
			room = new int[n][n];
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					room[i][j] = Integer.parseInt(input[i][j]);
			
			
			roomCount = new int[n*n+1];
			for (int i = 0; i < room.length; i++)
				for (int j = 0; j < room[i].length; j++) {
					count = 1;	// 처음 출발하는 방부터 count하므로 1로 초기화
					visitRoom(i, j);	// 모든 방 방문
					roomCount[room[i][j]] = count;	// 방문한 방의 번호를 index로 사용하여 그 방에서 이동한 방의 개수를 넣는다
				}

			
			max = 0;
			maxIndex = -1;
			// 방 번호를 index로 사용하여 저장하였기 때문에, 최대값이 여럿일 때 적힌 수(방 번호)가 가장 작은 것을 출력하기 위해 뒤에서부터 max값 갱신한다
			for (int i = roomCount.length-1; i > 0; i--) {
				max = Math.max(max, roomCount[i]);
				if (max == roomCount[i])
					maxIndex = i;	// 최댓값을 가진 방의 번호 기억
			}
			// 출력 : (테스트 케이스 번호, 출발하는 방, 최대 이동 수)
			System.out.printf("#%d %d %d\n", tc, maxIndex, max);
		}
		bufferedReader.close();
	}
	
	static void visitRoom(int x, int y) {
		for (int d = 0; d < 4; d++) {
			int nr = x + deltaR[d];
			int nc = y + deltaC[d];
			if (0<=nr && nr<n && 0<=nc && nc<n && room[nr][nc] == room[x][y]+1) {
				count++;	// 이동한 방 count
				visitRoom(nr, nc);	//이동
			}
		}
	}
}
