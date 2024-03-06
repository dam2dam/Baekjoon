import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final String ENTER = "\n";
	static int T, n, turnNumber;
	static int[] select, visited;
	static boolean[] hasTeam;
	static boolean isTheTeam;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		StringBuilder answer = new StringBuilder();

		T = Integer.parseInt(bufferedReader.readLine());
		for (int tc = 0; tc < T; tc++) {
			n = Integer.parseInt(bufferedReader.readLine());

			// 선택된 학생들의 번호
			select = new int[n + 1];
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int i = 1; i <= n; i++) {
				select[i] = Integer.parseInt(stringTokenizer.nextToken());
			}
			// 턴마다 다른 숫자로 방문 체크하는 배열
			visited = new int[n + 1];
			// 팀에 속하는지 여부를 저장하는 배열
			hasTeam = new boolean[n + 1];
			// 같은 팀이 될 수 있는지 여부
			isTheTeam = false;
			// 턴 번호
			turnNumber = 1;
			// 턴 번호를 증가시키며 방문하지 않은 학생을 탐색한다
			for (int i = 1; i <= n; i++) {
				if (visited[i] == 0) {
					searchTeam(i);
					turnNumber++;
				}
			}
			// 팀에 속하지 않는 학생을 센다
			int noTeamCount = 0;
			for (int i = 1; i <= n; i++) {
				if (!hasTeam[i]) {
					noTeamCount++;
				}
			}
			answer.append(noTeamCount).append(ENTER);
		}
		System.out.println(answer);
		bufferedReader.close();
	}

	/**
	 * 학생들이 팀에 속하는지 탐색한다
	 */
	static int searchTeam(int number) {
		// 방문했다면
		if (visited[number] > 0) {
			// 현재 턴에 방문했다면 (사이클)
			if (visited[number] == turnNumber) {
				// 팀이 될 수 있다
				isTheTeam = true;
			}
			return number;
		}
		// 현재 턴 방문 체크
		visited[number] = turnNumber;
		// (사이클이 생성되었다면) 사이클 시작점의 학생 번호
		int cycleNumber = searchTeam(select[number]);
		// 같은 팀이라면
		if (isTheTeam) {
			// 팀에 속한다
			hasTeam[number] = true;
			// 사이클 시작점으로 돌아왔다면 (팀 모두 체크완료)
			if (number == cycleNumber) {
				// 이제 다른 학생은 팀이 아님
				isTheTeam = false;
			}
		}
		// 사이클 시작점의 학생 번호를 반환한다
		return cycleNumber;
	}
}
