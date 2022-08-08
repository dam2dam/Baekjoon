	/*
	
	[2583] 영역 구하기
	눈금의 간격이 1인 M×N(M,N≤100)크기의 모눈종이가 있다.
	이 모눈종이 위에 눈금에 맞추어 K개의 직사각형을 그릴 때, 이들 K개의 직사각형의 내부를 제외한 나머지 부분이 몇 개의 분리된 영역으로 나누어진다.
	M, N과 K 그리고 K개의 직사각형의 좌표가 주어질 때, 
	K개의 직사각형 내부를 제외한 나머지 부분이 몇 개의 분리된 영역으로 나누어지는지, 
	그리고 분리된 각 영역의 넓이가 얼마인지를 구하여 이를 출력하는 프로그램을 작성하시오.
	
	첫째 줄에 분리되어 나누어지는 영역의 개수를 출력한다. 둘째 줄에는 각 영역의 넓이를 오름차순으로 정렬하여 빈칸을 사이에 두고 출력한다.
	
	1. 모눈종이 2차원 배열에 직사각형 위치를 저장한다
	2. 직사각형이 아닌 영역을 찾아 방문하고 넓이를 count한다 (count)
	3. 방문이 끝나면 분리된 영역의 개수를 count+1한다 (areaCount)
	4. 나누어진 영역의 개수와 각 영역의 넓이를 출력한다
	
	*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
	static int[][] paper;
	static boolean[][] check;
	static int m;
	static int n;
	static int count = 0;	//분리된 영역의 넓이
	static int[] deltaR = {-1, 1, 0, 0};
	static int[] deltaC = {0, 0, -1, 1};

	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		// 입력 : m:가로,  n:세로, k:직사각형 개수
		m = scanner.nextInt();
		n = scanner.nextInt();
		int k = scanner.nextInt();
		
		// 그림에서는 (0,0)꼭짓점이 왼쪽 아래였지만 배열로 보기 편하게 왼쪽 위로 돌려서 처리
		paper = new int[n][m];	//모눈종이 배열
		check = new boolean[n][m];	//방문체크 배열
		
		// 왼쪽 아래 꼭칫점 :(x1, y1), 오른쪽 위 꼭짓점 :(x2, y2)
		for (int i = 0; i < k; i++) {
			int x1 = scanner.nextInt();
			int y1 = scanner.nextInt();
			int x2 = scanner.nextInt();
			int y2 = scanner.nextInt();
			
			// 모눈종이 배열에 직사각형 위치 할당
			for (int r = x1; r < x2; r++)
				for (int c = y1; c < y2; c++)
					paper[r][c] = 1;
		}
		
		int areaCount = 0;	//분리된 영역의 개수
		List<Integer> nums = new ArrayList<Integer>();	//분리된 각 영역의 넓이를 담을 리스트
		
		// 분리된 영역을 하나 방문하고 돌아올 때마다 count
		for (int i = 0; i < paper.length; i++) {
			for (int j = 0; j < paper[i].length; j++) {
				if (!check[i][j] && paper[i][j] == 0) {
					dfs(i, j);	//분리된 영역 찾기
					areaCount++;	//분리된 영역 개수 count
					nums.add(count);	//분리된 영역의 넓이 count를 리스트에 넣기
					count = 0;	//분리된 영역의 넓이 초기화
				}
			}
		}
		// 출력
		// 1) 첫째 줄에 분리되어 나누어지는 영역의 개수를 출력한다.
		System.out.println(areaCount);
		// 2) 둘째 줄에는 각 영역의 넓이를 오름차순으로 정렬하여 빈칸을 사이에 두고 출력한다.
		Collections.sort(nums);
		for (Integer i : nums) 
			System.out.print(i + " ");
		
		scanner.close();
	}
	
	// 분리된 영역의 각 칸을 방문할 때마다 넓이 count
	static void dfs(int r, int c) {
		check[r][c] = true;	//방문 체크
		count++;	//분리된 영역 넓이 count
		for (int d = 0; d < 4; d++) {
			int nr = r + deltaR[d];
			int nc = c + deltaC[d];
			if (0<=nr && nr<n && 0<=nc && nc<m)
				if (!check[nr][nc] && paper[nr][nc] == 0)
					dfs(nr, nc);	
		}
	}
}
