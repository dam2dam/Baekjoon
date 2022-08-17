	/*
	
	[1992] 쿼드트리
	흑백 영상을 압축하여 표현하는 데이터 구조로 쿼드 트리(Quad Tree)라는 방법이 있다.
	주어진 영상이 모두 0으로만 되어 있으면 압축 결과는 "0"이 되고, 모두 1로만 되어 있으면 압축 결과는 "1"이 된다. 
	만약 0과 1이 섞여 있으면 전체를 한 번에 나타내지를 못하고, 왼쪽 위, 오른쪽 위, 왼쪽 아래, 오른쪽 아래, 이렇게 4개의 영상으로 나누어 압축하게 되며, 
	이 4개의 영역을 압축한 결과를 차례대로 괄호 안에 묶어서 표현한다.
	N ×N 크기의 영상이 주어질 때, 이 영상을 압축한 결과를 출력하는 프로그램을 작성하시오.
	
	영상을 압축한 결과를 출력한다.
	
	1. 구역을 첫번째 위치 값과 비교하여 모두 같은지(압축 가능한지) 확인한다 : canCompress()
	2. 길이가 1이거나 압축이 가능하면 그 자리의 값을 바로 출력한다
	3. 압축이 불가능하다면 구역을 4등분하여 각 구역을 다시 압축이 가능한지 확인한다 : compress()
	
	*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static char[][] array;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
		// 입력1 : 영상의 크기 (1 <= n <= 64) 
		int n = Integer.parseInt(bufferedReader.readLine());
		
		// 입력2 : 문자열 입력받아 2차원 배열에 저장
		array = new char[n][n];
		String input;
		for(int i = 0; i < n; i++) {
			input = bufferedReader.readLine();
			for(int j = 0; j < n; j++)
				array[i][j] = input.charAt(j);
		}
		
		// 압축하기
		compress(0, 0, n);
        bufferedReader.close();
	}
	
	static void compress(int y, int x, int size) {
		
		// 길이가 1이거나 압축이 가능하면 출력하고 반환한다
		if (size == 1 || canCompress(y, x, size) == true) {
			System.out.print(array[y][x]);
			return;
		}
		
		// 압축이 되지 않는다면 영상을 4등분하여 각 구역을 다시 압축한다
		int splitSize = size / 2;
		System.out.print("(");	// 4구역을 괄호로 묶어줌
		compress(y, x, splitSize);
		compress(y, x+splitSize, splitSize);
		compress(y+splitSize, x, splitSize);
		compress(y+splitSize, x+splitSize, splitSize);
		System.out.print(")");
	}

	static boolean canCompress(int y, int x, int size) {
		
		int first = array[y][x];
		for (int i = y; i < y+size; i++)
			for (int j = x; j < x+size; j++)
				if (array[i][j] != first)
					return false;
		return true;
	}

}
