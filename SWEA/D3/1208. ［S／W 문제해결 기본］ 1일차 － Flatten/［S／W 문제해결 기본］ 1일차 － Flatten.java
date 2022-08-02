import java.io.FileInputStream;
import java.util.Scanner;
 
public class Solution {
 
    public static void main(String[] args) throws Exception {
//      System.setIn(new FileInputStream("input.txt"));
        Scanner scanner = new Scanner(System.in);
         
        //테스트 케이스 입력
        for (int tc = 1; tc <= 10; tc++) {
            int dump = scanner.nextInt();
            int[] boxes = new int[100];
            for (int j = 0; j < 100; j++) 
                 boxes[j] = scanner.nextInt();
             
            int max = 0;
            int min = 100;
            int maxIndex = 0;
            int minIndex = 0;
             
            //평탄화
            while (dump > 0) {
                max = 0;
                min = 100;
                maxIndex = 0;
                minIndex = 0;
                 
                //최댓값 최솟값과 그의 인덱스 구하기
                for (int i = 0; i < boxes.length; i++) {
                    if (boxes[i] >= max) {
                        max = Math.max(max, boxes[i]);
                        maxIndex = i;
                    }
                    if (boxes[i] <= min) {
                        min = Math.min(min, boxes[i]);
                        minIndex = i;
                    }
                }
                if(boxes[maxIndex]-boxes[minIndex] <= 1) {
                    break;
                }
                //덤프
                boxes[maxIndex]--;
                boxes[minIndex]++;
                dump--;
            }
             
            //평탄화 후 현재 최댓값 최솟값 인덱스 구하기
            for (int i = 0; i < boxes.length; i++) {
                if (boxes[i] >= max) {
                    max = Math.max(max, boxes[i]);
                    maxIndex = i;
                }
                if (boxes[i] <= min) {
                    min = Math.min(min, boxes[i]);
                    minIndex = i;
                }
            }
            int answer = boxes[maxIndex] - boxes[minIndex];
            System.out.printf("#%d %d\n", tc, answer);
        }
        scanner.close();
    }
 
}