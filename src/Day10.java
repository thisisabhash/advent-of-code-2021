import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Day10 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<String> l = new LinkedList<>();

        while (scan.hasNextLine())
            l.add(scan.nextLine());

        System.out.println("Answer Part 1: " + score(l)[0]);
        System.out.println("Answer Part 1: " + score(l)[1]);
    }

    public static long[] score(List<String> input) {
        long answer1 = 0;
        List<Long> answer2 = new LinkedList<>();
        for (String s : input) {
            LinkedList<Character> stack = new LinkedList<>();
            long tempAnswer2 = 0;
            boolean illegalCharFound = false;
            //System.out.println("answer: " + answer);
            for (int i = 0; i < s.length() && illegalCharFound != true; i++) {
                char c = s.charAt(i);
                switch (c) {
                    case '[':
                    case '(':
                    case '<':
                    case '{':
                        stack.push(c);
                        break;
                    case '}':
                        if (!stack.isEmpty() && !stack.peek().equals('{')) {
                            answer1 += getPoint1(c);
                            illegalCharFound = true;
                        } else {
                            stack.pop();
                        }
                        break;
                    case ')':
                        if (!stack.isEmpty() && !stack.peek().equals('(')) {
                            answer1 += getPoint1(c);
                            illegalCharFound = true;
                        } else {
                            stack.pop();
                        }
                        break;
                    case ']':
                        if (!stack.isEmpty() && !stack.peek().equals('[')) {
                            answer1 += getPoint1(c);
                            illegalCharFound = true;
                        } else {
                            stack.pop();
                        }
                        break;
                    case '>':
                        if (!stack.isEmpty() && !stack.peek().equals('<')) {
                            answer1 += getPoint1(c);
                            illegalCharFound = true;
                        } else {
                            stack.pop();
                        }
                        break;
                    default:
                        break;
                }
            }

            while(illegalCharFound==false && !stack.isEmpty()){
                Character top = stack.pop();
                tempAnswer2 = tempAnswer2*5 + getPoint2(top);
            }

            if(tempAnswer2!=0)
                answer2.add(tempAnswer2);
        }

        Collections.sort(answer2);
        long[] answer = {answer1, answer2.get(answer2.size()/2)};
        return answer;
    }

    private static long getPoint1(Character c) {
        switch (c) {
            case ')':
                return 3;
            case ']':
                return 57;
            case '}':
                return 1197;
            case '>':
                return 25137;
            default:
                return 0;
        }
    }

    private static long getPoint2(Character c) {
        switch (c) {
            case '(':
                return 1;
            case '[':
                return 2;
            case '{':
                return 3;
            case '<':
                return 4;
            default:
                return 0;
        }
    }
}