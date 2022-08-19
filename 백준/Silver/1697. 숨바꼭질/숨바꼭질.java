import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, K;
	static boolean[] visit;
	final static int MIN_POINT = 0;
	final static int MAX_POINT = 100000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		
		N = Integer.parseInt(stringTokenizer.nextToken());
		K = Integer.parseInt(stringTokenizer.nextToken());
		
		visit = new boolean[MAX_POINT+1];
		move(N);
		
	}

	static void move(int point) {
		Queue<Subin> queue = new LinkedList<Subin>();
		queue.offer(new Subin(point, 0));
		visit[point] = true;
		
		Subin current;
		while(queue.isEmpty() == false) {
			current = queue.poll();
			
			if (current.point == K) {
				System.out.println(current.time);
				return;
			}
			
			if (current.point - 1 >= MIN_POINT && visit[current.point - 1] == false) {
				queue.offer(new Subin(current.point-1, current.time+1));
				visit[current.point - 1] = true;
			}
			
			if (current.point + 1 <= MAX_POINT && visit[current.point + 1] == false) {
				queue.offer(new Subin(current.point+1, current.time+1));
				visit[current.point + 1] = true;
			}
			
			if (current.point * 2 <= MAX_POINT && visit[current.point * 2] == false) {
				queue.offer(new Subin(current.point*2, current.time+1));
				visit[current.point * 2] = true;
			}
			
		}
	}
	
	static class Subin {
		int point;
		int time;
		public Subin(int point, int time) {
			super();
			this.point = point;
			this.time = time;
		}
	}
}
