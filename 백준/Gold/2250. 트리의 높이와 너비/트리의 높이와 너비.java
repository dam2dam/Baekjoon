import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static final int LEFT = 0;
	static final int RIGHT = 1;
	static final int NULL = -1;
	static final String BLANK = " ";

	static int N, colNumber;
	static int[] parents;
	static int[][] tree;
	static ArrayList<Integer>[] frame;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		tree = new int[N + 1][2];
		parents = new int[N + 1];
		Arrays.fill(parents, NULL);
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			tree[num][LEFT] = left;
			tree[num][RIGHT] = right;

			if (left != NULL) {
				parents[left] = num;
			}
			if (right != NULL) {
				parents[right] = num;
			}
		}

		frame = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			frame[i] = new ArrayList<>();
		}
		colNumber = 1;
		inorder(searchRoot(), 1);

		int maxWidth = 1;
		int maxLevel = 1;
		for (int i = 2; i <= N; i++) {
			int rowWidth = calculateWidth(frame[i]);
			if (rowWidth > maxWidth) {
				maxWidth = rowWidth;
				maxLevel = i;
			}
		}
		System.out.println(maxLevel + BLANK + maxWidth);

		br.close();
	}

	/**
	 * 너비 계산
	 * @param currentLevel 현재 레벨의 노드가 있는 1차원 배열
	 * @return 너비
	 */
	private static int calculateWidth(ArrayList<Integer> currentLevel) {
		if (currentLevel.size() == 0) {
			return NULL;
		}
		int left = currentLevel.get(0);
		int right = currentLevel.get(currentLevel.size() - 1);

		return right - left + 1;
	}

	/**
	 * 중위 순회
	 * @param num    노드 번호
	 * @param level 현재 노드의 레벨
	 */
	private static void inorder(int num, int level) {
		if (num == NULL) {
			return;
		}
		inorder(tree[num][LEFT], level + 1);
		frame[level].add(colNumber++);
		inorder(tree[num][RIGHT], level + 1);
	}

	/**
	 * 루트 노드 찾기
	 * @return 루트 노드
	 */
	private static int searchRoot() {
		int root = 0;
		for (int i = 1; i <= N; i++) {
			if (parents[i] == NULL) {
				root = i;
				break;
			}
		}
		return root;
	}
}
