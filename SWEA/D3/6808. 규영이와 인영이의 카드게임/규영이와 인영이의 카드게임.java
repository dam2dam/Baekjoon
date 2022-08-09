
	/*
	
	[6808] 규영이와 인영이의 카드게임
	규영이와 인영이는 1에서 18까지의 수가 적힌 18장의 카드로 게임을 하고 있다.
	한 번의 게임에 둘은 카드를 잘 섞어 9장씩 카드를 나눈다. 그리고 아홉 라운드에 걸쳐 게임을 진행한다.
	한 라운드에는 한 장씩 카드를 낸 다음 두 사람이 낸 카드에 적힌 수를 비교해서 점수를 계산한다.
	높은 수가 적힌 카드를 낸 사람은 두 카드에 적힌 수의 합만큼 점수를 얻고, 낮은 수가 적힌 카드를 낸 사람은 아무런 점수도 얻을 수 없다.
	이렇게 아홉 라운드를 끝내고 총점을 따졌을 때, 총점이 더 높은 사람이 이 게임의 승자가 된다.
	두 사람의 총점이 같으면 무승부이다.
	이번 게임에 규영이가 받은 9장의 카드에 적힌 수가 주어진다.
	규영이가 내는 카드의 순서를 고정하면, 인영이가 어떻게 카드를 내는지에 따른 9!가지 순서에 따라 규영이의 승패가 정해질 것이다.
	이 때, 규영이가 이기는 경우와 지는 경우가 총 몇 가지 인지 구하는 프로그램을 작성하라.
	
	인영이가 카드를 내는 9!가지의 경우에 대해 규영이가 게임을 이기는 경우의 수와 게임을 지는 경우의 수를 공백 하나로 구분하여 출력한다.
	
	1. 규영이의 카드를 입력받아 배열에 저장한 후, 전체 카드와 비교하여 규영이에게 없는 카드를 인영이의 카드 배열에 넣는다
	2. 인영이의 카드로 인영이가 낼 수 있는 카드의 순열을 만든다 : cardPermutation()
	3. 만들어진 카드 순열로 카드 비교 게임을 하여 규영이가 이긴 횟수와 진 횟수를 count한다 : compareCards()
	
	*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	final static int[] cards = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18};
	static int[] gyuyoung;
	static int[] inyoung;	
	static int[] inyoungPermutation;
	static boolean[] isSelected;
	static int winCount;
	static int loseCount;

	public static void main(String[] args) throws Exception {
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		// 입력1 : 테스트 케이스의 수
		int T = Integer.parseInt(bufferedReader.readLine());
		for (int tc = 1; tc <= T; tc++) {
			// 입력2 : 규영이가 내는 카드 순서대로 배열에 저장
			gyuyoung = new int[9];
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int i = 0; i < gyuyoung.length; i++)
				gyuyoung[i] = Integer.parseInt(stringTokenizer.nextToken());
			
			// 인영이의 카드 배열
			inyoung = new int[9];
			
			int inyoungCardsIndex = 0;	// 인영이의 카드를 배열에 넣을 때 사용할 index
			for (int card : cards) {
				boolean isExist = false;
				for (int i = 0; i < gyuyoung.length; i++)	// 전체 카드 중에 규영이 카드에 해당하면 존재 표시
					if (card == gyuyoung[i])
						isExist = true;
				if (isExist == false)	// 규영이에게 존재하지 않는 카드를 인영이에게 줌
					inyoung[inyoungCardsIndex++] = card;
			}
			winCount = 0;
			loseCount = 0;
			inyoungPermutation = new int[9];	// 인영이의 카드 순열을 저장할 배열
			isSelected = new boolean[9];
			cardPermutation(0);	// 인영이의 카드 순열 생성
			
			// 출력 (테스트 케이스 번호, 이긴 횟수, 진 횟수)
			System.out.printf("#%d %d %d\n", tc, winCount, loseCount);
		}
		bufferedReader.close();
	}
	// 인영이의 카드 순열 생성
	static void cardPermutation(int count) {
		if (count == 9) {
			compareCards(); // 인영이의 카드 순열이 완성되면 게임 시작
			return;
		}
		for (int i = 0; i < 9; i++) {
			if (isSelected[i] == true)
				continue;
			inyoungPermutation[count] = inyoung[i];
			isSelected[i] = true;
			cardPermutation(count+1);
			isSelected[i] = false;	// 순서를 고려하므로 다른 위치에서 사용할 수 있게 false
		}
	}
	// 카드 비교 게임
	static void compareCards() {
		int sum = 0;
		int gyuyoungScore = 0;
		int inyoungScore = 0;
		
		for (int i = 0; i < 9; i++) {
			// 카드에 적힌 수를 비교해서 높은 수가 적힌 카드를 가진 사람이 카드의 적힌 수의 합만큼 점수를 얻음
			sum = gyuyoung[i] + inyoungPermutation[i];
			if (gyuyoung[i] > inyoungPermutation[i])
				gyuyoungScore += sum;
			else if (gyuyoung[i] < inyoungPermutation[i])
				inyoungScore += sum;
		}
		// 총점이 더 높으면 규영이의 이긴 횟수를 늘려주고 더 낮으면 진 횟수를 늘려줌
		if (gyuyoungScore > inyoungScore)
			winCount++;
		else if (gyuyoungScore < inyoungScore)
			loseCount++;
	}
}
