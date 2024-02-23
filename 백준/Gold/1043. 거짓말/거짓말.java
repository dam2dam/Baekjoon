import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		int N = Integer.parseInt(stringTokenizer.nextToken());
		int M = Integer.parseInt(stringTokenizer.nextToken());

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		int knowNumber = Integer.parseInt(stringTokenizer.nextToken());
		// 진실을 아는 사람이 없다면 모든 파티에서 과장된 이야기를 할 수 있음
		if (knowNumber == 0) {
			System.out.println(M);
			return;
		}
		// 진실을 아는 사람 배열에 표시
		boolean[] knowPeople = new boolean[N + 1];
		for (int i = 0; i < knowNumber; i++) {
			int input = Integer.parseInt(stringTokenizer.nextToken());
			knowPeople[input] = true;
		}
		// unoin-find를 위한 parent 배열
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		ArrayList<Integer>[] party = new ArrayList[M];
		int previous = 0;
		for (int i = 0; i < M; i++) {
			party[i] = new ArrayList<>();
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			// 현재 파티 인원
			int partyPeople = Integer.parseInt(stringTokenizer.nextToken());
			for (int j = 0; j < partyPeople; j++) {
				int now = Integer.parseInt(stringTokenizer.nextToken());
				party[i].add(now);
				if (j > 0) {
					// 이전 사람과 같은 그룹으로 묶기
					union(previous, now);
				}
				previous = now;
			}
		}
		// 진실을 아는 사람의 그룹장은 진실을 알고 있다는 표시 해주기
		// (여기서 조금 헷갈렸지만 밑에서 파티 개수를 셀 때 어떤 사람이든 그룹장을 기준으로 하기 때문에 그룹장만 표시해줘도 된다)
		for (int i = 1; i <= N; i++) {
			if (knowPeople[i]) {
				knowPeople[find(i)] = true;
			}
		}
		int count = M;
		for (int i = 0; i < M; i++) {
			int people = party[i].get(0);
			// 각 파티의 그룹장이 진실을 알고 있다면 과장된 이야기를 할 수 있는 파티 개수 줄이기
			if (knowPeople[find(people)]) {
				count--;
			}
		}
		System.out.println(count);

		bufferedReader.close();
	}

	/**
	 * 같은 그룹이 된다.
	 */
	static void union(int people1, int people2) {
		int root1 = find(people1);
		int root2 = find(people2);

		if (root1 == root2) {
			return;
		}
		if (root1 > root2) {
			parent[root1] = root2;
		} else {
			parent[root2] = root1;
		}
	}

	/**
	 * 그룹장을 찾는다.
	 */
	static int find(int people) {
		if (parent[people] == people) {
			return people;
		}
		return parent[people] = find(parent[people]);
	}
}
