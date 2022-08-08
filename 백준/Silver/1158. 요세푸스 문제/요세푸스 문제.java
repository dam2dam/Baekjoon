	/*
	
	[1158] 요세푸스 문제
	1번부터 N번까지 N명의 사람이 원을 이루면서 앉아있고, 양의 정수 K(≤ N)가 주어진다.
	이제 순서대로 K번째 사람을 제거한다.
	이 과정은 N명의 사람이 모두 제거될 때까지 계속된다.
	원에서 사람들이 제거되는 순서를 (N, K)-요세푸스 순열이라고 한다.
	
	1. 큐에 N명의 사람을 넣는다
	2. 제거할 순번이 올 때까지 사람을 맨 뒷 순서로 넣고 순환한다.
	3. 제거할 순번이 오면 출력하고 2,3을 반복한다.
	4. 큐가 비면 종료한다.
    
	*/

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		// 입력	N:인원 수, K:제거할 순번 (1 ≤ K ≤ N ≤ 5,000)
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		
		// 큐에 인원수대로 입력
		Queue<Integer> josephus = new LinkedList<Integer>();
		for (int i = 1; i <= n; i++)
			josephus.add(i);
		
		// 출력
		System.out.print("<");
		int num;	// 제거한 순번을 담을 변수
		while (!josephus.isEmpty()) {
			for (int i = 0; i < k-1; i++) {	// 제거할 순번의 앞(k-1번째) 까지는 다시 뒷 순서로 보낸다
				num = josephus.poll();
				josephus.offer(num);
			}
			num = josephus.poll();	// 제거할 순번이 오면 제거하고 출력한다
			System.out.print(num);
			if (!josephus.isEmpty())
				System.out.print(", ");
		}
		System.out.println(">");
        scanner.close();
	}
}
