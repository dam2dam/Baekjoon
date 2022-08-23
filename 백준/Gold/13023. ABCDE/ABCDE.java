	/*
	
	[13023] ABCDE
	BOJ 알고리즘 캠프에는 총 N명이 참가하고 있다. 사람들은 0번부터 N-1번으로 번호가 매겨져 있고, 일부 사람들은 친구이다.
	오늘은 다음과 같은 친구 관계를 가진 사람 A, B, C, D, E가 존재하는지 구해보려고 한다.
		A는 B와 친구다.
		B는 C와 친구다.
		C는 D와 친구다.
		D는 E와 친구다.
	위와 같은 친구 관계가 존재하는지 안하는지 구하는 프로그램을 작성하시오.
	
	1. 친구 관계를 인접 리스트로 저장한다
	2. 모든 사람을 검색하며 depth를 늘리고 친구 관계를 확인한다
	3. 조건에 맞는 친구 관계가 존재하면 종료한다
	
	*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N, M, answer;
	static int[] visit;
	static ArrayList<ArrayList<Integer>> friends;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

		// 입력1 : N:사람의 수, M:친구 관계의 수
		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());

		// 친구 관계를 저장할 리스트 생성
		friends = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < N; i++)
			friends.add(new ArrayList<>());

		for (int i = 0; i < M; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			// 친구 관계 저장
			int from = Integer.parseInt(stringTokenizer.nextToken());
			int to = Integer.parseInt(stringTokenizer.nextToken());
			friends.get(from).add(to);	// 서로 친구 ~ 이므로 이어줌
			friends.get(to).add(from);	// 서로 친구 ~
		}

		visit = new int[N+1];
		
		// 모든 사람을 검색하여 친구 관계인지 확인
		for (int i = 0; i < friends.size(); i++) {
			visit[i] = 1;
			searchFriends(i, 0);
			visit[i] = 0;
			
			// 조건에 맞는 친구 관계가 존재하면 종료
			if(answer == 1)
				break;
		}

		// 출력
		System.out.println(answer);

		bufferedReader.close();
	}

	private static void searchFriends(int number, int depth) {

		// 조건에 맞는 친구 관계가 존재하면 종료
		if (depth == 4) {
			answer = 1;
			return;
		}

		// 확인하려는 친구를 방문하며 depth+1
		for (int current : friends.get(number))
			if (visit[current] == 0) {
				visit[number] = 1;
				searchFriends(current, depth+1);
				visit[number] = 0;	// 검색하는 순서에 따라 친구 관계가 다르게 검색되므로 방문 체크를 풀어주며 검색
			}

	}
}