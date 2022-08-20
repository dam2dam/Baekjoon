	/*
	
	[1074] Z
	한수는 크기가 2N × 2N인 2차원 배열을 Z모양으로 탐색하려고 한다. 
	N > 1인 경우, 배열을 크기가 2N-1 × 2N-1로 4등분 한 후에 재귀적으로 순서대로 방문한다.
	N이 주어졌을 때, r행 c열을 몇 번째로 방문하는지 출력하는 프로그램을 작성하시오.
	
	r행 c열을 몇 번째로 방문했는지 출력한다.
	
	1. 재귀함수로 Z의 크기를 줄이면서 4등분하여 각 구역의 좌표를 준 뒤 탐색한다 : searchZ()
	2. Z의 크기가 가장 작아졌을 때부터 방문 순서를 기록한다
	3. 모두 기록한 뒤 찾고자하는 좌표의 방문 순서를 출력한다
	
	*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, r, c, index;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringtokenizer = new StringTokenizer(bufferedReader.readLine());
		
		// 입력 : 배열의 크기:(2^N * 2^N), 방문 순서를 찾고자하는 좌표:(r,c)
		N = Integer.parseInt(stringtokenizer.nextToken());
		r = Integer.parseInt(stringtokenizer.nextToken());
		c = Integer.parseInt(stringtokenizer.nextToken());
		
		// 배열 한 변의 길이
		int size = (int) Math.pow(2, N);
		
		// 방문 순서 탐색
		searchZ(0, 0, size);
		
	}
	
	static void searchZ(int y, int x, int size) {
		
		// 찾고자하는 좌표를 찾으면 방문 순서 출력
		if(y == r && x == c) {
			System.out.println(index);
			return;
		}

		// Z의 크기를 줄이면서 4등분하여 탐색한다
		int halfSize = size/2;
		
		// 왼쪽 위 구역
		if(r < y+halfSize && c < x+halfSize) {
			searchZ(y, x, halfSize);
		}
		
		// 오른쪽 위 구역
		if(r < y+halfSize && x+halfSize <= c) {
			index += halfSize*halfSize*1;	// 앞 구역 이후부터 인덱스 시작
			searchZ(y, x+halfSize, halfSize);
		}
		
		// 왼쪽 아래 구역
		if(y+halfSize <= r && c < x+halfSize) {
			index += halfSize*halfSize*2;	// 앞 구역 이후부터 인덱스 시작
			searchZ(y+halfSize, x, halfSize);
		}
		
		// 오른쪽 아래 구역
		if(y+halfSize <= r && x+halfSize <= c) {
			index += halfSize*halfSize*3;	// 앞 구역 이후부터 인덱스 시작
			searchZ(y+halfSize, x+halfSize, halfSize);
		}
	}
	
}
