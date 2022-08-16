
	/*
	
	[1244] [S/W 문제해결 응용] 2일차 - 최대 상금
	우승자는 주어진 숫자판들 중에 두 개를 선택에서 정해진 횟수만큼 서로의 자리를 위치를 교환할 수 있다.
	정해진 횟수만큼 교환이 끝나면 숫자판의 위치에 부여된 가중치에 의해 상금이 계산된다.
	숫자판의 오른쪽 끝에서부터 1원이고 왼쪽으로 한자리씩 갈수록 10의 배수만큼 커진다.
	여기서 주의할 것은 반드시 횟수만큼 교환이 이루어져야 하고 동일한 위치의 교환이 중복되어도 된다.
	정해진 횟수만큼 숫자판을 교환했을 때 받을 수 있는 가장 큰 금액을 계산해보자.
	
	교환 후 받을 수 있는 가장 큰 금액을 출력한다.
	
	1. 교환할 수 있는 모든 경우에 교환 횟수만큼 교환한다 : findMaxValue()
		1-1. 마지막 인덱스까지 교환이 끝난 후에도 교환 횟수가 채워지지 않은 경우 가중치가 낮은 자리끼리 교환을 반복하며 횟수를 채운다
	2. 숫자판 위치에 부여된 가중치에 의해 상금을 계산하여 최댓값을 갱신한다
	3. 가장 큰 금액을 출력한다
	
	*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int[] prize;
	static int exchange;
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		
		// 입력1 : 테스트 케이스의 수
		int T = Integer.parseInt(bufferedReader.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			// 입력2 : 숫자판의 정보를 입력받아 배열에 저장
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			String input = stringTokenizer.nextToken();
			prize = new int[input.length()];
			for (int i = 0; i < input.length(); i++) {
				prize[i] = input.charAt(i)-'0';
			}
			// 입력3 : 교환 횟수
			exchange = Integer.parseInt(stringTokenizer.nextToken());

			// 숫자판을 교환하여 가장 큰 금액 구하기
			max = 1;
			findMaxValue(0, 0);
				
			// 출력
			System.out.printf("#%d %d\n", tc, max);
		}
		bufferedReader.close();
	}
	
	
	static void findMaxValue(int start, int count) {
		
		int end = prize.length-1;
		// 마지막 인덱스까지 교환 횟수를 채우지 못한 경우 가중치가 낮은 자리끼리 교환하며 횟수 채우기
		if (start == end && count != exchange) {
			for (int i = 0; i < exchange-count; i++) {
				swap(end, end-1);
				max = calculate();
			}
			return;
		}
		
		if (count == exchange) {
			// 숫자판 위치에 부여된 가중치에 의한 상금 계산
			int number = calculate();
			// 최댓값 갱신
			max = Math.max(max, number);
			return;
		}
		
		for (int i = start; i < prize.length; i++) {
			for (int j = i+1; j < prize.length; j++) {
				if (prize[i] == prize[j])	// 같은 수는 실제로 교환하지 않음
					findMaxValue(i, count+1);	// 횟수가 채워질 때까지 교환하며 찾기

				swap(i, j);	// 숫자판 교환
				findMaxValue(i, count+1);	// 횟수가 채워질 때까지 교환하며 찾기
				swap(i, j);	// 다음 교환을 위해 원상복구						
			
			}
		}	
	}
	
	static int calculate() {
		int number = 0;
		int digit = 1;
		for (int i = prize.length-1; i >= 0 ; i--) {
			number += prize[i] * digit;
			digit *= 10;
		}
		return number;
	}
	
	static void swap(int num1, int num2) {
		int temp = prize[num1];
		prize[num1] = prize[num2];
		prize[num2] = temp;
	}

}
