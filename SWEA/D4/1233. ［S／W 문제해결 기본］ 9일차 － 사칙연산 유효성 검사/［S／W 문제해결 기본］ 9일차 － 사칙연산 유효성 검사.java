
/*

	[1233] [S/W 문제해결 기본] 9일차 - 사칙연산 유효성 검사
	사칙연산으로 구성되어 있는 식은 이진 트리로 표현할 수 있다.
	임의의 정점에 연산자가 있으면 해당 연산자의 왼쪽 서브 트리의 결과와 오른쪽 서브 트리의 결과를 사용해서 해당 연산자를 적용한다.
	사칙연산 “+, -, *, /”와 양의 정수로만 구성된 임의의 이진 트리가 주어질 때, 이 식의 유효성을 검사하는 프로그램을 작성하여라.
	(단, 계산이 가능한지가 아닌 유효성을 검사하는 문제이므로 0으로 나누는 경우는 고려하지 않는다.)

	여기서 말하는 유효성이란, 사칙연산 “+, -, *, /”와 양의 정수로 구성된 임의의 식이 적절한 식인지를 확인하는 것으로, 
	계산이 가능하다면 “1”, 계산이 불가능할 경우 “0”을 출력한다.

	1. 트리의 정보를 2차원 배열에 저장한다 { {정점번호, 값, 왼쪽자식노드, 오른쪽자식노드}, ... }
	2. 단말 노드인 경우 (배열의 길이 2), 왼쪽 자식 노드만 있는 경우 (배열의 길이 3), 
		왼쪽 자식 노드와 오른쪽 자식 노드가 모두 있는 경우 (배열의 길이 4)로 나누어 유효성을 검사를 구현한다 : isValid()
		2-1. 단말 노드인 경우, 값이 정수여야 함
		2-2. 왼쪽 자식 노드만 있는 경우, 식이 성립되지 않으므로 유효하지 않다
		2-3. 왼쪽 자식 노드와 오른쪽 자식 노드가 모두 있는 경우, 각각의 노드를 유효성 검사한다 
	3. 값이 연산자이면서 방문하지 않은 배열을 차례로 방문하여 유효성을 검사한다
	4. 유효하면 1 아니면 0을 출력한다

 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	static int n;
	static String[][] binaryTree;
	static boolean[] check;
	static int valid;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		// 테스트 케이스 : 10개
		for (int tc = 1; tc <= 10; tc++) {
			// 입력1 : n:트리가 갖는 정점의 총 수
			n = Integer.parseInt(bufferedReader.readLine());
			
			// 트리의 정보를 2차원 배열에 저장한다
			binaryTree = new String[n+1][]; // 정점의 번호를 index로 사용하기 위해 0번은 비워둔다
			for (int i = 1; i < binaryTree.length; i++)
				binaryTree[i] = bufferedReader.readLine().split(" ");
			
			check = new boolean[n+1];
			for (int i = 1; i < binaryTree.length; i++) {
				String value = binaryTree[i][1];
				if (value.equals("+") || value.equals("-") 	// 값이 연산자이면서 방문하지 않은 배열을 방문하여 유효성을 검사한다
						|| value.equals("*") || value.equals("/") && check[i] == false) {
					
					valid = 1;	// default 1에서 유효하지 않은 경우가 있으면 0
					isValid(binaryTree[i]);
				}
			}
			System.out.printf("#%d %d\n", tc, valid);
		}
		bufferedReader.close();
	}

	static void isValid(String[] array) {
		int index = Integer.parseInt(array[0]);
		check[index] = true;

		// 단말 노드인 경우 : 값이 정수가 아니면 식이 유효하지 않다
		if (array.length == 2)
			if (Character.isDigit(array[1].charAt(0)) == false) {
				valid = 0;
				return;
			}
		
		// 왼쪽 자식 노드만 있는 경우 : 식이 유효하지 않다
		else if (array.length == 3) {
			valid = 0;
			return;
		}
		
		// 왼쪽 자식 노드와 오른쪽 자식 노드가 모두 있는 경우
		else if (array.length == 4) {
			int left = Integer.parseInt(array[2]);
			int right = Integer.parseInt(array[3]);
			
			if (check[left] == false && check[right] == false 
					&& array[1].equals("+") || array[1].equals("-") || array[1].equals("*") || array[1].equals("/")) {
				
				isValid(binaryTree[left]);	// 왼쪽 서브 트리 유효성 검사
				isValid(binaryTree[right]);	// 오른쪽 서브 트리 유효성 검사
			}
		}
	}

}
