import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		//n장의 카드 입력
		int n = scanner.nextInt();
		//n장의 카드 queue에 넣기
		Queue<Integer> cards = new LinkedList<>();
		for (int i = 1; i <= n; i++)
			cards.offer(i);
		
		int top;	//제일 위의 버리는 카드를 담는 변수
		while (!cards.isEmpty()) {	//큐가 비었으면 종료
			top = cards.poll();	//카드 하나를 버림
			if (cards.isEmpty()) {	//카드를 버린 후 큐가 비어있으면
				System.out.println(top);	//출력 후 종료
				break;
			}
			cards.offer(cards.poll());	//카드를 버린 후 제일 위에 있는 카드를 제일 밑으로 옮김
		}
		scanner.close();
	}
}
