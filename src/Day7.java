import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Day7 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<Integer> l = new LinkedList<>();

        String s = scan.nextLine();
        String[] words = s.split(",");
        for (int i = 0; i < words.length; i++) {
            l.add(Integer.valueOf(words[i]));
        }

        System.out.println("Answer Part 1: " + leastFuel1(l));
        System.out.println("Answer Part 2: " + leastFuel2(l));
    }

    public static int leastFuel1(List<Integer> input) {
//        int sum = 0;
//        for (Integer i : input) {
//            sum += i;
//        }
//
//        if (sum % input.size() == 0) {
//            int diff = 0;
//            for (Integer i : input) {
//                diff += Math.abs(i - sum / input.size());
//            }
//            return diff;
//        }
//
//        int floor = Math.floorDiv(sum, input.size());
//        int ceil = floor + 1;
//        int floorDiff = 0;
//        int ceilDiff = 0;
//        for (Integer i : input) {
//            floorDiff += Math.abs(i - floor);
//            ceilDiff += Math.abs(i - ceil);
//        }
//
//        return Math.min(ceilDiff, floorDiff);

        Collections.sort(input);
        if (input.size() % 2 == 0) {
            int median1 = (input.get(input.size() / 2) + input.get(input.size() / 2 - 1)) / 2;
            int median2 = median1 + 1;
            int diff1 = 0;
            int diff2 = 0;
            for (Integer i : input) {
                diff1 += Math.abs(i - median1);
                diff2 += Math.abs(i - median2);
            }
            return Math.min(diff1, diff2);
        } else {
            int median = input.get(input.size() / 2);
            int diff = 0;
            for (Integer i : input) {
                diff += Math.abs(i - median);
                ;
            }
            return diff;
        }
    }

    public static int leastFuel2(List<Integer> input) {
        // ((x-a)^2 + ... + nx - sum ) / 2 = 0
        // 2nx - 2*sum + n = 0
        // x = (2*sum - n)/2n
        int sum = 0;
        for (Integer i : input) {
            sum += i;
        }
        int pos1 = (2 * sum - input.size()) / (2 * input.size());
        int pos2 = pos1 + 1;
        int diff1 = 0, diff2 = 0;
        for (Integer i : input) {
            int d1 = Math.abs(i - pos1);
            int d2 = Math.abs(i - pos2);
            diff1 += d1 * (d1 + 1) / 2;
            diff2 += d2 * (d2 + 1) / 2;
        }
        return Math.min(diff1, diff2);
    }
}
