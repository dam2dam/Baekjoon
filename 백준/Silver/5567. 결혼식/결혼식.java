import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static final int SANGGUEN = 1;
	static final int LIMIT = 2;
	static int n, m;
	static ArrayList<Integer>[] relations;
	static boolean[] invited;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		n = Integer.parseInt(bufferedReader.readLine());
		m = Integer.parseInt(bufferedReader.readLine());

		relations = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			relations[i] = new ArrayList<>();
		}
		int a;
		int b;
		for (int i = 0; i < m; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			a = Integer.parseInt(stringTokenizer.nextToken());
			b = Integer.parseInt(stringTokenizer.nextToken());
			relations[a].add(b);
			relations[b].add(a);
		}
		invited = new boolean[n + 1];
		invite(SANGGUEN);

		int count = 0;
		for (int i = 2; i <= n; i++) {
			if (invited[i]) {
				count++;
			}
		}
		System.out.println(count);
		bufferedReader.close();
	}

	private static void invite(int mainPerson) {
		Queue<Friend> queue = new ArrayDeque<>();

		queue.offer(new Friend(mainPerson, 0));
		invited[mainPerson] = true;

		while (!queue.isEmpty()) {
			Friend friend = queue.poll();
			if (friend.count >= LIMIT) {
				continue;
			}
			for (int next : relations[friend.number]) {
				if (!invited[next]) {
					queue.offer(new Friend(next, friend.count + 1));
					invited[next] = true;
				}
			}
		}
	}

	static class Friend {
		int number, count;

		public Friend(int number, int count) {
			this.number = number;
			this.count = count;
		}
	}
}
