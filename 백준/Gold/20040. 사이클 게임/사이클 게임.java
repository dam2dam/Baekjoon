import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		int n = Integer.parseInt(stringTokenizer.nextToken());
		int m = Integer.parseInt(stringTokenizer.nextToken());

		parents = new int[n];
		for (int i = 0; i < n; i++) {
			parents[i] = i;
		}
		for (int turn = 1; turn <= m; turn++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int vertex1 = Integer.parseInt(stringTokenizer.nextToken());
			int vertex2 = Integer.parseInt(stringTokenizer.nextToken());
			if (!union(vertex1, vertex2)) {
				System.out.println(turn);
				return;
			}
		}
		System.out.println(0);

		bufferedReader.close();
	}

	static boolean union(int vertex1, int vertex2) {
		int root1 = find(vertex1);
		int root2 = find(vertex2);
		if (root1 == root2) {
			return false;
		}
		if (root1 > root2) {
			parents[root1] = root2;
		} else {
			parents[root2] = root1;
		}
		return true;
	}

	static int find(int vertex) {
		if (parents[vertex] == vertex) {
			return vertex;
		}
		return parents[vertex] = find(parents[vertex]);
	}
}
