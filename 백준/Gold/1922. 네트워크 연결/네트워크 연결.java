	/*
	
	[1922] 네트워크 연결
	도현이는 컴퓨터와 컴퓨터를 모두 연결하는 네트워크를 구축하려 한다. 
	모두가 자료를 공유하기 위해서는 모든 컴퓨터가 연결이 되어 있어야 한다. 
	(a와 b가 연결이 되어 있다는 말은 a에서 b로의 경로가 존재한다는 것을 의미한다. 
			a에서 b를 연결하는 선이 있고, b와 c를 연결하는 선이 있으면 a와 c는 연결이 되어 있다.)
	이제 각 컴퓨터를 연결하는데 필요한 비용이 주어졌을 때 모든 컴퓨터를 연결하는데 필요한 최소비용을 출력하라.
	모든 컴퓨터를 연결할 수 없는 경우는 없다.
	
	모든 컴퓨터를 연결하는데 필요한 최소비용을 첫째 줄에 출력한다.
	
	1. 모든 컴퓨터의 경로와 비용을 인접 리스트에 저장하고 연결을 시작할 컴퓨터를 우선순위 큐에 넣는다
	2. 현재 연결한 컴퓨터와 이어져있는 컴퓨터 중 최소 비용을 가진 컴퓨터가 연결되어 있지 않으면 연결한다 : connect()
	3. 반복하며 연결하고 최소 비용을 누적한 후 모든 컴퓨터가 연결되면 최소 비용을 출력한다 
	
	*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, computerA, computerB, cost, minCostSum, computerCount;
	static ArrayList<Computer>[] network;
	static PriorityQueue<Computer> priorityQueue;
	static boolean[] visit;

	public static void main(String[] args) throws IOException{
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		
		// 입력1 : N:컴퓨터의 수, M:연결할 수 있는 선의 수
		N = Integer.parseInt(bufferedReader.readLine());
		M = Integer.parseInt(bufferedReader.readLine());
		
		// 인접 리스트 배열 생성
		network = new ArrayList[N+1];
		for (int i = 0; i < N; i++)
			network[i+1] = new ArrayList<>();
		
		for (int i = 0; i < M; i++) {
			
			// 입력2 : computerA와 computerB를 연결하는데 드는 비용 cost를 인접리스트로 저장
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			computerA = Integer.parseInt(stringTokenizer.nextToken());
			computerB = Integer.parseInt(stringTokenizer.nextToken());
			cost = Integer.parseInt(stringTokenizer.nextToken());
			
			network[computerA].add(new Computer(computerB, cost));
			network[computerB].add(new Computer(computerA, cost));
		}
		
		priorityQueue = new PriorityQueue<>();
		visit = new boolean[N+1];
		
		priorityQueue.offer(new Computer(1, 0));	// 연결을 시작할 첫번째 컴퓨터를 우선순위 큐에 넣음
		
		// 연결 시작
		connect();
		
		// 출력
		System.out.println(minCostSum);
		
		bufferedReader.close();
	}
	
	static void connect() {
		
		minCostSum = 0;	// 최소 비용을 누적할 변수
		computerCount = 0;	// 연결한 컴퓨터의 개수
		
		while (priorityQueue.isEmpty() == false) {
			Computer current = priorityQueue.poll();
			
			// 현재 고른 컴퓨터가 이미 연결되어 있으면 넘어감
			if (visit[current.number] == true)
				continue;
			
			// 연결하고 비용 누적
			visit[current.number] = true;
			computerCount++;
			minCostSum += current.cost;
			
			// 모든 컴퓨터가 연결되면 종료
			if (computerCount == N)
				return;
			
			// 현재 연결한 컴퓨터와 연결된 컴퓨터를 모두 우선순위 큐에 넣음
			for (Computer computer : network[current.number])
				priorityQueue.offer(computer);
		}
		
	}

	static class Computer implements Comparable<Computer>{
		int number, cost;

		public Computer(int number, int cost) {
			this.number = number;
			this.cost = cost;
		}

		@Override
		public int compareTo(Computer computer) {
			return this.cost - computer.cost;
		}
	}
	
}
