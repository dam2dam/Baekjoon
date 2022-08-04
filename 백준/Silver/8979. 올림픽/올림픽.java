import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		String[] s = bReader.readLine().split(" ");
		
		int n = Integer.parseInt(s[0]);
		int k = Integer.parseInt(s[1]);
		
		int[][] olympic = new int[n][4];
		for (int i = 0; i < n; i++) {
			s = bReader.readLine().split(" ");
			olympic[i] = Arrays.stream(s).mapToInt(Integer::parseInt).toArray();
		}
		
		Arrays.sort(olympic, new Comparator<int[]>() {
			
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1] == o2[1] && o1[2] == o2[2])
					return o2[3] - o1[3];
				else if (o1[1] == o2[1])
					return o2[2] - o1[2];
				else
					return o2[1] - o1[1]; 
			}
		});
		
		int jointCount = 0;
		int indexOfK = 0;
		for(int i = 0; i < n; i++)
			if(olympic[i][0] == k)
				indexOfK = i;
		
		for (int i = 0; i < n; i++)
			if(olympic[indexOfK][1] == olympic[i][1] && olympic[indexOfK][2] == olympic[i][2] && olympic[indexOfK][3] == olympic[i][3])
				jointCount++;
		
		for (int i = 0; i < n; i++) {
			if (jointCount > 1) {
				if(olympic[indexOfK][1] == olympic[i][1] && olympic[indexOfK][2] == olympic[i][2] && olympic[indexOfK][3] == olympic[i][3]) {
					System.out.println(i+1);
					break;
				}
			}
			else {
				System.out.println(indexOfK+1);
				break;
			}
		}
	}
}
