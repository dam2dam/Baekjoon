import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {
	
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringtokenizer;

		for (int tc = 1; tc <= 10; tc++) {
			
			int T = Integer.parseInt(bufferedReader.readLine());
			
			Deque<Integer> deque = new LinkedList<>();
			
			stringtokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int i = 0; i < 8; i++) {
				int input = Integer.parseInt(stringtokenizer.nextToken());
				deque.offer(input);
			}
			
			int current;
			int downNumber = 1;
			while (deque.peekLast() != 0) {
				if (downNumber > 5)
					downNumber -= 5;
				
				current = deque.poll();
				if(current - downNumber < 0) {
					deque.offer(0);
					break;
				}
				deque.offer(current - downNumber++);
			}
			
			System.out.printf("#%d ", tc);
			for(int i : deque)
				System.out.print(i +" ");
			System.out.println();
		}
		bufferedReader.close();
	}
	
}
