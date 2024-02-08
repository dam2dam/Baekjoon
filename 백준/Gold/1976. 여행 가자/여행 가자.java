import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final int CONNECTION = 1;
	static int[] connected;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		int N = Integer.parseInt(bufferedReader.readLine());
		int M = Integer.parseInt(bufferedReader.readLine());

		connected = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			connected[i] = i;
		}

		int input;
		for (int i = 1; i <= N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 1; j <= N; j++) {
				input = Integer.parseInt(stringTokenizer.nextToken());
				// 연결된 나라 표기
				if (input == CONNECTION) {
					union(i, j);
				}
			}
		}
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		int plan;
		int start = 0;
		for (int i = 0; i < M; i++) {
			plan = Integer.parseInt(stringTokenizer.nextToken());
			// 여행 일정의 시작점 기록
			if (i == 0) {
				start = plan;
				continue;
			}
			// 시작점과 연결되지 않은 나라가 하나라도 있으면 불가능
			if (find(start) != find(plan)) {
				System.out.println("NO");
				System.exit(0);
			}
		}
		System.out.println("YES");
		bufferedReader.close();
	}

	static int find(int country) {
		if (connected[country] == country) {
			return country;
		}
		return connected[country] = find(connected[country]);
	}

	static void union(int country1, int country2) {
		int connection1 = find(country1);
		int connection2 = find(country2);

		if (connection1 == connection2) {
			return;
		}
		connected[connection2] = connection1;
	}
}
