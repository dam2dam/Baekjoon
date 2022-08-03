import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

		//수의 개수 n, 합을 구해야 하는 횟수 m
		int n = Integer.parseInt(stringTokenizer.nextToken());
		int m = Integer.parseInt(stringTokenizer.nextToken());
		
		//n개의 수 입력
		int[][] nums = new int[n+1][n+1]; //인덱스를 사용하기 위해 0번째 숫자는 비워둠
		int[][] dp = new int[n+1][n+1];
		
		for (int i = 1; i <= n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 1; j <= n; j++) {
				nums[i][j] = Integer.parseInt(stringTokenizer.nextToken()); //입력 저장
				dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + nums[i][j]; //누적 합 저장
			}
		}
			
		//합을 구해야 하는 구간 입력 & 출력
		for (int k = 0; k < m; k++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int x1 = Integer.parseInt(stringTokenizer.nextToken());
			int y1 = Integer.parseInt(stringTokenizer.nextToken());
			int x2 = Integer.parseInt(stringTokenizer.nextToken());
			int y2 = Integer.parseInt(stringTokenizer.nextToken());
			
			int sum = dp[x2][y2] - dp[x2][y1-1] - dp[x1-1][y2] + dp[x1-1][y1-1];
			System.out.println(sum);
		}
		bufferedReader.close();
	}
}
