import java.util.Arrays;

class Solution {
    
    static int N, index;
	static Node[] nodes;
	static boolean[] visited;
	static int[] result;
    
    public int[][] solution(int[][] nodeinfo) {
        N = nodeinfo.length;
		int[][] answer = new int[2][N];
		// node 번호 매겨서 저장
		nodes = new Node[N];
		for (int i = 0; i < N; i++) {
			nodes[i] = new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]);
		}
		// 레벨이 높은 순서로 정렬 (같은 레벨이라면 왼쪽->오른쪽 순)
		Arrays.sort(nodes);
		// 이진 트리 생성
		Node root = nodes[0];
		for (int i = 1; i < N; i++) {
			Node node = nodes[i];
			makeTree(root, node);
		}
		// 전위 순회
		result = new int[N];
		visited = new boolean[N + 1];
		index = 0;
		preorder(root);
		answer[0] = result;
		// 후위 순회
		result = new int[N];
		visited = new boolean[N + 1];
		index = 0;
		postorder(root);
		answer[1] = result;
        
        return answer;
    }
    /**
	 * 트리를 생성하는 메서드
	 * @param parent 부모 노드
	 * @param node 트리에 합쳐질 노드
	 */
	static void makeTree(Node parent, Node node) {
		// 현재 노드가 부모 노드보다 왼쪽에 있고
		if (node.x < parent.x) {
			// 부모 노드의 왼쪽 자식 노드가 없다면
			if (parent.leftChild == null) {
				// 현재 노드가 부모 노드의 왼쪽 자식이 됨
				parent.leftChild = node;
				// 부모 노드의 왼쪽 자식 노드가 이미 있다면
			} else {
				// 왼쪽 자식 노드를 기준으로 합쳐보기
				makeTree(parent.leftChild, node);
			}
			// 현재 노드가 부모 노드보다 오른쪽에 있고
		} else {
			// 부모 노드의 오른쪽 자식 노드가 없다면
			if (parent.rightChild == null) {
				// 현재 노드가 부모 노드의 오른쪽 자식이 됨
				parent.rightChild = node;
				// 부모 노드의 오른쪽 자식 노드가 이미 있다면
			} else {
				// 오른쪽 자식 노드를 기준으로 합쳐보기
				makeTree(parent.rightChild, node);
			}
		}
	}

	/**
	 * 트리를 전위 순회하는 메서드
	 * @param node 순회할 노드
	 */
	static void preorder(Node node) {
		// 이미 방문했던 노드거나, 부모 노드가 리프 노드라면 return
		if (node == null || visited[node.number]) {
			return;
		}
		// 방문
		visited[node.number] = true;
		result[index++] = node.number;
		// 왼쪽 노드 순회
		preorder(node.leftChild);
		// 오른쪽 노드 순회
		preorder(node.rightChild);
	}

	/**
	 * 트리를 후위 순회하는 메서드
	 * @param node 순회할 노드
	 */
	static void postorder(Node node) {
		// 이미 방문했던 노드거나, 부모 노드가 리프 노드라면 return
		if (node == null || visited[node.number]) {
			return;
		}
		// 왼쪽 노드 순회
		postorder(node.leftChild);
		// 오른쪽 노드 순회
		postorder(node.rightChild);
		// 방문
		visited[node.number] = true;
		result[index++] = node.number;
	}

	static class Node implements Comparable<Node> {
		int number;
		int x;
		int y;
		Node leftChild;
		Node rightChild;

		/**
		 * @param number 노드 번호
		 * @param x 노드의 x 좌표
		 * @param y 노드의 y 좌표
		 */
		public Node(int number, int x, int y) {
			this.number = number;
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Node node) {
			// y 좌표가 같으면 (같은 level)
			if (this.y == node.y) {
				// x 좌표 오름차순
				return this.x < node.x ? -1 : this.x == node.x ? 0 : 1;
			}
			// y 좌표 내림차순
			return this.y > node.y ? -1 : 1;
		}
	}
}
