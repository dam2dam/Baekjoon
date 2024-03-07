import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final int CARDS_SIZE = 1_000_001;
	static final String BLANK = " ";

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		StringBuilder answer = new StringBuilder();

		int N = Integer.parseInt(bufferedReader.readLine());
		// 플레이어의 카드
		int[] players = new int[N];
		// 플레이어가 들고있는 카드인지 여부
		boolean[] existCards = new boolean[CARDS_SIZE];
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for (int i = 0; i < N; i++) {
			players[i] = Integer.parseInt(stringTokenizer.nextToken());
			existCards[players[i]] = true;
		}
		// 점수
		int[] score = new int[CARDS_SIZE];
		for (int i = 0; i < N; i++) {
			// 현재 플레이어의 카드
			int player = players[i];
			for (int rival = player * 2; rival < CARDS_SIZE; rival += player) {
				// 현재 플레이어의 카드의 배수를 다른 플레이어(라이벌)가 들고있다면
				if (existCards[rival]) {
					// 현재 플레이어 1점 증가
					score[player]++;
					// 라이벌 1점 감소
					score[rival]--;
				}
			}
		}
		for (int i = 0; i < N; i++) {
			answer.append(score[players[i]]).append(BLANK);
		}
		System.out.println(answer);
		bufferedReader.close();
	}
}
