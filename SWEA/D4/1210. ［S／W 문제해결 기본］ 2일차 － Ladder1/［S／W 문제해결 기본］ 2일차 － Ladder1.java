import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int y, x;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringtokenizer;

		for (int tc = 1; tc <= 10; tc++) {
			
			int T = Integer.parseInt(bufferedReader.readLine());
			
			int[][] ladder = new int[100][100];
			for (int i = 0; i < 100; i++) {
				stringtokenizer = new StringTokenizer(bufferedReader.readLine());
				for (int j = 0; j < 100; j++) {
					ladder[i][j] = Integer.parseInt(stringtokenizer.nextToken());
					if (ladder[i][j] == 2) {
						y = i;
						x = j;
					}
				}
			}
			boolean[][] visit = new boolean[100][100];
			
			while (y > 0) {

				if(x-1 >= 0 && ladder[y][x-1] == 1 && visit[y][x-1] == false) {
					visit[y][x-1] = true;
					x--;
					continue;
				}
				
				if(x+1 < 100 && ladder[y][x+1] == 1 && visit[y][x+1] == false) {
					visit[y][x+1] = true;
					x++;
					continue;
				}
				
				if(ladder[y-1][x] == 1 && visit[y-1][x] == false) {
					visit[y-1][x] = true;
					y--;
				}
			}
			
			System.out.printf("#%d %d\n", tc, x);
		}
		bufferedReader.close();
	}
	
}
