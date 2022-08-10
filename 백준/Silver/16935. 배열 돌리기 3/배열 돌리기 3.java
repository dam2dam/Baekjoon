	/*
	
	[16935] 배열 돌리기 3
	크기가 N×M인 배열이 있을 때, 배열에 연산을 R번 적용하려고 한다. 연산은 총 6가지가 있다.
	
	입력으로 주어진 배열에 R개의 연산을 순서대로 수행한 결과를 출력한다.
	
	1. 1번 연산 : 배열을 상하 반전시키는 연산
		=> 맨 윗 줄부터 중심으로 가면서 대칭되는 아랫 줄과 N/2번 swap 
	2. 2번 연산 : 배열을 좌우 반전시키는 연산
		=> 맨 왼쪽 줄부터 중심으로 가면서 대칭되는 오른쪽 줄과 M/2번 swap 
	3. 3번 연산 : 오른쪽으로 90도 회전시키는 연산
		=> ex) 원래 배열(i, j)의 ( 0, 0~M-1 )칸을 바꿀 배열의 ( 0~N-1, M-1 )칸에 넣어 돌리기 (i=0줄 -> j=M-1줄)
	4. 4번 연산 : 왼쪽으로 90도 회전시키는 연산
		=> ex) 원래 배열(i, j)의 ( 0, 0~M )칸을 바꿀 배열의 ( N~0, 0 )칸에 넣어 돌리기
	5. 5번 연산 : 1번 그룹의 부분 배열을 2번 그룹 위치로, 2번을 3번으로, 3번을 4번으로, 4번을 1번으로 이동시키는 연산
	   6번 연산 : 1번 그룹의 부분 배열을 4번 그룹 위치로, 4번을 3번으로, 3번을 2번으로, 2번을 1번으로 이동시키는 연산
			그룹 예시 )12
				   43
		=> 0~N/2, N/2~N-1, 0~M/2, M/2~M-1을 범위로 하는 for문을 돌려서 그룹별로 바꿔주기
	6. 각 연산을 함수로 구현하고 switch문으로 각 연산에 해당하는 함수를 호출하기
	
	*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] array;
	static int N;
	static int M;
	static int R;

	public static void main(String[] args) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		
		// 입력1 : (N,M):배열의 크기, R:수행해야 하는 연산의 수
		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());
		R = Integer.parseInt(stringTokenizer.nextToken());
		
		// 입력2 : 배열의 원소 저장
		array = new int[N][M];
		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < M; j++)
				array[i][j] = Integer.parseInt(stringTokenizer.nextToken());
		}
		
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for (int i = 0; i < R; i++) {
			// 입력3 : 수행해야 하는 연산
			int num = Integer.parseInt(stringTokenizer.nextToken());
			// 수행해야 하는 연산에 맞는 함수 호출
			switch (num) {
				case 1:
					upDownReverse();
					break;
				case 2:
					leftRightReverse();
					break;
				case 3:
					array = rightTurn(array);
					break;
				case 4:
					array = leftTurn(array);
					break;
				case 5:
					array = groupRightTurn(array);
					break;
				case 6:
					array = groupLeftTurn(array);
					break;
			}
		}
		// 출력
		for (int[] nums : array) {
			for (int num : nums)
				System.out.print(num + " ");
			System.out.println();
		}
		bufferedReader.close();
	}
	
	// 1번 연산 : 배열을 상하 반전시키는 연산
	static void upDownReverse() {
		int[] temp = new int[M];
		
		for (int i = 0; i < N/2; i++) {	// 중간까지 상하 대칭되는 자리의 행 swap
			temp = array[i];
			array[i] = array[N-1-i];
			array[N-1-i] = temp;
		}
	}
	
	// 2번 연산 : 배열을 좌우 반전시키는 연산
	static void leftRightReverse() {
		int temp = 0;
		
		for (int j = 0; j < M/2; j++) { // 중간까지 좌우 대칭되는 자리의 열 swap
			for (int i = 0; i <	N; i++) {
				temp = array[i][j];
				array[i][j] = array[i][M-1-j];
				array[i][M-1-j] = temp;
			}
		}
	}
	
	// 3번 연산 : 오른쪽으로 90도 회전시키는 연산
	static int[][] rightTurn(int[][] array) {
		int[][] changedArray = new int[M][N];
		
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				changedArray[j][N-1-i] = array[i][j]; 
		
		// 배열 회전 후, 행과 열의 크기를 바꾸어 줌
		int temp = N;
		N = M;
		M = temp;
		return changedArray;
	}
	
	// 4번 연산 : 왼쪽으로 90도 회전시키는 연산
	static int[][] leftTurn(int[][] array) {
		int[][] changedArray = new int[M][N];
		
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				changedArray[M-1-j][i] = array[i][j]; 

		// 배열 회전 후, 행과 열의 크기를 바꾸어 줌
		int temp = N;
		N = M;
		M = temp;
		return changedArray;
	}
	
	// 5번 연산 : 1번 그룹의 부분 배열을 2번 그룹 위치로, 2번을 3번으로, 3번을 4번으로, 4번을 1번으로 이동시키는 연산
	static int[][] groupRightTurn(int[][] array) {
		int[][] changedArray = new int[N][M];
		// 1 -> 2
		for (int i = 0; i < N/2; i++)
			for (int j = 0; j < M/2; j++)
				changedArray[i][j + M/2] = array[i][j];
		// 2 -> 3
		for (int j = M/2; j < M; j++)
			for (int i = 0; i < N/2; i++)
				changedArray[i + N/2][j] = array[i][j];
		// 3 -> 4
		for (int i = N/2; i < N; i++)
			for (int j = M/2; j < M; j++)
				changedArray[i][j - M/2] = array[i][j];
		// 4 -> 1
		for (int j = 0; j < M/2; j++)
			for (int i = N/2; i < N; i++)
				changedArray[i - N/2][j] = array[i][j];

		return changedArray;
	}
	
	// 6번 연산 : 1번 그룹의 부분 배열을 4번 그룹 위치로, 4번을 3번으로, 3번을 2번으로, 2번을 1번으로 이동시키는 연산
	static int[][] groupLeftTurn(int[][] array) {
		int[][] changedArray = new int[N][M];
		// 1 -> 4
		for (int j = 0; j < M/2 ; j++)
			for (int i = 0; i < N/2; i++)
				changedArray[i + N/2][j] = array[i][j];
		// 4 -> 3
		for (int i = N/2; i < N; i++)
			for (int j = 0; j < M/2; j++)
				changedArray[i][j + M/2] = array[i][j];
		// 3 -> 2
		for (int j = M/2; j < M ; j++)
			for (int i = N/2; i < N; i++)
				changedArray[i - N/2][j] = array[i][j];
		// 2 -> 1
		for (int i = 0; i < N/2; i++)
			for (int j = M/2; j < M; j++)
				changedArray[i][j - M/2] = array[i][j];

		return changedArray;
	}
}
