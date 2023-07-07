import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static Plan[] plan;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		
		N = Integer.parseInt(bufferedReader.readLine());
		
		plan = new Plan[N+1];
		for (int i = 1; i <= N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int time = Integer.parseInt(stringTokenizer.nextToken());
			int pay = Integer.parseInt(stringTokenizer.nextToken());
			
			plan[i] = new Plan(time, pay);
		}

//		System.out.println(Arrays.toString(plan));

		dp = new int[N+2];
		int answer = 0;
		for (int today = N; today > 0; today--) {
			int time = plan[today].time;
			int pay = plan[today].pay;
			
			int deadline = today + time;
			if (deadline > N+1) {
				dp[today] = dp[today + 1];
			}
			else {
				dp[today] = Math.max(dp[today+1], pay + dp[deadline]);
				answer = Math.max(dp[today], answer);
			}
		}
//		System.out.println(Arrays.toString(dp));
		System.out.println(answer);
		
		bufferedReader.close();
	}
	
	
	
	static class Plan {
		int time, pay;

		public Plan(int time, int pay) {
			super();
			this.time = time;
			this.pay = pay;
		}

		@Override
		public String toString() {
			return "Plan [time=" + time + ", pay=" + pay + "]";
		}
		
	}
}
