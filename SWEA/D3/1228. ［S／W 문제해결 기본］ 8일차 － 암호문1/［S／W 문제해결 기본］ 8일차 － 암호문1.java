	
/*
	
	[1228] [S/W 문제해결 기본] 8일차 - 암호문1
	0 ~ 999999 사이의 수를 나열하여 만든 암호문이 있다.
	이 암호문은 특수 제작된 처리기로만 수정이 가능하다.
	이 처리기는 다음과 같이 1개의 기능을 제공한다.
	1. I(삽입) x, y, s : 앞에서부터 x의 위치 바로 다음에 y개의 숫자를 삽입한다. s는 덧붙일 숫자들이다.[ ex) I 3 2 123152 487651 ]
	
	#기호와 함께 테스트 케이스의 번호를 출력하고, 공백 문자 후 수정된 암호문의 처음 10개 항을 출력한다.
	
	1. 입력받은 암호문을 LinkedList에 저장한다
	2. 명령어를 입력받아 덧붙일 숫자들을 array에 저장한다
	3. 덧붙일 숫자를 하나씩 꺼내와서 LinkedList의 해당 위치(x)에 넣는다 
		숫자가 여러개이므로 위치 인덱스를 증가시키며 차례로 넣는다 (증가시키지 않으면 숫자가 뒤로 밀려나 역순이 된다)
	
	 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		// 테스트 케이스 10개
		for (int tc = 1; tc <= 10; tc++) {
			// 입력 1 : 원본 암호문의 길이 N ( 10 ≤ N ≤ 20 의 정수)
			int n = Integer.parseInt(bufferedReader.readLine());
			// 암호문을 저장할 LinkedList
			LinkedList<String> ciphertext = new LinkedList<>();

			// 입력 2 : 원본 암호문
			StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int i = 0; i < n; i++)
				ciphertext.add(stringTokenizer.nextToken());
			
			// 입력 3 : 명령어의 개수 ( 5 ≤ N ≤ 10 의 정수)
			int command = Integer.parseInt(bufferedReader.readLine());
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			
			// 입력 4 : 명령어
			while (stringTokenizer.hasMoreTokens()) {
				if (stringTokenizer.nextToken().equals("I")) {
					// 앞에서부터 index의 위치 바로 다음에 numberOfCiphertext개의 숫자를 삽입한다
					int index = Integer.parseInt(stringTokenizer.nextToken());
					int numberOfCiphertext = Integer.parseInt(stringTokenizer.nextToken());
					// 덧붙일 숫자들 배열에 저장
					String[] addtext = new String[numberOfCiphertext];
					for (int i = 0; i < numberOfCiphertext; i++)
						addtext[i] = stringTokenizer.nextToken();
					
					// 덧붙일 숫자들을 하나씩 가져와서 차례로 삽입
					for (String string : addtext)
						ciphertext.add(index++, string);
				}
			}
			// 수정된 암호문의 처음 10개 출력
			System.out.printf("#%d ", tc);
			for (int i = 0; i < 10; i++)
				System.out.print(ciphertext.get(i) + " ");
			System.out.println();
		}
		bufferedReader.close();
	}
}
