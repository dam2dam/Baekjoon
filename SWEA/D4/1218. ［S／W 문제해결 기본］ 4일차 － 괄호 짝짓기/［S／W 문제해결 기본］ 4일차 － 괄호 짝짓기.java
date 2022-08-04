import java.util.Scanner;
import java.util.Stack;

public class Solution {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		//테스트 케이스 수
		for (int tc = 1; tc <= 10; tc++) {
			//테스트 케이스의 길이
			int tcLength = scanner.nextInt();
			//괄호 문자열을 입력받아 배열에 할당
			char[] inputs = new char[tcLength];
			String input = scanner.next();
			for (int i = 0; i < inputs.length; i++)
				inputs[i] = input.charAt(i);
			
			//유효성 여부	1:유효함, 0:유효하지 않음
			int valid = 0;
			//괄호를 하나씩 stack에 넣고 짝이 맞으면 pop
			Stack<Character> brackets = new Stack<>();
			for (int i = 0; i < inputs.length; i++) {
				if (brackets.empty())
					brackets.push(inputs[i]);

				else if (brackets.peek() == '(' && inputs[i] == ')')
					brackets.pop();

				else if (brackets.peek() == '[' && inputs[i] == ']')
					brackets.pop();

				else if (brackets.peek() == '{' && inputs[i] == '}')
					brackets.pop();

				else if (brackets.peek() == '<' && inputs[i] == '>')
					brackets.pop();

				else
					brackets.push(inputs[i]);
			}
			//짝이 모두 맞아 스택이 비어있으면 유효함
			if (brackets.empty())
				valid = 1;
			//출력
			System.out.printf("#%d %d\n", tc, valid);
		}
		scanner.close();
	}
}
