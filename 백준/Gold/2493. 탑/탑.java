import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static final int ZERO = 0;
	static final String BLANK = " ";

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		StringBuilder stringBuilder = new StringBuilder();
		Stack<Top> stack = new Stack<>();

		int N = Integer.parseInt(bufferedReader.readLine());
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for (int index = 1; index <= N; index++) {
			int height = Integer.parseInt(stringTokenizer.nextToken());
			while (!stack.isEmpty()) {
				if (stack.peek().height < height) {
					stack.pop();
				} else {
					stringBuilder.append(stack.peek().index).append(BLANK);
					break;
				}
			}
			if (stack.isEmpty()) {
				stringBuilder.append(ZERO).append(BLANK);
				stack.push(new Top(index, height));
				continue;
			}
			stack.push(new Top(index, height));
		}
		System.out.println(stringBuilder);
		bufferedReader.close();
	}

	static class Top {
		int index, height;

		public Top(int index, int height) {
			this.index = index;
			this.height = height;
		}
	}
}
