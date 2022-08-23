import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int T, N, M, a, b, count;
	static int[][] villege;
	static int[] parents, counts;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		T = Integer.parseInt(bufferedReader.readLine());
		for (int tc = 1; tc <= T; tc++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			N = Integer.parseInt(stringTokenizer.nextToken());
			M = Integer.parseInt(stringTokenizer.nextToken());

			parents = new int[N+1];
			for (int i = 1; i <= N; i++)
				parents[i] = i;

			for (int i = 0; i < M; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				a = Integer.parseInt(stringTokenizer.nextToken());
				b = Integer.parseInt(stringTokenizer.nextToken());

				union(a, b);
			}
			counts = new int[N+1];
			for (int i = 1; i <= N; i++)
				counts[find(parents[i])]++;
			count = 0;
			for (int i : counts)
				if(i > 0)
					count++;

			System.out.printf("#%d %d\n", tc, count);
		}
	}
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot)
			return false;

		parents[bRoot] = aRoot;
		return true;

	}

	static int find(int a) {
		if (parents[a] == a) {
			return a;
		}
		return find(parents[a]);
	}
}