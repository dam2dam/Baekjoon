import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringtokenizer;

		for (int tc = 1; tc <= 10; tc++) {
			
			int dump = Integer.parseInt(bufferedReader.readLine());
			
			int[][] boxes = new int[100][2];
			
			stringtokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int i = 0; i < 100; i++) {
				boxes[i][0] = i;
				boxes[i][1] = Integer.parseInt(stringtokenizer.nextToken());
			}
			Arrays.sort(boxes, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					if(o1[1] == o2[1])
						return o1[0] < o2[0] ? -1 : o1[0] == o2[0] ? 0 : 1;
					return o1[1] < o2[1] ? -1 : o1[1] == o2[1] ? 0 : 1;
				}
			});
			
			while (dump > 0 && boxes[99][1]-boxes[0][1] > 1) {
				boxes[99][1]--;
				boxes[0][1]++;
				dump--;
				
				for(int i = 98; i > 0; i--) {
					if(boxes[i][1] > boxes[99][1]) {
						int temp = boxes[99][1];
						boxes[99][1] = boxes[i][1];
						boxes[i][1] = temp;
						break;
					}
				}
				for(int i = 1; i < 100; i++) {
					if(boxes[0][1] > boxes[i][1]) {
						int temp = boxes[0][1];
						boxes[0][1] = boxes[i][1];
						boxes[i][1] = temp;
						break;
					}
				}
				
			}

			System.out.printf("#%d %d\n", tc, boxes[99][1]-boxes[0][1]);
		}
		bufferedReader.close();
	}
	
}
