class Solution {
    
    static final int CONNECTED = 1;
    static int[] parent;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                if (computers[i][j] == CONNECTED) {
                    connect(i, j);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (find(i) == i){
                answer++;
            }
        }
        return answer;
    }
    
    static void connect (int computer1, int computer2) {
        int parent1 = find(computer1);
        int parent2 = find(computer2);
        
        if (parent1 != parent2) {
            parent[parent2] = parent1;
        }
    }
    
    static int find (int computer) {
        if (parent[computer] == computer) {
            return computer;
        }
        return parent[computer] = find(parent[computer]);
    }
}