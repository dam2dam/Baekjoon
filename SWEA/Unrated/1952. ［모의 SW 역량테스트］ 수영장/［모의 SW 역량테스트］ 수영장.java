	
	/*
	
	[1952] [모의 SW 역량테스트] 수영장
	김 프로는 지출이 너무 많아 내년 1년 동안 각 달의 이용 계획을 수립하고 가장 적은 비용으로 수영장을 이용할 수 있는 방법을 찾고 있다.
	수영장에서 판매하고 있는 이용권은 아래와 같이 4 종류이다.
	   1) 1일 이용권 : 1일 이용이 가능하다.
	   2) 1달 이용권 : 1달 동안 이용이 가능하다. 1달 이용권은 매달 1일부터 시작한다.
	   3) 3달 이용권 : 연속된 3달 동안 이용이 가능하다. 3달 이용권은 매달 1일부터 시작한다.
	       (11월, 12월에도 3달 이용권을 사용할 수 있다 / 다음 해의 이용권만을 구매할 수 있기 때문에 3달 이용권은 11월, 12월, 1윌 이나 12월, 1월, 2월 동안 사용하도록 구매할 수는 없다.)
	   4) 1년 이용권 : 1년 동안 이용이 가능하다. 1년 이용권은 매년 1월 1일부터 시작한다.
	이용 계획에 나타나는 숫자는 해당 달에 수영장을 이용할 날의 수를 의미한다.
	각 이용권의 요금과 각 달의 이용 계획이 입력으로 주어질 때,
	가장 적은 비용으로 수영장을 이용할 수 있는 방법을 찾고 그 비용을 정답으로 출력하는 프로그램을 작성하라.
	
	출력해야 할 정답은 이용 계획대로 수영장을 이용하는 경우 중 가장 적게 지출하는 비용이다.
	
	1. 이용권의 요금과 1년 이용 계획을 각각 배열에 저장한다
	2. 재귀를 사용하여 이용 금액을 누적하며 탐색하고 최솟값을 갱신한다
	3. 중간에 최소 금액을 넘겼다면 돌아가서 다른 방법을 탐색하고 탐색한다
	4. 탐색이 끝나면 수영장을 이용할 수 있는 최소 비용을 출력한다
	
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int T, min;
	static int[] prices, months;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		
		// 입력1 : 테스트 케이스의 수
		T = Integer.parseInt(bufferedReader.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			// 입력2 : 1일, 1달, 3달, 1년 이용권의 요금을 입력받아 prices배열에 저장
			prices = new int[4];
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int i = 0; i < 4; i++) 
				prices[i] = Integer.parseInt(stringTokenizer.nextToken());
			
			// 입력3 : 1~12월의 이용 계획을 입력받아 months배열에 저장
			months = new int[12];
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int i = 0; i < 12; i++)
				months[i] = Integer.parseInt(stringTokenizer.nextToken());
			
			// 1년 이용권의 금액을 최솟값으로 임의 할당
			min = prices[3]; 
			
			// 1월(index 0), 0원부터 탐색 시작
			search(0, 0);
			
			// 출력 : 
			System.out.printf("#%d %d\n", tc, min);
		}
        bufferedReader.close();

	}

	/**
	 * 수영장 이용 계획에 따라 최소 비용으로 이용할 수 있는 방법을 탐색하는 함수
	 * @param currentMonth 현재 달
	 * @param sum 현재 달까지의 이용 금액 누적합
	 */
	private static void search(int currentMonth, int sum) {
		
		// 현재 달까지의 이용금액 누적합이 최솟값을 넘었다면 더이상 탐색하지 않고 돌아감
		if (sum >= min) 
			return;
		
		// 12월까지 이용계획을 모두 탐색했다면 최솟값 갱신
		if (currentMonth >= 12) {
			min = Math.min(min, sum);
			return;
		}
		
		// 현재 달에 이용 계획이 없다면 금액을 누적하지 않고 다음 달 탐색 
		if (months[currentMonth] == 0)
			search(currentMonth+1, sum);
		
		else {
			// 1일 이용권을 사용했을 때 한 달 이용 금액 누적 후 다음 달 탐색 
			search(currentMonth+1, sum + (prices[0]*months[currentMonth]));
			
			// 1달 이용권을 사용했을 때 금액 누적 후 다음 달 탐색
			search(currentMonth+1, sum + prices[1]);
			
			// 3달 이용권을 사용했을 때 금액 누적 후 3달 뒤 탐색
			search(currentMonth+3, sum + prices[2]);
		}
	}

}
