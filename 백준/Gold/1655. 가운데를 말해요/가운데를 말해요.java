import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	
	static int N, number;
	static PriorityQueue<Integer> frontPriorityQueue, backPriorityQueue;
	static int[] answers;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
		frontPriorityQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
		backPriorityQueue = new PriorityQueue<>();
		N = Integer.parseInt(bufferedReader.readLine());
		answers = new int[N];
		for (int i = 0; i < N; i++) {
			number = Integer.parseInt(bufferedReader.readLine());
			if (frontPriorityQueue.isEmpty())
				frontPriorityQueue.offer(number);
			
			else if (backPriorityQueue.isEmpty()) {
				if (frontPriorityQueue.peek() > number) {
					backPriorityQueue.offer(frontPriorityQueue.poll());
					frontPriorityQueue.offer(number);
				}
				else
					backPriorityQueue.offer(number);					
			}
			
			else if (backPriorityQueue.peek() > number)
				frontPriorityQueue.offer(number);
		
			else
				backPriorityQueue.offer(number);

			if (frontPriorityQueue.size() < backPriorityQueue.size())
				frontPriorityQueue.offer(backPriorityQueue.poll());
			
			else if (frontPriorityQueue.size() > backPriorityQueue.size()+1)
				backPriorityQueue.offer(frontPriorityQueue.poll());
			
			answers[i] = frontPriorityQueue.peek();
			
		}
		for (int answer : answers) {
			System.out.println(answer);
		}
		bufferedReader.close();
	}
}
