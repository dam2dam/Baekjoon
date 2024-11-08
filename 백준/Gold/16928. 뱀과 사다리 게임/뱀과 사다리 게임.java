import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static Map<Integer, Integer> laddersOrSnakes;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// 사다리와 뱀 정보를 map으로 저장 (key: 시작점, value: 도착점) 둘 다 가지고 있을 수 없으므로 함께 저장함
		laddersOrSnakes = new HashMap<>();
		int start;
		int end;
		for (int i = 0; i < N + M; i++) {
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			laddersOrSnakes.put(start, end);
		}
		int answer = playGame();
		System.out.println(answer);

		br.close();
	}

	static int playGame() {
		Queue<Space> pq = new ArrayDeque<>();
		boolean[] visited = new boolean[101];
		// 1번 칸에서 시작
		pq.offer(new Space(1, 0));
		visited[1] = true;

		while (!pq.isEmpty()) {
			Space current = pq.poll();

			for (int dice = 1; dice <= 6; dice++) {
				int next = current.index + dice;

				// 다음 칸이 100번 칸이면 주사위를 굴린 횟수 반환
				if (next == 100) {
					return current.diceCount + 1;
				}
				// 칸이 100을 넘어가면 이동 불가
				if (next > 100) {
					continue;
				}
				// 이미 갔던 곳은 안 감
				if (visited[next]) {
					continue;
				}
				if (laddersOrSnakes.containsKey(next)) {
					pq.offer(new Space(laddersOrSnakes.get(next), current.diceCount + 1));
				} else {
					pq.offer(new Space(next, current.diceCount + 1));
				}
				visited[next] = true;
			}
		}
		return 0;
	}

	/**
	 * 보드판에서 칸의 정보
	 */
	static class Space {
		int index;
		int diceCount;

		/**
		 * @param index 칸 번호
		 * @param diceCount 주사위를 굴린 횟수
		 */
		public Space(int index, int diceCount) {
			this.index = index;
			this.diceCount = diceCount;
		}
	}
}
