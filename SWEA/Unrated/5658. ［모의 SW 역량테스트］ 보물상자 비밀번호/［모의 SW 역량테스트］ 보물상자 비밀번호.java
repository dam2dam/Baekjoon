
	/*
	
	[5658] [모의 SW 역량테스트] 보물상자 비밀번호
	각 변에 다음과 같이 16진수 숫자(0~F)가 적혀 있는 보물상자가 있다.
	보물 상자의 뚜껑은 시계방향으로 돌릴 수 있고, 한 번 돌릴 때마다 숫자가 시계방향으로 한 칸씩 회전한다.
	각 변에는 동일한 개수의 숫자가 있고, 시계방향 순으로 높은 자리 숫자에 해당하며 하나의 수를 나타낸다.
	예를 들어 [Fig.1]의 수는 1A3, B54, 8F9, D66이고, [Fig.2]의 수는 61A, 3B5, 48F, 9D6이다.
	보물상자에는 자물쇠가 걸려있는데, 이 자물쇠의 비밀번호는 보물 상자에 적힌 숫자로 만들 수 있는 모든 수 중, K번째로 큰 수를 10진 수로 만든 수이다.
	N개의 숫자가 입력으로 주어졌을 때, 보물상자의 비밀 번호를 출력하는 프로그램을 만들어보자.
	(서로 다른 회전 횟수에서 동일한 수가 중복으로 생성될 수 있다. 크기 순서를 셀 때 같은 수를 중복으로 세지 않도록 주의한다.)
	
	출력의 각 줄은 '#t'로 시작하고, 공백을 한 칸 둔 다음 정답을 출력한다.
	
	1. N개의 16진수를 Character형태로 ArrayList에 저장한다.
	2. K번째로 '큰' 수 를 앞에서부터 찾기 위해,  내림차순으로 TreeSet을 선언한다.
	3. ArrayList에서 N/4길이만큼씩 요소를 StringBuileder로 붙이고 16진수로 변환하여 TreeSet에 저장한다.
	4. ArrayList의 맨 뒤 숫자를 앞으로 옮기고(시계방향으로 한 칸 회전) 위의 3번을 반복한다.
	5. TreeSet에서 K번째 수를 출력한다.
	
	*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Solution {

	static int T, N, K;
	static ArrayList<Character> Numbers;	// 입력받은 16진수 저장
	static TreeSet<Integer> treeSet;	// 보물 상자에 적힌 숫자로 만들 수 있는 모든 수 저장
	static StringBuilder stringBuilder;	// 각 숫자를 만들 때 임시로 숫자 문자열을 저장

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		
		// 입력1 : 테스트 케이스의 수
		T = Integer.parseInt(bufferedReader.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			// 입력2: N:숫자의 개수, K:크기 순서
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			N = Integer.parseInt(stringTokenizer.nextToken());
			K = Integer.parseInt(stringTokenizer.nextToken());
			
			// 입력3 : N개의 16진수를 Character형태로 ArrayList에 저장
			Numbers = new ArrayList<>();
			String input = bufferedReader.readLine();
			for (int i = 0; i < N; i++) 
				Numbers.add(input.charAt(i));
			
			treeSet = new TreeSet<>(Collections.reverseOrder()); // K번째로 '큰' 수 를 앞에서부터 찾기 위해 내림차순 정렬
			stringBuilder = new StringBuilder();
			// (N/4)번 회전
			for (int i = 0; i < N/4; i++) {
				for (int startIndex = 0; startIndex < N; startIndex+=N/4) {
					for (int currentIndex = 0; currentIndex < N/4; currentIndex++)
						// 각 변의 숫자의 시작 인덱스부터 N/4길이만큼 숫자 붙이기
						stringBuilder.append(Numbers.get(startIndex+currentIndex));
					// StringBuilder로 만든 숫자 문자열을 16진수로 변환
					treeSet.add(Integer.parseInt((stringBuilder.toString()), 16));
					// StringBuilder 비우기
					stringBuilder.setLength(0);
				}
				//  맨 뒤 숫자를 앞으로 옮김 (시계방향으로 한 칸 회전)
				Numbers.add(0, Numbers.remove(N-1));
			}
			
			int count = 1;	// 1번째부터 시작
			int answer = 0;
			for (Integer number : treeSet) {
				// 내림차순 정렬 되어있는 treeSet에서 K번째 수 저장
				if (count == K)
					answer = number;
				count++;
			}
		
			// 출력
			System.out.printf("#%d %d\n", tc, answer);
		}
		bufferedReader.close();
	}
}
