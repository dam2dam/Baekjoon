import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, D;
	static ArrayList<Point>[] path;
	static int[] minDistance;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		N = Integer.parseInt(stringTokenizer.nextToken());
		D = Integer.parseInt(stringTokenizer.nextToken());

		// 현재 지점으로 오는 지름길 리스트의 배열
		path = new ArrayList[10001];
		for (int i = 0; i <= 10000; i++) {
			path[i] = new ArrayList<>();
		}
		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int from = Integer.parseInt(stringTokenizer.nextToken());
			int to = Integer.parseInt(stringTokenizer.nextToken());
			int distance = Integer.parseInt(stringTokenizer.nextToken());

			// 지름길의 길이가 더 길거나, 지름길의 도착 위치가 목적지를 넘어가면
			// 지름길 리스트에 넣지 않음
			if (distance >= to - from || to > D) {
				continue;
			}
			path[to].add(new Point(from, distance));
		}
		// 최단 거리 배열
		minDistance = new int[D + 1];
		Arrays.fill(minDistance, 10000);

		minDistance[0] = 0; // 시작 위치
		for (int now = 1; now <= D; now++) {
			// 현재 지점으로 오는 지름길이 없다면 직전 거리 + 1
			if (path[now].isEmpty()) {
				minDistance[now] = minDistance[now - 1] + 1;
			} else {
				// 현재 지점으로 오는 지름길 확인하기
				for (Point previous : path[now]) {
					// 현재 지점까지의 최단 거리보다 지름길로 오는 길이 더 짧다면 최단 거리 갱신
					if (minDistance[now] > minDistance[previous.number] + previous.distance) {
						minDistance[now] = Math.min(minDistance[now - 1] + 1,
							minDistance[previous.number] + previous.distance);
					}
				}
			}
		}
		System.out.println(minDistance[D]);
		bufferedReader.close();
	}

	static class Point {
		int number, distance;

		public Point(int number, int distance) {
			this.number = number;
			this.distance = distance;
		}
	}

}
