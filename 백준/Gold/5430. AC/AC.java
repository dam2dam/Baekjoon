	/*
	
	[5430] AC
	AC는 정수 배열에 연산을 하기 위해 만든 언어이다. 이 언어에는 두 가지 함수 R(뒤집기)과 D(버리기)가 있다.
	함수 R은 배열에 있는 수의 순서를 뒤집는 함수이고, D는 첫 번째 수를 버리는 함수이다. 배열이 비어있는데 D를 사용한 경우에는 에러가 발생한다.
	함수는 조합해서 한 번에 사용할 수 있다.
	배열의 초기값과 수행할 함수가 주어졌을 때, 최종 결과를 구하는 프로그램을 작성하시오.
	
	각 테스트 케이스에 대해서, 입력으로 주어진 정수 배열에 함수를 수행한 결과를 출력한다. 만약, 에러가 발생한 경우에는 error를 출력한다.
	
	1. 배열을 deque에 저장한다
	2. 배열의 방향 상태를 나타내는 boolean 변수를 사용한다 : reverse
	3. 함수 R : 배열 방향 상태를 true와 xor연산하여 바꾼다
	4. 함수 D : 배열 방향 상태에 따라 앞이나 뒤에서 첫 번째 수를 버린다
	5. 비어있을 때 함수 D를 통해 삭제하면 예외가 발생하도록 remove(),removeLast()를 사용하고, error를 출력하도록 예외처리한다
	
	*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer stringTokenizer;

		// 입력1 : 테스트 케이스의 개수
		int T = Integer.parseInt(bufferedReader.readLine());
		for (int tc = 0; tc < T; tc++) {
			
			// 입력2 : 수행할 함수 배열에 입력
			String input = bufferedReader.readLine();
			char[] function = new char[input.length()];
			for (int i = 0; i < input.length(); i++)
				function[i] = input.charAt(i);
			
			// 입력3 : 배열에 들어있는 수의 개수
			int n = Integer.parseInt(bufferedReader.readLine());
			
			// 입력4 : [x1, x2, ... xn] 형태로 배열에 들어있는 정수를  Deque에 저장
			input = bufferedReader.readLine();
			stringTokenizer = new StringTokenizer(input, "[],"); // 대괄호와 쉼표를 구분자로 하여 분할
			
			Deque<Integer> deque = new ArrayDeque<>();
			String next;
			while(stringTokenizer.hasMoreTokens()) {
				next = stringTokenizer.nextToken();
				if (next.equals(""))	// 빈 배열을 입력받았을 경우 deque에 넣지 않고 비어있는 채로 진행한다
					break;
				deque.offer(Integer.parseInt(next));
			}
			
			try {
				boolean reverse = false;	// 배열 정방향:false, 역방향:true
				for (char func : function) {
					switch (func) {
					
						case 'R':	// 배열 순서 뒤집는 함수
							reverse ^= true;	// true와 xor 연산으로 배열 방향 상태 변경
							break;
	
						case 'D':	// 첫번째 수 버리는 함수
							// 현재 배열의 상태가 정방향이면 앞에서 첫번째 수 버리기
							if (reverse == false)	
								deque.remove();
							// 현재 배열의 상태가 역방향이면 뒤에서 첫번째 수 버리기
							else 
								deque.removeLast();	
							break;
					}
				}
				// 출력 : [x1, x2, ... xn] 형태
				bufferedWriter.write("[");
				while(deque.isEmpty() == false) {
					if (reverse == false)
						bufferedWriter.write(String.valueOf(deque.remove()));	// 현재 배열의 상태가 정방향이면 앞에서부터 차례로 출력
					else 
						bufferedWriter.write(String.valueOf(deque.removeLast()));	// 현재 배열의 상태가 역방향이면 뒤에서부터 차례로 출력

					if (deque.isEmpty() == false)
						bufferedWriter.write(",");
				}
				bufferedWriter.write("]\n");
			} 
			catch (Exception e) {	// 에러가 발생한 경우 "error" 출력
				bufferedWriter.write("error\n");
			}
		}
		bufferedReader.close();
		bufferedWriter.close();
	}

}
