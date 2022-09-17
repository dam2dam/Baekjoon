
	/*
	
	[4013] [모의 SW 역량테스트] 특이한 자석
	판에는 4개의 자석이 놓여져 있었고, 각 자석은 8개의 ‘날’(튀어나온 곳)를 가지고 있다.
	자석의 각 날 마다 N 극 또는 S 극의 자성을 가지고 있다.
	이 특이한 자석은 1번부터 4번까지 판에 일렬로 배치되어 있고, 빨간색 화살표 위치에 날 하나가 오도록 배치되어 있다.
	심심한 선표는 이 특이한 자석을 가지고 놀아보니 신기한 규칙을 발견했다.
	임의의 자석을 1 칸씩 K 번 회전시키려고 해보니,
	하나의 자석이 1 칸 회전될 때, 붙어 있는 자석은 서로 붙어 있는 날의 자성과 다를 경우에만 인력에 의해 반대 방향으로 1 칸 회전된다.
	이를 신기하게 생각한 선표는 무작위로 자석을 돌렸을 때, 모든 회전이 끝난 후, 아래와 같은 방법으로 점수를 계산을 하고자 한다.
	- 1 번 자석에서 빨간색 화살표 위치에 있는 날의 자성이 N 극이면 0 점, S 극이면 1 점을 획득한다.
	- 2 번 자석에서 빨간색 화살표 위치에 있는 날의 자성이 N 극이면 0 점, S 극이면 2 점을 획득한다.
	- 3 번 자석에서 빨간색 화살표 위치에 있는 날의 자성이 N 극이면 0 점, S 극이면 4 점을 획득한다.
	- 4 번 자석에서 빨간색 화살표 위치에 있는 날의 자성이 N 극이면 0 점, S 극이면 8 점을 획득한다.
	4 개 자석의 자성 정보와 자석을 1 칸씩 K 번 회전시키려고 할 때,
	K 번 자석을 회전시킨 후 획득하는 점수의 총 합을 출력하는 프로그램을 작성하라.
	
	정답은 모든 자석의 회전이 끝난 후 획득한 점수의 총 합이다.
	
	1. 4개 자석의 각각 8개 날의 자성정보를 입력받아 LinkedList<Integer> 배열에 저장한다
	2. 회전하려는 자석부터 큐에 넣고 양쪽에 위치하는 자석이 회전 가능한지 확인하며 가능하다면 큐에 넣는다
	3. 자석의 방향 정보에 따라 각 자석의 LinkedList 인덱스로 poll하여 이동 한다
	4. 모든 자석의 회전이 끝나면 점수를 계산하여 출력한다
	
	*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	final static int CLOCKWISE = 1;
	final static int COUNTERCLOCKWISE = -1;
	final static int SOUTH_POLE = 1;

	static int T, K, score;
	static boolean[] visit;
	static LinkedList<Integer>[] magnets;
	static Queue<Magnet> queue;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		// 입력1 : 테스트 케이스의 수
		T = Integer.parseInt(bufferedReader.readLine());

		for (int tc = 1; tc <= T; tc++) {
			
			// 입력2 : 자석을 회전시키는 횟수
			K = Integer.parseInt(bufferedReader.readLine());

			// 입력3 : 4개 자석의 각각 8개 날의 자성정보를 입력받아 LinkedList<Integer> 배열에 저장
			magnets = new LinkedList[5];	// 자석 index: 1~4
			for (int i = 1; i <= 4; i++) {
				magnets[i] = new LinkedList<>();
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				for (int j = 0; j < 8; j++) {
					// index 0 : 화살표 위치, index 1~ : 시계 방향으로 하나씩 저장
					magnets[i].add(Integer.parseInt(stringTokenizer.nextToken()));
				}
			}
			
			for (int i = 0; i < K; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				
				// 입력4 : 자석의 번호와 회전방향 (1: 시계방향, -1: 반시계방향)
				int number = Integer.parseInt(stringTokenizer.nextToken());
				int direction = Integer.parseInt(stringTokenizer.nextToken());

				visit = new boolean[5];	// 자석 확인 여부를 저장
				queue = new ArrayDeque<Magnet>();	// 회전가능 여부를 확인하기 위해 자석을 임시 저장할 Queue
				queue.offer(new Magnet(number, direction));	// 회전 시키려는 대상 자석을 enqueue

				while (queue.isEmpty() == false) {
					Magnet currentMagnet = queue.poll();
					
					// 이미 확인했으면 continue
					if (visit[currentMagnet.number] == true)
						continue;

					visit[currentMagnet.number] = true;

					int leftMagnetNumber = currentMagnet.number-1;	// 현재 자석의 왼쪽 자석
					int rightMagnetNumber = currentMagnet.number+1;	// 현재 자석의 오른쪽 자석
					
					// 왼쪽 자석의 2번 날과 현재 자석의 6번 날의 자성이 같지 않으면 
					if (0 < leftMagnetNumber && visit[leftMagnetNumber] == false && 
							magnets[leftMagnetNumber].get(2) != magnets[currentMagnet.number].get(6)) {
						// 현재 자석과 반대 방향으로 돌도록 queue에 저장
						queue.offer(new Magnet(leftMagnetNumber, -currentMagnet.direction));
					}
					
					// 현재 자석의 6번 날과 오른쪽 자석의 2번 날의 자성이 같지 않으면 
					if (rightMagnetNumber <= 4 && visit[rightMagnetNumber] == false && 
							magnets[currentMagnet.number].get(2) != magnets[rightMagnetNumber].get(6)) {
						// 현재 자석과 반대 방향으로 돌도록 queue에 저장
						queue.offer(new Magnet(rightMagnetNumber, -currentMagnet.direction));
					}
					
					// 현재 자석의 방향이 시계 방향이면 
					if (currentMagnet.direction == CLOCKWISE) {
						// 화살표가 위치하는 날의 바로 왼쪽 날(마지막 인덱스)을 
						int lastPole = magnets[currentMagnet.number].pollLast();
						// 화살표 위치(처음 인덱스)로 이동
						magnets[currentMagnet.number].offerFirst(lastPole);
					}
					
					// 현재 자석의 방향이 반시계 방향이면 
					else if (currentMagnet.direction == COUNTERCLOCKWISE){
						// 화살표가 위치하는 날의 바로 오른쪽 날(처음 인덱스)을 
						int firstPole = magnets[currentMagnet.number].pollFirst();
						// 화살표 위치(마지막 인덱스)로 이동
						magnets[currentMagnet.number].offerLast(firstPole);
					}
				}
			}
			score = 0;
			for (int i = 0; i < 4; i++)
				// S극이면 점수 획득
				if (magnets[i+1].get(0) == SOUTH_POLE)
					// 자석번호-1(= 각 자석의 인덱스)에 따라 2의 거듭제곱의 점수를 획득함
					// 1번 - 1점, 2번 - 2점, 3번 - 4점, 4번 - 8점
					score += Math.pow(2, i);

			// 출력 : 모든 자석의 회전이 끝난 후 획득한 점수의 총 합
			System.out.printf("#%d %d\n", tc, score);

		} // end testcase
		
		bufferedReader.close();
	}		

	// 1 - 2 == 2 - 6 , 2 - 2 == 3 - 6 , 3 - 2 == 4 - 6

	static class Magnet {
		int number, direction;

		public Magnet(int number, int direction) {
			this.number = number;
			this.direction = direction;
		}
	}

}

