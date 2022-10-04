
	/*
	
	[2239] 스도쿠
	스도쿠는 매우 간단한 숫자 퍼즐이다. 9×9 크기의 보드가 있을 때, 각 행과 각 열, 그리고 9개의 3×3 크기의 보드에 1부터 9까지의 숫자가 중복 없이 나타나도록 보드를 채우면 된다.
	하다 만 스도쿠 퍼즐이 주어졌을 때, 마저 끝내는 프로그램을 작성하시오.
	
	9개의 줄에 9개의 숫자로 답을 출력한다. 
	답이 여러 개 있다면 그 중 사전식으로 앞서는 것을 출력한다. 
	즉, 81자리의 수가 제일 작은 경우를 출력한다.
	
	1. 81개의 숫자를 입력받아 sudoku 배열에 저장한다
	2. 배열을 차례로 돌면서 빈 칸에 유효 숫자를 채워보고 유효한 숫자가 더이상 없다면 돌아가서 다시 비운 후 다른 숫자를 채운다
	3. 스도쿠가 완성되면 모두 return true하여 재귀를 빠져나오고 완성된 스도쿠 보드를 출력한다
	
	*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	final static int BLANK = 0;
	static int[][] sudoku;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder stringBuilder = new StringBuilder();
		
		// 입력 : 9개 줄에 9개의 숫자로 된 보드를 입력받아 sudoku 배열에 저장
		sudoku = new int[9][9];
		for (int i = 0; i < 9; i++) {
			String input = bufferedReader.readLine();
			for (int j = 0; j < 9; j++)
				sudoku[i][j] = input.charAt(j) - '0';
		}

		// 스도쿠 완성하기 시작
		fillNumber(0);
		
		// 출력 : 9개의 줄에 9개의 숫자로 답을 출력
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++)
				stringBuilder.append(sudoku[i][j]);
			stringBuilder.append("\n");
		}
		System.out.println(stringBuilder.toString());
		
		bufferedReader.close();
	}

	/**
	 * 스도쿠에 숫자를 채워 완성하는 함수
	 * @param count 현재까지 완성한 칸의 숫자
	 * @return 완성여부
	 */
	static boolean fillNumber(int count) {
		
		// 81개의 칸을 모두 채웠으면 return true
		if (count == 81)
			return true;
		
		// 왼쪽 위부터 오른쪽 아래까지 차례로 0~80이므로
		int r = count / 9;	// 현재 칸의 행 좌표 계산
		int c = count % 9;	// 현재 칸의 열 좌표 계산
		
		// 빈 칸이라면
		if (sudoku[r][c] == BLANK)
			// 1~9까지의 수를 차례로 시도
			for (int i = 1; i <= 9; i++) {
				// 유효하지 않은 숫자라면 continue
				if (isValid(r, c, i) == false)
					continue;
				// 숫자 채우기
				sudoku[r][c] = i;
				// 다음 숫자를 채우러 넘어가고 스도쿠가 완성되었다면 모두 true 리턴
				if(fillNumber(count + 1) == true)
					return true;
				// 유효하지 않았다면 다음 숫자를 채워보기 위해 되돌리기
				sudoku[r][c] = BLANK;
			}	
		// 빈 칸이 아니라면
		else
			// 숫자를 채우지 않고 다음 숫자를 채우러 넘어가고 스도쿠가 완성되었다면 모두 true 리턴
			if(fillNumber(count + 1) == true)
				return true;
		
		return false;
	}

	/**
	 * 스도쿠 칸을 채울 수 있는 유효 숫자 여부를 판단하는 함수
	 * @param r 채우려는 칸의 행 좌표
	 * @param c 채우려는 칸의 열 좌표
	 * @param number 채우려는 숫자
	 * @return 유효 숫자 여부
	 */
	static boolean isValid(int r, int c, int number) {
		
		// 행 검사
		for (int j = 0; j < 9; j++) 
			if (sudoku[r][j] == number) 
				return false;
		// 열 검사
		for (int i = 0; i < 9; i++) 
			if (sudoku[i][c] == number) 
				return false;
		
		// 현재 위치에 해당하는 3*3 칸 검사
		r = r / 3 * 3;
		c = c / 3 * 3;
		for (int i = 0; i < 3; i++) 
			for (int j = 0; j < 3; j++) 
				if (sudoku[r+i][c+j] == number) 
					return false;
		
		return true;
	}
	
}
