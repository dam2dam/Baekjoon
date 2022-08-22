
	/*
	
	[1238] [S/W 문제해결 기본] 10일차 - Contact
	비상연락망과 연락을 시작하는 당번에 대한 정보가 주어질 때, 가장 나중에 연락을 받게 되는 사람 중 번호가 가장 큰 사람을 구하시오
	연락 인원은 최대 100명이며, 부여될 수 있는 번호는 1이상, 100이하이다.
	단, 예시에서 5번이 존재하지 않듯이 중간 중간에 비어있는 번호가 있을 수 있다.
	한 명의 사람이 다수의 사람에게 연락이 가능한 경우 항상 다자 간 통화를 통해 동시에 전달한다.
	연락이 퍼지는 속도는 항상 일정하다 (전화를 받은 사람이 다음사람에게 전화를 거는 속도는 동일).
	비상연락망 정보는 사전에 공유되며 한 번 연락을 받은 사람에게 다시 연락을 하는 일은 없다.
	
	1. 비상연락망 그래프를 입력받아 인접 리스트에 저장한다
	2. 연락을 시작하는 당번부터 큐에 넣고 연락을 시작한다
	2. 방문 체크 배열에 연락받은 순서를 기록함과 동시에 방문을 체크한다
	3. 가장 나중에 연락받은 사람의 순서를 기록했다가, 방문 체크 배열에서 그 순서를 가진 인덱스 중 가장 큰 값을 찾는다
	
	*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	final static int INPUT_MAX = 100;
	static int length, start;
	static ArrayList<ArrayList<Integer>> graph;
	static int[] visit;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		
		// 테스트 케이스 10개
		for(int tc = 1; tc <= 10; tc++) {
			
			// 입력1 : length:입력받는 데이터의 길이, start:연락의 시작점
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			length = Integer.parseInt(stringTokenizer.nextToken());
			start = Integer.parseInt(stringTokenizer.nextToken());
			
			// 그래프 ArrayList 초기화
			graph = new ArrayList<ArrayList<Integer>>();
			for (int i = 0; i < INPUT_MAX+1; i++)
				graph.add(new ArrayList<Integer>());
			
			// 입력2 : from:연락하는 사람, to:연락받는 사람 을 입력받아 ArrayList에 저장
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int i = 0; i < length/2; i++) {
				int from = Integer.parseInt(stringTokenizer.nextToken());
				int to = Integer.parseInt(stringTokenizer.nextToken());
				graph.get(from).add(to);
			}
			
			// 방문과 연락받은 순서를 저장하는 배열
			visit = new int[INPUT_MAX+1];
			
			// 연락을 시작하는 당번인 start부터 시작
			Queue<Integer> queue = new LinkedList<>();
			queue.offer(start);
			visit[start] = 1;	// 연락 순서를 기록하여 방문 체크
			
			int max = 0;
			while (queue.isEmpty() == false) {
				int currentListIndex = queue.poll();
				
				for(int i = 0; i < graph.get(currentListIndex).size(); i++) {
					int targetIndex = graph.get(currentListIndex).get(i);
					
					// 연락을 하려는 대상이 아직 연락을 받지 않았다면
					if (visit[targetIndex] == 0) {
						queue.offer(targetIndex);
						visit[targetIndex] = visit[currentListIndex] + 1;	// 이전에 연락받은 사람보다 +1로 순서 기록
						max = Math.max(max, visit[targetIndex]);	// 가장 나중에 연락받은 사람의 순서 기록
					}
				}
			}
			int answer = 0;
			for(int i = 0; i < visit.length; i++)
				// 가장 나중에 연락받은 사람의 순서를 찾으면 갱신 (반복하며 최댓값이 갱신되므로 가장 숫자가 큰(인덱스가 큰)사람이 답이 됨)
				if(visit[i] == max)
					answer = i;
			
			System.out.printf("#%d %d\n", tc, answer);
			
		}
		
	}

}
