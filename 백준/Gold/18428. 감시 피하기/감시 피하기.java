import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static final String BLANK = "X";
	static final String TEACHER = "T";
	static final String STUDENT = "S";
	static final String WALL = "O";

	static int N;
	static String[][] hallway;
	static ArrayList<Integer> blanks, teachers;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		hallway = new String[N][N];
		blanks = new ArrayList<>();
		teachers = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {
				// 복도 상황 입력 받기
				String input = st.nextToken();
				hallway[i][j] = input;

				if (input.equals(BLANK)) {
					// 빈칸의 인덱스를 1차원으로 변환해서 저장
					blanks.add(i * N + j);
				} else if (input.equals(TEACHER)) {
					// 선생님의 인덱스를 1차원으로 변환해서 저장
					teachers.add(i * N + j);
				}
			}
		}
		// 빈칸 조합을 위한 방문 체크 배열 초기화
		visited = new boolean[blanks.size()];
		// 모든 학생들을 감시로부터 피하도록 할 수 있다면 YES
		if (wallCombination(0, 0)) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}

		br.close();
	}

	/**
	 * 빈칸 3개를 조합하는 함수
	 * @param start 선택을 고려하기 시작할 인덱스
	 * @param count 선택한 빈칸의 개수
	 * @return 모든 학생들을 감시로부터 피하도록 할 수 있는지 여부
	 */
	static boolean wallCombination(int start, int count) {
		// 빈칸 3개를 선택했다면 
		if (count == 3) {
			// 감시 체크
			if (checkTeachers()) {
				return true;
			}
			return false;
		}
		// start 이후의 인덱스만 선택
		for (int i = start; i < blanks.size(); i++) {
			// 이미 선택한 칸이라면 넘어감
			if (visited[i]) {
				continue;
			}
			// 장애물(벽)을 세움
			int current = blanks.get(i);
			hallway[current / N][current % N] = WALL;
			visited[i] = true;

			// 다음 빈칸 선택. 선택이 완료되었을 때 감시를 피할 수 있었다면 모두 true로 리턴
			if (wallCombination(i + 1, count + 1)) {
				return true;
			}
			// 세워둔 장애물(벽)을 제거
			hallway[current / N][current % N] = BLANK;
			visited[i] = false;
		}
		return false;
	}

	/**
	 * 선생님의 감시를 확인하는 함수
	 * @return 모든 학생이 감시를 피했는지 여부
	 */
	static boolean checkTeachers() {
		for (int teacher : teachers) {
			int teacherR = teacher / N;
			int teacherC = teacher % N;

			String current;
			// 해당 선생님의 왼쪽 감시
			for (int nr = teacherR - 1; nr >= 0; nr--) {
				current = hallway[nr][teacherC];
				// 학생을 발견했다면 바로 false 반환
				if (current.equals(STUDENT)) {
					return false;
				}
				// 장애물(벽)을 발견했다면 다른 방향을 감시하도록 넘어감
				if (current.equals(WALL)) {
					break;
				}
			}
			// 해당 선생님의 위쪽 감시
			for (int nc = teacherC - 1; nc >= 0; nc--) {
				current = hallway[teacherR][nc];
				// 학생을 발견했다면 바로 false 반환
				if (current.equals(STUDENT)) {
					return false;
				}
				// 장애물(벽)을 발견했다면 다른 방향을 감시하도록 넘어감
				if (current.equals(WALL)) {
					break;
				}
			}
			// 해당 선생님의 아래쪽 감시
			for (int nr = teacherR + 1; nr < N; nr++) {
				current = hallway[nr][teacherC];
				// 학생을 발견했다면 바로 false 반환
				if (current.equals(STUDENT)) {
					return false;
				}
				// 장애물(벽)을 발견했다면 다른 방향을 감시하도록 넘어감
				if (current.equals(WALL)) {
					break;
				}
			}
			// 해당 선생님의 오른쪽 감시
			for (int nc = teacherC + 1; nc < N; nc++) {
				current = hallway[teacherR][nc];
				// 학생을 발견했다면 바로 false 반환
				if (current.equals(STUDENT)) {
					return false;
				}
				// 장애물(벽)을 발견했다면 감시 멈춤
				if (current.equals(WALL)) {
					break;
				}
			}
		}
		// 모든 감시를 피했다면 true 반환
		return true;
	}

}
