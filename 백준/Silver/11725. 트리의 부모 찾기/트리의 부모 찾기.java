import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static final int ROOT = 1;
	static final int UNVISITED = 0;
	static final String ENTER = "\n";
	static int N;
	static ArrayList<Integer>[] tree;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		StringBuilder answer = new StringBuilder();

		N = Integer.parseInt(bufferedReader.readLine());
		tree = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<>();
		}
		for (int i = 0; i < N - 1; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int from = Integer.parseInt(stringTokenizer.nextToken());
			int to = Integer.parseInt(stringTokenizer.nextToken());
			tree[from].add(to);
			tree[to].add(from);
		}
		parents = new int[N + 1];
		parents[ROOT] = ROOT;
		dfs(ROOT);

		for (int i = 2; i <= N; i++) {
			answer.append(parents[i])
				.append(ENTER);
		}
		System.out.println(answer);

		bufferedReader.close();
	}

	static void dfs(int parent) {
		for (int child : tree[parent]) {
			if (parents[child] != UNVISITED) {
				continue;
			}
			parents[child] = parent;
			dfs(child);
		}
	}
}
