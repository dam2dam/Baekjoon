import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static final int BLANK = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		int numberOfTruck = Integer.parseInt(stringTokenizer.nextToken());
		int lengthOfBridge = Integer.parseInt(stringTokenizer.nextToken());
		int maxLoad = Integer.parseInt(stringTokenizer.nextToken());

		Queue<Integer> bridge = new ArrayDeque<>();
		for (int i = 0; i < lengthOfBridge; i++) {
			bridge.offer(BLANK);
		}
		int time = 0;
		int weightOfBridge = 0;
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		int truck = Integer.parseInt(stringTokenizer.nextToken());

		while (!bridge.isEmpty()) {
			time++;
			weightOfBridge -= bridge.poll();

			if (weightOfBridge + truck <= maxLoad) {
				weightOfBridge += truck;
				bridge.offer(truck);
				if (stringTokenizer.hasMoreTokens()) {
					truck = Integer.parseInt(stringTokenizer.nextToken());
				} else {
					break;
				}
			} else {
				bridge.offer(BLANK);
			}
		}
		System.out.println(time + bridge.size());
		bufferedReader.close();
	}
}
