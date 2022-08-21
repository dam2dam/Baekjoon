import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution {
	
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		for (int tc = 1; tc <= 10; tc++) {
			
			int T = Integer.parseInt(bufferedReader.readLine());
			
			Stack<Character> stack = new Stack<>();
			
			int valid = 1;
			String input = bufferedReader.readLine();
			char bracket;
			for (int i = 0; i < T; i++) {
				bracket = input.charAt(i);
				if(stack.isEmpty() == true) {
					stack.push(bracket);
					continue;
				}
				if(stack.peek() == '(' && bracket == ')')
					stack.pop();
				else if(stack.peek() == '{' && bracket == '}')
					stack.pop();
				else if(stack.peek() == '[' && bracket == ']')
					stack.pop();
				else if(stack.peek() == '<' && bracket == '>')
					stack.pop();
				else
					stack.push(bracket);
			}
			if(stack.isEmpty() == false) 
				valid = 0;
			System.out.printf("#%d %d\n", tc, valid);
			
		}
		bufferedReader.close();
	}
	
}
