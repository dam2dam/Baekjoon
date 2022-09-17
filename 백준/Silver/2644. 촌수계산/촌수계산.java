import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, person1, person2, degreeOfKinship, answer;
	static boolean correct;
	static ArrayList<Integer>[] people;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		
		
		N = Integer.parseInt(bufferedReader.readLine());
		people = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			people[i] = new ArrayList<>();
		}
		
		
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		person1 = Integer.parseInt(stringTokenizer.nextToken());
		person2 = Integer.parseInt(stringTokenizer.nextToken());
		
		M = Integer.parseInt(bufferedReader.readLine());
		for (int i = 0; i < M; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int parent = Integer.parseInt(stringTokenizer.nextToken());
			int child = Integer.parseInt(stringTokenizer.nextToken());
			people[parent].add(child);
			people[child].add(parent);
		}
		
//		int index = 0;
//		for (ArrayList<Integer> list : people) {
//			if (list != null) {
//				System.out.println(index + " " + list.toString());
//			}
//			index++;
//		}
//		
		visited = new boolean[N+1];
		degreeOfKinship = 0;
		correct = false;
		answer = 0;
		visited[person1] = true;
		if(countDegreeOfKinship(person1, degreeOfKinship) == true) {
			System.out.println(degreeOfKinship);
		} else {
			System.out.println(-1);
		}
	}

	private static boolean countDegreeOfKinship(int person, int depth) {
		
		if (person == person2) {
			correct = true;
			degreeOfKinship = depth;
			return correct;
		}
		
		for (int i = 0; i < people[person].size(); i++) {
			int current = people[person].get(i);
			if (visited[current] == true) 
				continue;
			visited[current] = true;
			if(countDegreeOfKinship(current, depth+1) == true) 
				return correct;
			visited[current] = false;
		}
		return correct;
	}
}
