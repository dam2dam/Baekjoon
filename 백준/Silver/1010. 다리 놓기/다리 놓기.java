	/*
	
	[1010] 다리 놓기
	도시를 동쪽과 서쪽으로 나누는 큰 일직선 모양의 강이 흐르고 있다.
	강 주변에서 다리를 짓기에 적합한 곳을 사이트라고 한다. 
	재원이는 서쪽에는 N개의 사이트가 있고 동쪽에는 M개의 사이트가 있다는 것을 알았다. (N ≤ M)
	재원이는 서쪽의 사이트와 동쪽의 사이트를 다리로 연결하려고 한다. (이때 한 사이트에는 최대 한 개의 다리만 연결될 수 있다.) 
	재원이는 다리를 최대한 많이 지으려고 하기 때문에 서쪽의 사이트 개수만큼 (N개) 다리를 지으려고 한다. 
	다리끼리는 서로 겹쳐질 수 없다고 할 때 다리를 지을 수 있는 경우의 수를 구하는 프로그램을 작성하라.
	
	각 테스트 케이스에 대해 주어진 조건하에 다리를 지을 수 있는 경우의 수를 출력한다.
	
	1. 서쪽 사이트의 개수만큼 다리를 연결하려 하고, 한 사이트에는 한 개의 다리만 연결될 수 있으며, 다리끼리는 서로 겹쳐질 수 없으므로 
		동쪽 사이트의 개수에서 서쪽 사이트의 개수만큼 뽑는 조합과 같다
	2. 다리의 최대 개수만큼 2차원 배열을 만들어 모든 조합의 결과를 저장해놓는다
	3. 입력받은 다리의 사이트 수에 해당하는 경우의 수를 배열에서 찾아 출력한다
	
	*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int r;
	static int count;

	public static void main(String[] args) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		// 입력1 : 테스트 케이스의 수
		int T = Integer.parseInt(bufferedReader.readLine());
		
		// 가능한 사이트의 조합을 모두 저장한다
		int[][] bridgeCombinations = new int[31][31];
		for (int i = 0; i <= 30; i++) {
			for (int j = 0; j <= i; j++) {
				if (i == j || j == 0)
					bridgeCombinations[i][j] = 1;
				else
					bridgeCombinations[i][j] = bridgeCombinations[i-1][j-1] + bridgeCombinations[i-1][j];
			}
		}

		for (int tc = 1; tc <= T; tc++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			// 입력2 : r:서쪽 사이트의 개수, n:동쪽 사이트의 개수
			r = Integer.parseInt(stringTokenizer.nextToken());
			n = Integer.parseInt(stringTokenizer.nextToken());
			
			// 출력 : 입력된 사이트의 개수에 해당하는 조합을 찾아 출력한다
			System.out.println(bridgeCombinations[n][r]);
		}
		bufferedReader.close();
	}
}
