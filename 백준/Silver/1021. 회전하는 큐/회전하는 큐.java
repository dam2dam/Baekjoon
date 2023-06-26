import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		int N = Integer.parseInt(stringTokenizer.nextToken());
		int M = Integer.parseInt(stringTokenizer.nextToken());
		LinkedList<Integer> deque = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			deque.offer(i);
		}

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		int target, index, count = 0;
		for (int i = 0; i < M; i++) {
			target = Integer.parseInt(stringTokenizer.nextToken());
			while (target != deque.peekFirst()) {
				index = deque.indexOf(target);
				if (index <= deque.size() / 2) {
					deque.offerLast(deque.pollFirst());
					count++;
				} else {
					deque.offerFirst(deque.pollLast());
					count++;
				}
			}
			deque.pollFirst();
		}
		System.out.println(count);
		bufferedReader.close();
	}
}
