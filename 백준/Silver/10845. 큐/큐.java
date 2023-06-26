import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

	static final String PUSH = "push";
	static final String POP = "pop";
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
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			String command = stringTokenizer.nextToken();
			switch (command) {
				case PUSH:
					int X = Integer.parseInt(stringTokenizer.nextToken());
					queue.offer(X);
					break;
				case POP:
					if (queue.isEmpty()) {
						stringBuilder.append(NOTHING);
					} else {
						stringBuilder.append(queue.pollFirst());
					}
					stringBuilder.append(ENTER);
					break;
				case SIZE:
					stringBuilder.append(queue.size())
						.append(ENTER);
					break;
				case EMPTY:
					if (queue.isEmpty()) {
						stringBuilder.append(TRUE);
					} else {
						stringBuilder.append(FALSE);
					}
					stringBuilder.append(ENTER);
					break;
				case FRONT:
					if (queue.isEmpty()) {
						stringBuilder.append(NOTHING);
					} else {
						stringBuilder.append(queue.peekFirst());
					}
					stringBuilder.append(ENTER);
					break;
				case BACK:
					if (queue.isEmpty()) {
						stringBuilder.append(NOTHING);
					} else {
						stringBuilder.append(queue.peekLast());
					}
					stringBuilder.append(ENTER);
					break;
			}
		}
		System.out.println(stringBuilder);
		bufferedReader.close();
	}

}
