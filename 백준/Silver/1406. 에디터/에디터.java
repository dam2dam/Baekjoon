import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static final String CURSOR_TO_LEFT = "L";
	static final String CURSOR_TO_RIGHT = "D";
	static final String DELETE_ON_LEFT = "B";
	static final String ADD_TO_RIGHT = "P";

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		String input = bufferedReader.readLine();
		int N = input.length();
		Stack<String> left = new Stack<>();
		Stack<String> right = new Stack<>();
		for (int i = 0; i < N; i++) {
			left.push(String.valueOf(input.charAt(i)));
		}
		String command, character;
		int theNumberofCommand = Integer.parseInt(bufferedReader.readLine());
		for (int i = 0; i < theNumberofCommand; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			command = stringTokenizer.nextToken();

			switch (command) {
				case CURSOR_TO_LEFT:
					if (left.size() > 0) {
						right.push(left.pop());
					}
					break;
				case CURSOR_TO_RIGHT:
					if (right.size() > 0) {
						left.push(right.pop());
					}
					break;
				case DELETE_ON_LEFT:
					if (left.size() > 0) {
						left.pop();
					}
					break;
				case ADD_TO_RIGHT:
					character = stringTokenizer.nextToken();
					left.push(character);
					break;
			}
		}
		while (!left.isEmpty()) {
			right.push(left.pop());
		}
		StringBuilder stringBuilder = new StringBuilder();
		while (!right.isEmpty()) {
			stringBuilder.append(right.pop());
		}
		System.out.print(stringBuilder);
		bufferedReader.close();
	}
}
