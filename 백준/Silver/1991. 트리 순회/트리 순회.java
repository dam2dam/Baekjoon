import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final char ASCII_A = 'A';
	static final int NOTHING = '.' - 'A';
	static final String ENTER = "\n";
	static Node[] tree;
	static StringBuilder answer;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		answer = new StringBuilder();

		int N = Integer.parseInt(bufferedReader.readLine());
		tree = new Node[26];
		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int value = stringTokenizer.nextToken().charAt(0) - ASCII_A;
			int left = stringTokenizer.nextToken().charAt(0) - ASCII_A;
			int right = stringTokenizer.nextToken().charAt(0) - ASCII_A;
			tree[value] = new Node(left, right);
		}
		preOrder(0);
		answer.append(ENTER);
		inOrder(0);
		answer.append(ENTER);
		postOrder(0);

		System.out.println(answer);
		bufferedReader.close();
	}

	static void preOrder(int value) {
		int left = tree[value].left;
		int right = tree[value].right;

		answer.append((char)(value + ASCII_A));
		if (left != NOTHING) {
			preOrder(left);
		}
		if (right != NOTHING) {
			preOrder(right);
		}
	}

	static void inOrder(int value) {
		int left = tree[value].left;
		int right = tree[value].right;

		if (left != NOTHING) {
			inOrder(left);
		}
		answer.append((char)(value + ASCII_A));
		if (right != NOTHING) {
			inOrder(right);
		}
	}

	static void postOrder(int value) {
		int left = tree[value].left;
		int right = tree[value].right;

		if (left != NOTHING) {
			postOrder(left);
		}
		if (right != NOTHING) {
			postOrder(right);
		}
		answer.append((char)(value + ASCII_A));
	}

	static class Node {
		int left, right;

		public Node(int left, int right) {
			this.left = left;
			this.right = right;
		}
	}
}
