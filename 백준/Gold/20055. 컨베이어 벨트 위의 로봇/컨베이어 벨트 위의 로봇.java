import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, K, step, zeroDurabilityCount;
	static List<Integer> belt;
	static boolean[] isExistRobots;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		N = Integer.parseInt(stringTokenizer.nextToken());
		K = Integer.parseInt(stringTokenizer.nextToken());

		belt = new ArrayList<>();
		zeroDurabilityCount = 0;
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for (int i = 0; i < N * 2; i++) {
			belt.add(Integer.parseInt(stringTokenizer.nextToken()));
		}
		isExistRobots = new boolean[2 * N];

		while (true) {
			step++;
			// 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
			// 벨트 회전
			belt.add(0, belt.remove(2 * N - 1));
			// 로봇 회전
			for (int currentRobotIndex = N - 1; currentRobotIndex > 0; currentRobotIndex--) {
				isExistRobots[currentRobotIndex] = isExistRobots[currentRobotIndex - 1];
			}
			isExistRobots[0] = false;

			// 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다.
			isExistRobots[N - 1] = false;
			for (int currentRobotIndex = N - 1; currentRobotIndex > 0; currentRobotIndex--) {
				if (!isExistRobots[currentRobotIndex]
					&& isExistRobots[currentRobotIndex - 1]
					&& belt.get(currentRobotIndex) > 0) {

					isExistRobots[currentRobotIndex] = true;
					isExistRobots[currentRobotIndex - 1] = false;
					belt.set(currentRobotIndex, belt.get(currentRobotIndex) - 1);

					// 내구도가 0인 칸 세기
					if (belt.get(currentRobotIndex) == 0) {
						zeroDurabilityCount++;
					}
				}
			}

			// 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
			if (belt.get(0) != 0 && !isExistRobots[0]) {
				isExistRobots[0] = true;
				belt.set(0, belt.get(0) - 1);

				// 내구도가 0인 칸 세기
				if (belt.get(0) == 0) {
					zeroDurabilityCount++;
				}
			}

			// 4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
			if (zeroDurabilityCount >= K) {
				break;
			}
		}

		System.out.println(step);
		bufferedReader.close();
	}
}
