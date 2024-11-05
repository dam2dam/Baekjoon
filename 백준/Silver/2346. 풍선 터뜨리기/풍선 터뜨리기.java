import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static final String SPACE = " ";
	static int N;
	static Deque<Balloon> balloons;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		balloons = new ArrayDeque<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int value = Integer.parseInt(st.nextToken());
			balloons.offer(new Balloon(i, value));
		}
		popBalloon();
		System.out.println(answer);

		br.close();
	}

	static void popBalloon() {
		int moveCount;

		while (!balloons.isEmpty()) {
			// 터지는 풍선
			Balloon balloon = balloons.poll();
			answer.append(balloon.number).append(SPACE);

			if (balloons.isEmpty()) {
				return;
			}

			int value = balloon.value;
			// 양수 : 시계 방향
			if (value > 0) {
				// 터질 풍선이 맨 앞에 놓이도록 (value - 1)번만 옮김
				moveCount = value - 1;
				for (int i = 0; i < moveCount; i++) {
					balloons.offer(balloons.poll());
				}
			} else if (value < 0) {
				// 음수 : 반시계 방향
				moveCount = Math.abs(value);
				for (int i = 0; i < moveCount; i++) {
					balloons.offerFirst(balloons.pollLast());
				}
			}
		}

	}

	static class Balloon {
		int number;
		int value;

		public Balloon(int number, int value) {
			this.number = number;
			this.value = value;
		}
	}
}
