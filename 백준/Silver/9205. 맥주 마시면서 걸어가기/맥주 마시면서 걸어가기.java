import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	final static String O = "happy";
	final static String X = "sad";
	
	static int T, N;
	static List<Point> convenienceStores;
	static Point sanggeun = new Point();
	static Point rockFestival = new Point();

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		
		// 입력1 : 테스트 케이스의 수
		T = Integer.parseInt(bufferedReader.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			// 입력2 : 맥주를 파는 편의점의 수
			N = Integer.parseInt(bufferedReader.readLine());
			
			// 입력3 : 상근이네 집의 좌표
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int r = Integer.parseInt(stringTokenizer.nextToken());
			int c = Integer.parseInt(stringTokenizer.nextToken());
			sanggeun.r = r;
			sanggeun.c = c;
			
			// 입력4 : 편의점의 좌표를 입력받아 convenienceStores리스트에 저장
			convenienceStores = new ArrayList<Point>();
			for (int i = 0; i < N; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				r = Integer.parseInt(stringTokenizer.nextToken());
				c = Integer.parseInt(stringTokenizer.nextToken());
				convenienceStores.add(new Point(r, c));
			}
			
			// 입력5 : 펜타포트 락 페스티벌의 좌표
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			r = Integer.parseInt(stringTokenizer.nextToken());
			c = Integer.parseInt(stringTokenizer.nextToken());
			rockFestival.r = r;
			rockFestival.c = c;
		
			// 출력 : 페스티벌에 갈 수 있으면 "happy", 중간에 맥주가 바닥나서 더 이동할 수 없으면 "sad"
			if (goFestival() == true) 
				System.out.println(O);
			else
				System.out.println(X);
			
		}
		bufferedReader.close();
		
	}
	
	/**
	 * 페스티벌 도착 가능 여부를 탐색하는 함수
	 * @return 도착 가능 여부
	 */
	static boolean goFestival() {
		Queue<Point> queue = new ArrayDeque<Point>();
		
		// 상근이네 집 좌표 enqueue
		queue.offer(sanggeun);
		
		while (queue.isEmpty() == false) {
			Point current = queue.poll();
			
			// 집이나 편의점으로부터 1000m 이내에 페스티벌이 열리는 곳이 있다면 return true
			if (distance(current, rockFestival) <= 1000)
				return true;
			
			// 편의점의 좌표 탐색
			for (int i = 0; i < convenienceStores.size(); i++) {
				Point next = convenienceStores.get(i);

				// 현재 위치로부터 1000m 이내에 다음 편의점이 있다면 enqueue
				if (distance(current, next) <= 1000) {
					queue.offer(next);
					// 방문한 편의점은 편의점 리스트에서 제거
					convenienceStores.remove(i);
				}
			}
		}
		// 도착하지 못했다면 return false
		return false;
		
	}
	
	/**
	 * 맨해튼 거리를 구하는 함수
	 * @param p1 출발지 좌표
	 * @param p2 도착지 좌표
	 * @return 두 좌표의 맨해튼 거리
	 */
	static int distance(Point p1, Point p2) {
		return Math.abs(p2.r - p1.r) + Math.abs(p2.c - p1.c);
	}


	static class Point {
		int r, c;

		public Point() {
			super();
		}
		/**
		 * @param r 행 좌표
		 * @param c 열 좌표
		 */
		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
}
