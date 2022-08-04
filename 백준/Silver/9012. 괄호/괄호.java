import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		//테스트 케이스 개수 입력
		int T = Integer.parseInt(bufferedReader.readLine());
		for (int tc = 0; tc < T; tc++) {
			//괄호 문자열 입력
			String input = bufferedReader.readLine();
			
			Stack<Character> parenthesis = new Stack<>();
			for (int i = 0; i < input.length(); i++) {
				if (parenthesis.empty())	//스택이 비어있으면 괄호 push
					parenthesis.push(input.charAt(i));
				
				else if (parenthesis.peek() == '(' && input.charAt(i) == ')') {	//짝이 맞으면 pop
					parenthesis.pop();
					continue;
				}
				else 
					parenthesis.push(input.charAt(i));	//짝이 맞을 때까지 괄호 push
			}
			//상태 : 올바른 문자열이면 YES 아니면  NO 출력
			String status = "NO";
			//괄호의 모양이 바르게 구성되어 스택이 비어있으면 YES
			if (parenthesis.empty())	
				status = "YES";
			System.out.println(status);
		}
		
		bufferedReader.close();
	}
}
