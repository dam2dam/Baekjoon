import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

	static final String PUSH_FRONT = "push_front";
	static final String PUSH_BACK = "push_back";
	static final String POP_FRONT = "pop_front";
	static final String POP_BACK = "pop_back";
	static final String SIZE = "size";
	static final String EMPTY = "empty";
	static final String FRONT = "front";
	static final String BACK = "back";
	static final String ENTER = "\n";
	static final int NOTHING = -1;
	static final int TRUE = 1;
	static final int FALSE = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		StringBuilder stringBuilder = new StringBuilder();

		int N = Integer.parseInt(bufferedReader.readLine());
		int X = 0;
		ArrayDeque<Integer> deque = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			String command = stringTokenizer.nextToken();

			switch (command) {
				case PUSH_FRONT:
					X = Integer.parseInt(stringTokenizer.nextToken());
					deque.offerFirst(X);
					break;

				case PUSH_BACK:
					X = Integer.parseInt(stringTokenizer.nextToken());
					deque.offerLast(X);
					break;

				case POP_FRONT:
					if (deque.isEmpty()) {
						stringBuilder.append(NOTHING);
					} else {
						stringBuilder.append(deque.pollFirst());
					}
					stringBuilder.append(ENTER);
					break;

				case POP_BACK:
					if (deque.isEmpty()) {
						stringBuilder.append(NOTHING);
					} else {
						stringBuilder.append(deque.pollLast());
					}
					stringBuilder.append(ENTER);
					break;

				case SIZE:
					stringBuilder.append(deque.size())
						.append(ENTER);
					break;

				case EMPTY:
					if (deque.isEmpty()) {
						stringBuilder.append(TRUE);
					} else {
						stringBuilder.append(FALSE);
					}
					stringBuilder.append(ENTER);
					break;

				case FRONT:
					if (deque.isEmpty()) {
						stringBuilder.append(NOTHING);
					} else {
						stringBuilder.append(deque.peekFirst());
					}
					stringBuilder.append(ENTER);
					break;

				case BACK:
					if (deque.isEmpty()) {
						stringBuilder.append(NOTHING);
					} else {
						stringBuilder.append(deque.peekLast());
					}
					stringBuilder.append(ENTER);
					break;
			}
		}
		System.out.println(stringBuilder);
		bufferedReader.close();
	}

}
