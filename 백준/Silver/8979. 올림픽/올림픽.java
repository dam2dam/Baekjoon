import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		String[] s = bReader.readLine().split(" ");
		
		int n = Integer.parseInt(s[0]);	//국가의 수
		int k = Integer.parseInt(s[1]);	//둥수를 알고 싶은 국가
		
		int[][] olympic = new int[n][4];	//메달 배열 { {국가번호, 금, 은, 동}, ...}
		for (int i = 0; i < n; i++) {
			s = bReader.readLine().split(" ");
			//String array -> String array stream 생성 -> IntStream으로 변환 -> int array로 변환
			olympic[i] = Arrays.stream(s).mapToInt(Integer::parseInt).toArray();
		}
		
		Arrays.sort(olympic, new Comparator<int[]>() { //재정의된 아래 기준으로 정렬 (return값 내림차순) : 등수가 높은 국가가 상위로
			
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1] == o2[1] && o1[2] == o2[2]) //금메달 은메달 수가 같으면
					return o2[3] - o1[3];	// 동메달 비교
				if (o1[1] == o2[1])	//금메달 수가 같으면
					return o2[2] - o1[2];	// 은메달 비교
                return o2[1] - o1[1];	// 금메달 비교
			}
		});
		
		int jointCount = 0;	//같은 등수 국가 count
		int indexOfK = 0;	//등수를 알고 싶은 국가의 index
		
		//등수를 알고 싶은 국가의 index 저장
		for(int i = 0; i < n; i++)
			if(olympic[i][0] == k)
				indexOfK = i;
		
		//같은 등수 국가 count
		for (int i = 0; i < n; i++)
			if(olympic[indexOfK][1] == olympic[i][1] && olympic[indexOfK][2] == olympic[i][2] && olympic[indexOfK][3] == olympic[i][3])
				jointCount++;
		
		for (int i = 0; i < n; i++) {
			//같은 등수의 국가가 있으면 그 중 가장 위에 정렬 되어있는 국가의 등수(index+1) 출력
			if (jointCount > 1) {
				if(olympic[indexOfK][1] == olympic[i][1] && olympic[indexOfK][2] == olympic[i][2] && olympic[indexOfK][3] == olympic[i][3]) {
					System.out.println(i+1);
					break;
				}
            }
			//같은 등수의 국가가 없으면 등수를 알고 싶은 국가의 등수(index+1) 출력
			else {
				System.out.println(indexOfK+1);
				break;
			}
		}
	}
}
