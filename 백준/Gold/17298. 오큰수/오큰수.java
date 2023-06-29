import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static final int NOTHING = -1;
	static final String BLANK = " ";

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		int N = Integer.parseInt(bufferedReader.readLine());
		int[] numbers = new int[N];
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
		}

		int[] nge = new int[N];
		Stack<Integer> stack = new Stack<>();
		stack.push(numbers[N - 1]);
		nge[N - 1] = NOTHING;
		for (int i = N - 2; i >= 0; i--) {
			if (stack.isEmpty()) {
				stack.push(numbers[i]);
				nge[i] = NOTHING;
				continue;
			}
			while (!stack.isEmpty() && stack.peek() <= numbers[i]) {
				stack.pop();
			}
			if (!stack.isEmpty()) {
				nge[i] = stack.peek();
			} else {
				nge[i] = NOTHING;
			}
			stack.push(numbers[i]);
		}

		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < N; i++) {
			stringBuilder.append(nge[i]).append(BLANK);
		}
		System.out.println(stringBuilder);
		bufferedReader.close();
	}
}
