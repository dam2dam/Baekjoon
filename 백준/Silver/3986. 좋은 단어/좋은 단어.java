import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bufferedReader.readLine());

		Stack<Character> stack;
		int goodWordCount = 0;
		for (int i = 0; i < N; i++) {
			String word = bufferedReader.readLine();
			int wordLength = word.length();
			if (wordLength % 2 == 1) {
				continue;
			}
			stack = new Stack<>();
			stack.push(word.charAt(0));
			for (int j = 1; j < wordLength; j++) {
				if (!stack.isEmpty() && stack.peek() == word.charAt(j)) {
					stack.pop();
				} else {
					stack.push(word.charAt(j));
				}
			}
			if (stack.isEmpty()) {
				goodWordCount++;
			}
		}
		System.out.println(goodWordCount);
		bufferedReader.close();
	}
}
