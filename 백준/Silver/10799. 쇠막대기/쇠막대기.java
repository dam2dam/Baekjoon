import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	static final char OPEN_BRACKET = '(';

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		String input = bufferedReader.readLine();
		int length = input.length();

		Stack<Character> stack = new Stack<>();
		boolean isPreviousLaser = false;
		int stickCount = 0;
		for (int i = 0; i < length; i++) {
			if (input.charAt(i) == OPEN_BRACKET) {
				stack.push(OPEN_BRACKET);
				isPreviousLaser = true;
				continue;
			}
			stack.pop();
			if (isPreviousLaser) {    // 레이저
				stickCount += stack.size();
			} else {    // 막대기
				stickCount++;
			}
			isPreviousLaser = false;
		}
		System.out.println(stickCount);
		bufferedReader.close();
	}
}
