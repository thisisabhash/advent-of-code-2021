import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Day1 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<Integer> input = new LinkedList<>();

        while(scan.hasNextInt()) {
            input.add(scan.nextInt());
        }

        System.out.println("Answer Part 1: " + calculateDepthIncrease(input));
        System.out.println("Answer Part 2: " + calculateDepthIncrease3Window(input));
    }

    public static int calculateDepthIncrease(List<Integer> input) {
        int answer = 0;
        for(int i=1;i<input.size();i++){
            answer += input.get(i) > input.get(i-1) ? 1 : 0;
        }

        return answer;
    }

    public static int calculateDepthIncrease3Window(List<Integer> input) {
        int prev = input.get(0) + input.get(1) + input.get(2);
        int answer = 0;
        //System.out.println("size: " + input.size());
        for(int i=3;i<input.size();i++){
            int curr = prev - input.get(i-3) + input.get(i);
            answer += curr > prev ? 1 : 0;
            prev = curr;
        }

        return answer;
    }

}
