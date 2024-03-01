import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int[] candies;
	static ArrayList<Integer>[] friends;
	static ArrayList<Group> groups;
	static boolean[] visited;
	static int candySum, number;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		int N = Integer.parseInt(stringTokenizer.nextToken());
		int M = Integer.parseInt(stringTokenizer.nextToken());
		int K = Integer.parseInt(stringTokenizer.nextToken());

		// 아이들이 받은 사탕
		candies = new int[N + 1];
		// 친구 관계
		friends = new ArrayList[N + 1];
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for (int i = 1; i <= N; i++) {
			candies[i] = Integer.parseInt(stringTokenizer.nextToken());
			friends[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int from = Integer.parseInt(stringTokenizer.nextToken());
			int to = Integer.parseInt(stringTokenizer.nextToken());
			// 친구 관계 양방향으로 저장
			friends[from].add(to);
			friends[to].add(from);
		}
		// 친구 그룹들
		groups = new ArrayList<>();
		// 인덱스 편의를 위해 0번째에 하나 추가해두기
		groups.add(new Group(0, 0));

		visited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			if (visited[i]) {
				continue;
			}
			// 친구들이 엮여있는 그룹 만들기 (그룹의 사탕 수, 인원 세기)
			candySum = 0;
			number = 0;
			makeGroup(i);
			groups.add(new Group(number, candySum));
		}

		int numberofGroups = groups.size();
		dp = new int[numberofGroups][K];
		for (int i = 1; i < numberofGroups; i++) {
			Group group = groups.get(i);
			for (int j = 1; j < K; j++) {
				// 현재 고려하는 인원이 그룹원의 수보다 작으면 그룹을 선택하지 못한다
				if (j < group.number) {
					dp[i][j] = dp[i - 1][j];
					continue;
				}
				// 1. 그룹을 선택한다면, 해당 그룹의 사탕과, 해당 그룹원을 제외한 인원에서 가장 최대였던 사탕 수를 합한다
				int select = group.candy + dp[i - 1][j - group.number];
				// 2. 그룹을 선택하지 않는다면 직전까지 최대였던 사탕 수를 그대로 쓴다
				int notSelect = dp[i - 1][j];
				// 두 경우 중에 더 큰 수를 저장한다
				dp[i][j] = Math.max(select, notSelect);
			}
		}
		System.out.println(dp[numberofGroups - 1][K - 1]);

		bufferedReader.close();
	}

	/**
	 * 아이들의 친구를 방문하면서 그룹의 사탕 수와 인원 수를 기록한다
	 */
	static void makeGroup(int child) {
		visited[child] = true;
		candySum += candies[child];
		number++;
		for (int friend : friends[child]) {
			if (visited[friend]) {
				continue;
			}
			makeGroup(friend);
		}
	}

	static class Group {
		int number, candy;

		/**
		 * @param number 인원 수
		 * @param candy 사탕 수
		 */
		public Group(int number, int candy) {
			this.number = number;
			this.candy = candy;
		}
	}
}
