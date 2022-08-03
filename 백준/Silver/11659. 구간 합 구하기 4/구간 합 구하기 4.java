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
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		int[] nums = new int[n+1]; //인덱스를 사용하기 위해 0번째 숫자는 비워둠
		int[] dp = new int[n+1];
		for (int i = 1; i <= n; i++) {
			nums[i] = Integer.parseInt(stringTokenizer.nextToken());	//입력 저장
			dp[i] = dp[i-1] + nums[i];	//누적합 저장
		}
		
		//합을 구해야 하는 구간 입력
		for (int k = 0; k < m; k++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int i = Integer.parseInt(stringTokenizer.nextToken());
			int j = Integer.parseInt(stringTokenizer.nextToken());
			
			System.out.println(dp[j] - dp[i-1]); // sum = j번째 수까지의 합 - (i-1)번째 수까지의 합
		}	
		bufferedReader.close();
	}
}
