import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayList<Integer> plus = new ArrayList<>();
		ArrayList<Integer> minus = new ArrayList<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int input = Integer.parseInt(st.nextToken());
			if (input > 0) {           // 양수 좌표에 있는 책 저장
				plus.add(input);
			} else if (input < 0) {    // 음수 좌표에 있는 책 저장
				minus.add(Math.abs(input));
			}
		}
		// 정렬
		Collections.sort(plus);
		Collections.sort(minus);

		int pSize = plus.size();
		int mSize = minus.size();
		int sum = 0;
		// 양수 좌표의 책들 가장 먼 곳부터 M개씩 가져다 놓기
		for (int i = pSize - 1; i >= 0; i -= M) {
			sum += plus.get(i) * 2;
		}
		// 음수 좌표의 책들 가장 먼 곳부터 M개씩 가져다 놓기
		for (int i = mSize - 1; i >= 0; i -= M) {
			sum += minus.get(i) * 2;
		}

		if (pSize == 0) {    // 양수 좌표에 해당하는 책이 없다면
			// 음수 좌표에 해당하는 가장 먼 곳의 책을 나중에 가져다 놓고 돌아오지 않아도 됨
			sum -= minus.get(mSize - 1);
		} else if (mSize == 0) {    // 음수 좌표에 해당하는 책이 없다면
			// 양수 좌표에 해당하는 가장 먼 곳의 책을 나중에 가져다 놓고 돌아오지 않아도 됨
			sum -= plus.get(pSize - 1);
		} else {
			// 가장 먼 곳의 책을 나중에 가져다 놓고 돌아오지 않아도 됨
			sum -= Math.max(plus.get(pSize - 1), minus.get(mSize - 1));
		}
		System.out.println(sum);

		br.close();
	}
}
