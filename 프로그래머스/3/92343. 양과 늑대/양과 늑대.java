class Solution {
    
    static final int SHEEP = 0;
	static final int PARENT = 0;
	static final int CHILD = 1;

	static int maxSheepCount;
	static boolean[] visited;
    
    public int solution(int[] info, int[][] edges) {
        visited = new boolean[info.length];
        
		visited[0] = true;
		dfs(0, 0, 0, info, edges);
        
        return maxSheepCount;
    }
    
	/**
	 * 노드를 이동하며 양을 모으는 메서드
	 * @param index 현재 노드의 번호
	 * @param sheepCount 현재까지 모은 양의 수
	 * @param wolfCount 현재까지 따라온 늑대의 수
	 * @param info 노드 정보
	 * @param edges 노드 연결 관계 정보
	 */
    static void dfs(int index, int sheepCount, int wolfCount, int[] info, int[][] edges) {
        // 현재 노드에 양이 있다면
		if (info[index] == SHEEP) {
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
		for (int[] edge : edges) {
            // 이미 방문했고(부모노드) 그와 연결된 노드(자식노드)를 방문하지 않았던 곳이라면 (이어진 길)
			if (visited[edge[PARENT]] && !visited[edge[CHILD]]) {
				visited[edge[CHILD]] = true;
				dfs(edge[CHILD], sheepCount, wolfCount, info, edges);
				visited[edge[CHILD]] = false;
			}
		}
	}
}