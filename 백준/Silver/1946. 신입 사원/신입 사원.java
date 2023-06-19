import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, documentScore, interviewScore, minScore, passCount;
	static Applicant[] applicants;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		int T = Integer.parseInt(bufferedReader.readLine());
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(bufferedReader.readLine());
			applicants = new Applicant[N];
			for (int i = 0; i < N; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				documentScore = Integer.parseInt(stringTokenizer.nextToken());
				interviewScore = Integer.parseInt(stringTokenizer.nextToken());

				applicants[i] = new Applicant(documentScore, interviewScore);
			}
			Arrays.sort(applicants);

			passCount = 1;
			minScore = applicants[0].interviewScore;
			for (int i = 1; i < N; i++) {
				if (minScore < applicants[i].interviewScore) {
					continue;
				}
				minScore = applicants[i].interviewScore;
				passCount++;
			}
			System.out.println(passCount);
		}
		bufferedReader.close();
	}

	static class Applicant implements Comparable<Applicant> {
		int documentScore, interviewScore;

		public Applicant(int documentScore, int interviewScore) {
			this.documentScore = documentScore;
			this.interviewScore = interviewScore;
		}

		@Override
		public int compareTo(Applicant applicant) {
			return this.documentScore - applicant.documentScore;
		}
	}
}
