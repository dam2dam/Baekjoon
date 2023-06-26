import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bufferedReader.readLine());
		Stack<Integer> stack = new Stack<>();
		long checkCount = 0;
		for (int i = 0; i < N; i++) {
			int height = Integer.parseInt(bufferedReader.readLine());
			while (!stack.isEmpty()) {
				if (stack.peek() > height) {
					break;
				}
				stack.pop();
			}
			checkCount += stack.size();
			stack.push(height);
		}
		System.out.println(checkCount);
		bufferedReader.close();
	}
}
