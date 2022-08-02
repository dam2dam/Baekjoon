import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("input.txt"));	
		Scanner scanner = new Scanner(System.in);
		int T = scanner.nextInt();
		
		//테스트 케이스 입력
		for (int tc = 1; tc <= T; tc++) {
			int n = scanner.nextInt();
			
			//숫자 배열 생성
			int[][] nums = new int[n][n];
			
			//이동을 위한 델타 배열
			int[] deltaR = {0, 1, 0, -1};
			int[] deltaC = {1, 0, -1, 0};
			int d = 0;
			
			// 현재 위치
			int x = 0;
			int y = 0;
			
			for (int i = 1; i <= n*n; i++) {
				nums[x][y] = i; //숫자를 입력하고
				x += deltaR[d]; //한칸 전진
				y += deltaC[d];
				
				//범위를 넘었거나 숫자가 있으면 직전 자리로 돌아가서 방향 바꿈
				if(x < 0 || y < 0 || x >= n || y >= n || nums[x][y] != 0) {
					x -= deltaR[d];
					y -= deltaC[d];
					
					//델타 배열 순환
					d = (d+1) % 4; 
					x += deltaR[d];
					y += deltaC[d];
				}
			}
			System.out.println("#"+tc);
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++)
					System.out.print(nums[i][j] + " ");
				System.out.println();
			}
		}
	}
}
