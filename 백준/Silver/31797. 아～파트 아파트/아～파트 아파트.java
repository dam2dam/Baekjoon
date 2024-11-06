import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		Person[] apt = new Person[M * 2];
		int index = 0;
		for (int number = 1; number <= M; number++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			apt[index++] = new Person(number, left);
			apt[index++] = new Person(number, right);
		}
		// 손의 높이로 정렬
		Arrays.sort(apt);
		// 아파트 층 수(인덱스)를 전체 손의 개수로 나눈 나머지가 층 수이다 (손이 순환하기 때문) 
		int alcoholIndex = (N - 1) % (M * 2);
		System.out.println(apt[alcoholIndex].number);

		br.close();
	}

	/**
	 * 아파트 게임에 참여하는 사람
	 */
	static class Person implements Comparable<Person> {
		int number;
		int height;

		/**
		 * @param number 사람의 번호
		 * @param height 한 손의 아파트 층 수
		 */
		Person(int number, int height) {
			this.number = number;
			this.height = height;
		}

		@Override
		public int compareTo(Person p) {
			return this.height < p.height ? -1 : this.height == p.height ? 0 : 1;
		}
	}
}

