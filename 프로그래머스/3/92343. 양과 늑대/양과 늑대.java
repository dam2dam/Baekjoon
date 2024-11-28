class Solution {
    
    
	static final int SHEEP = 0;
	static final int PARENT = 0;
	static final int CHILD = 1;

	static int[] node;
	static int[][] connections;
	static boolean[] states;
	static int maxSheepCount;
    
    
    public int solution(int[] info, int[][] edges) {
        node = info;
		connections = edges;

        // 방문했던 곳 상태를 저장할 배열
		states = new boolean[(int)Math.pow(2, info.length)];
		states[1] = true;
		dfs(0, 0, 0, 1);
        
        return maxSheepCount;
    }
    /**
	 * 노드를 이동하며 양을 모으는 메서드
	 * @param index 현재 노드의 번호
	 * @param sheepCount 현재까지 모은 양의 수
	 * @param wolfCount 현재까지 따라온 늑대의 수
	 * @param state 현재까지 방문 체크 (비트마스킹)
	 */
	static void dfs(int index, int sheepCount, int wolfCount, int state) {
		// 현재 노드에 양이 있다면
		if (node[index] == SHEEP) {
			// 양의 수를 세기
			sheepCount++;
			// 양의 수 최댓값 갱신
			if (sheepCount > maxSheepCount) {
				maxSheepCount = sheepCount;
			}
			// 현재 노드에 늑대가 있다면
		} else {
			// 늑대의 수 세기
			wolfCount++;
			// 늑대의 수가 양의 수보다 많거나 같다면 돌아감
			if (wolfCount >= sheepCount) {
				return;
			}
		}
		// 모든 노드의 연결을 확인
		for (int[] connection : connections) {
			// 방문했던 곳(부모노드)에 연결된 노드(자식노드)를 방문하지 않았고
			// 현 상태와 같은 방문이 없었다면 (방문 순서에 상관없이 현재 거쳐온 곳과 같은 상태를 봤었다면 중복을 거름)
			if (((state & (1 << connection[PARENT])) > 0) && ((state & (1 << connection[CHILD])) == 0)
				&& !states[state | (1 << connection[CHILD])]) {

				dfs(connection[CHILD], sheepCount, wolfCount, state | (1 << connection[CHILD]));
			}
		}
	}
}
