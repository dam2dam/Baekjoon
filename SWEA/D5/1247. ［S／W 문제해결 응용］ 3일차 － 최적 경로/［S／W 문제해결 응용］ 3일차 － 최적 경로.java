	
	/*
	
	[1247] [S/W 문제해결 응용] 3일차 - 최적 경로
	삼성전자의 서비스 기사인 김대리는 회사에서 출발하여 냉장고 배달을 위해 N명의 고객을 방문하고 자신의 집에 돌아가려한다.
	회사에서 출발하여 N명의 고객을 모두 방문하고 집으로 돌아오는 경로 중 가장 짧은 것을 찾으려 한다.
	회사와 집의 좌표가 주어지고, 2명에서 10명 사이의 고객 좌표가 주어질 때,
	회사에서 출발해서 이들을 모두 방문하고 집에 돌아가는 경로 중 총 이동거리가 가장 짧은 경로를 찾는 프로그램을 작성하라.
	
	각 줄은 ‘#x’로 시작하고 공백을 하나 둔 다음 최단 경로의 이동거리를 기록한다. 여기서 x는 테스트 케이스의 번호다.
	
	1. 회사에서 출발해 고객을 방문하고 집으로 가는 모든 경로를 순열로 구현한다
	2. 순열을 위한 임시 배열을 만들어 시작은 회사의 좌표, 끝은 집의 좌표로 둔다
	3. 고객의 좌표 리스트에서 순열 임시 배열에 좌표를 저장하며 순열을 만든다
	4. 순열이 완성되면 이동거리를 구하고 최솟값인지 비교하여 갱신하여 최단 경로의 이동거리를 출력한다
	
	*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	static int n;
	static int startX, startY, endX, endY;
	static List<Customer> customers;
	static int[][] temp;
	static boolean[] check;
	static int sum, min;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		// 입력1 : 테스트 케이스의 수
		int T = Integer.parseInt(bufferedReader.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			// 입력2 : 고객의 수
			n = Integer.parseInt(bufferedReader.readLine());
			
			// 입력3 : start:회사의 좌표, end:집의 좌표
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			startX = Integer.parseInt(stringTokenizer.nextToken());
			startY = Integer.parseInt(stringTokenizer.nextToken());
			endX = Integer.parseInt(stringTokenizer.nextToken());
			endY = Integer.parseInt(stringTokenizer.nextToken());
			
			// 입력4 : 고객 좌표를 입력받아 List에 저장
			customers = new ArrayList<Customer>();
			int x, y;
			while (stringTokenizer.hasMoreTokens()) {
				x = Integer.parseInt(stringTokenizer.nextToken());
				y = Integer.parseInt(stringTokenizer.nextToken());
				customers.add(new Customer(x, y));
			}
			
			// 순열을 위한 임시 배열
			temp = new int[customers.size()+2][2];

			// 순열의 시작:회사의 좌표, 끝:집의 좌표
			temp[0][0] = startX;
			temp[0][1] = startY;
			temp[temp.length-1][0] = endX;
			temp[temp.length-1][1] = endY;
			// 방문체크 배열
			check = new boolean[customers.size()];
			
			sum = 0;
			min = Integer.MAX_VALUE;
			// 방문 시작
			visitCustomer(1);
			System.out.printf("#%d %d\n", tc, min);
		}
	}
	
	static void visitCustomer(int index) {
		
		// 순열이 완성되면 경로의 길이를 구해서 최단 거리인지 비교한다
		if (index == temp.length-1) {
			sum = 0;
			int x1, y1, x2, y2;
			for (int i = 1; i < temp.length; i++) {
				x1 = temp[i-1][0];
				y1 = temp[i-1][1];
				x2 = temp[i][0];
				y2 = temp[i][1];
				sum += Math.abs(x2 - x1) + Math.abs(y2 - y1);
			}
			min = Math.min(min, sum);
			return;
		}
		
		for (int i = 0; i < customers.size(); i++) {
			if (check[i] == false) {	// 방문하지 않았으면
				// 임시 배열에 좌표의 정보를 저장
				temp[index][0] = customers.get(i).x;
				temp[index][1] = customers.get(i).y;
				check[i] = true;
				visitCustomer(index+1);	// 다음 자리 순열 만들기
				check[i] = false;
			}
		}
	}
	
	static class Customer {
		int x;
		int y;
		public Customer(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

}
