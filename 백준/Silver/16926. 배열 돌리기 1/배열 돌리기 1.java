	/*
	
	[16926] 배열 돌리기 1
	크기가 N×M인 배열이 있을 때, 배열을 돌려보려고 한다.
	배열은 반시계 방향으로 돌려야 한다.
	배열과 정수 R이 주어졌을 때, 배열을 R번 회전시킨 결과를 구해보자.
	
	입력으로 주어진 배열을 R번 회전시킨 결과를 출력한다.

	1. 2차원 배열을 입력받는다
	2. 회전하는 함수를 구현한다 : rotateArray() 
		2-1. 좌우변과 상하변으로 나누어 for문을 돌며 각각 이동한다
		2-2. depth를 적용하여 겉에서부터 한 줄씩 회전하며 안쪽으로 들어간다
	3. 회전시키려는 횟수 R만큼 회전하여 return된 배열을 출력한다
	
	*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		
		// 입력1 : (N,M):배열의 크기, R:수행해야 하는 회전의 수
		int N = Integer.parseInt(stringTokenizer.nextToken());
		int M = Integer.parseInt(stringTokenizer.nextToken());
		int R = Integer.parseInt(stringTokenizer.nextToken());
		
		// 입력2 : 배열의 원소 입력받기
		int[][] array = new int[N][M];
		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < M; j++)
				array[i][j] = Integer.parseInt(stringTokenizer.nextToken());
		}
		
		// R번 회전
		for (int i = 0; i < R; i++)
			array = rotateArray(array, N, M);			
		
		// 출력
		for (int[] nums : array) {
			for (int num : nums) {
				System.out.print(num + " ");
			}
			System.out.println();
		}
		bufferedReader.close();
	}
	
	static int[][] rotateArray(int[][] array, int rowLength, int colLength) {
		
		// 회전한 값을 담아 return할 임시 배열
		int[][] tempArray = new int[rowLength][colLength];
		
		for (int depth = 0; depth < Math.min(rowLength, colLength)/2; depth++) {	// 겉에서부터 한줄씩 회전하며 (더 작은 변의)중심까지 들어간다
			
			// 모든 row,col에 depth를 적용한다 (좌상 우상 좌하 우하 각각 적용)
			for (int next = 1; next < rowLength - 2*depth; next++) {	// 좌우변 이동범위 : 상하 한칸씩 안쪽으로 들어온 만큼 적게 움직인다
			
				// 1. 왼쪽 세로줄 아래로 ( v ) : 윗 칸( next-1 )의 데이터를 아래 칸( next )에 저장한다
				tempArray[next +depth][0 +depth] = array[(next-1) +depth][0 +depth];	// depth: +1,+1 (우하향)
				// 2. 오른쪽 세로줄 위로  ( ^ )	: 아래 칸( (rowLength-1) - (next-1) )의 데이터를 윗 칸( (rowLength-1) - next )에 저장한다
				tempArray[(rowLength-1) - next -depth][(colLength-1) -depth] = array[(rowLength-1) - (next-1) -depth][(colLength-1) -depth];	// depth: -1,-1 (좌상향)
			}
			
			for (int next = 1; next < colLength - 2*depth; next++) {	// 상하변 이동범위 : 좌우 한칸씩 안쪽으로 들어온 만큼 적게 움직인다
				
				// 3. 위쪽 가로줄 왼쪽으로 ( <- ) : 오른쪽 칸( (colLength-1) - (next-1) )의 데이터를 왼쪽 칸( (colLength-1) - next )에 저장한다
				tempArray[0 +depth][(colLength-1) - next -depth] = array[0 +depth][(colLength-1) - (next-1) -depth];	// depth: +1,-1 (좌하향)
				// 4. 아래쪽 가로줄 오른쪽으로 ( -> ) : 왼쪽 칸( next-1 )의 데이터를 오른쪽 칸( next )에 저장한다
				tempArray[(rowLength-1) -depth][next +depth] = array[(rowLength-1) -depth][(next-1) +depth];	// depth: -1,+1	(우상향)
			}
		}
		return tempArray;
	}
}
