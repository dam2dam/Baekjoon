import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    
	static int T, N, totalCount;
    
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(bufferedReader.readLine());
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(bufferedReader.readLine());
			
			totalCount = 0;
			search(0);
			System.out.println(totalCount);
		}
	}

	static void search(int sum) {
		if (sum >= N) {
			if (sum == N) 
				totalCount++;
			return;
		}
		search(sum+1);
		search(sum+2);
		search(sum+3);
	}
	
}
