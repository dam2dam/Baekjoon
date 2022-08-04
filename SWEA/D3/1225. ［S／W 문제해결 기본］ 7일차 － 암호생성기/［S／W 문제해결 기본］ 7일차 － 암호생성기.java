import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		//테스트 케이스 수
		for (int t = 0; t < 10; t++) {
			//테스트 케이스 번호 입력
			int tc = scanner.nextInt();
			//8개의 숫자 배열에 입력
			int[] nums = new int[8];
			for (int i = 0; i < 8; i++)
				nums[i] = scanner.nextInt();

			
			while(nums[7] > 0) {
				int decreasingNumber = 1;	//감소 해주는 수
				int temp;	//첫번째 숫자를 임시 저장할 변수
				
				//한 사이클
				for (int i = 0; i < 5; i++) {
					temp = nums[0] - decreasingNumber++;	//첫번째 숫자를 감소 후
					for (int j = 0; j < 7; j++)		//다른 숫자의 인덱스를 앞으로 당김
						nums[j] = nums[j+1];
					nums[7] = temp;		//감소한 첫번째 숫자 맨 뒤로 이동
					
					//감소한 숫자가 0보다 작으면 종료
					if (nums[7] <= 0) {
						nums[7] = 0;
						break;
					}
				}
			}
			
			//출력
			System.out.print("#"+tc+" ");
			for (int n : nums)
				System.out.print(n + " ");
			System.out.println();
		}
		scanner.close();
	}
}
