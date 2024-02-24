import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		char[] string1 = bufferedReader.readLine().toCharArray();
		char[] string2 = bufferedReader.readLine().toCharArray();
		int r = string1.length;
		int c = string2.length;

		int[][] dp = new int[r + 1][c + 1];
		for (int i = 1; i <= r; i++) {
			for (int j = 1; j <= c; j++) {
				// 현재 비교하는 두 문자가 같은 문자이면, 이전까지 구한 부분 문자열에 + 1
				if (string1[i - 1] == string2[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					// 현재 비교하는 두 문자의 직전까지 구한 부분 문자열 중, 더 큰 수
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		System.out.println(dp[r][c]);
		bufferedReader.close();
	}
}
