	/*
	
	[2563] 색종이
	가로, 세로의 크기가 각각 100인 정사각형 모양의 흰색 도화지가 있다.
	이 도화지 위에 가로, 세로의 크기가 각각 10인 정사각형 모양의 검은색 색종이를 색종이의 변과 도화지의 변이 평행하도록 붙인다.
	이러한 방식으로 색종이를 한 장 또는 여러 장 붙인 후 색종이가 붙은 검은 영역의 넓이를 구하는 프로그램을 작성하시오.
	
	첫째 줄에 색종이가 붙은 검은 영역의 넓이를 출력한다.
	
	1. 흰색 도화지를 2차원 배열로 생성한다 (흰색 : 0)
	2. 검은색 색종이의 넓이 만큼 배열 데이터를 바꾼다 (검은색 : 1)
	3. 1로 바뀐 데이터 만큼 count한다
	
	*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		// 입력1 : 색종이의 수
		int n = Integer.parseInt(bufferedReader.readLine());
		
		int[][] paper = new int[100][100];
		int x, y;
		for (int i = 0; i < n; i++) {
			// 입력2 : 색종이를 붙인 위치
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			x = Integer.parseInt(stringTokenizer.nextToken());
			y = Integer.parseInt(stringTokenizer.nextToken());
			
			// 색종이의 넓이만큼 도화지 배열을 1로 바꾼다
			for (int r = x; r < x+10; r++)
				for (int c = y; c < y+10; c++)
					paper[r][c] = 1;
		}
		// 색종이가 붙은 영역을  count한다
		int count = 0;
		for (int i = 0; i < paper.length; i++)
			for (int j = 0; j < paper[i].length; j++)
				if (paper[i][j] == 1)
					count++;
		
		// 출력
		System.out.println(count);
		bufferedReader.close();
	}
}
