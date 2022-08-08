
/*
	[9229] 한빈이와 Spot Mart
	한빈이는 퇴근길에 스팟마트에 들러 과자 두 봉지를 사서 양 손에 하나씩 들고 가려고 한다.
	최대한 양이 많은 (무게가 많이 나가는) 과자 봉지를 고르고 싶으나, 
	스팟마트에는 N개의 과자 봉지가 있으며, 각 과자 봉지는 ai그램의 무게를 가진다.
	과자 두 봉지의 무게가 M 그램을 초과하면 무거워서 과자를 들고 다닐 수 없다.
	한빈이가 들고 다닐수 있는 과자들의 최대 무게 합을 출력하라. 한빈이는 과자를 “정확히” 두 봉지 사야 함에 유의하라.
	
	각 테스트 케이스마다 ‘#x’(x는 테스트 케이스 번호를 의미, 1부터 시작)를 출력하고, 한빈이가 들고 갈 수 있는 과자 봉지의 무게 합 최대를 출력하라.
	
	1. 배열에 과자 봉지 무게를 저장한다
	2. 재귀함수를 통해 조합을 구현하여 과자 봉지 무게 합을 구한다
	3. 제한을 넘지 않는 무게 합 최대값을 출력한다
	
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[] snack;
	static int[] snackCombination;	// 2개의 과자 봉지 조합
	static int n;
	static int m;
	static int sum;
	static int max;

	public static void main(String[] args) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		// 입력1 : 테스트 케이스의 수
		int T = Integer.parseInt(bufferedReader.readLine());
		for (int tc = 1; tc <= T; tc++) {
			// 입력2 : n:과자 봉지의 개수, m:무게 합 제한
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			n = Integer.parseInt(stringTokenizer.nextToken());
			m = Integer.parseInt(stringTokenizer.nextToken());
			
			snack = new int[n];	// 스팟마트에 있는 과자 봉지의 배열
			snackCombination = new int[2];	// 2개의 과자 봉지 조합
			
			// 배열에 과자 봉지 무게 저장
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int i = 0; i < n; i++)
				snack[i] = Integer.parseInt(stringTokenizer.nextToken());
			
			max = -1;
			combinationforMax(0, 0);
			
			// 출력
			System.out.printf("#%d %d\n", tc, max);
		}
		bufferedReader.close();
	}
	static void combinationforMax(int start, int index) {
		if (index == 2) {	// 2개 조합이 되면
			sum = snackCombination[0] + snackCombination[1];	//무게의 합 저장
			if (sum <= m)	//무게의 합이 제한을 초과하지 않으면
				max = Math.max(max, sum);	// 무게 합 최대 저장
			return;
		}
		for (int i = start; i < n; i++) {
			snackCombination[index] = snack[i];	// 과자를 start번째부터 채워넣음
			combinationforMax(i+1, index+1);	// 다음 수 조합
		}
	}
}
