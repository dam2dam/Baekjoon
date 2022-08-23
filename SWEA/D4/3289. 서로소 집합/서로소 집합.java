import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int T, N, M, command, a, b;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder stringBuilder = new StringBuilder();
		StringTokenizer stringTokenizer;

		T = Integer.parseInt(bufferedReader.readLine());
		for (int tc = 1; tc <= T; tc++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			N = Integer.parseInt(stringTokenizer.nextToken());
			M = Integer.parseInt(stringTokenizer.nextToken());

			stringBuilder.append("#").append(tc).append(" ");

			make(); 

			for (int i = 0; i < M; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				command = Integer.parseInt(stringTokenizer.nextToken());
				a = Integer.parseInt(stringTokenizer.nextToken());
				b = Integer.parseInt(stringTokenizer.nextToken());

				if (command == 0)
					union(a, b);
				else if (command == 1) {
					if(find(a) == find(b))
						stringBuilder.append(1);
					else
						stringBuilder.append(0);
				}
			}
			stringBuilder.append("\n");
		}
		System.out.println(stringBuilder);
		bufferedReader.close();
	}
	
	static void make() {
		parents = new int[N+1];
		for (int i = 0; i <= N; i++)
			parents[i] = i;
	}

	static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot != bRoot)
			parents[bRoot] = aRoot;
	}

	static int find(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = find(parents[a]);
	}
	
}
