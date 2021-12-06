import jdk.jshell.VarSnippet;

import java.util.*;

class Point {
    int x;
    int y;

    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class LineSegment {
    Point A;
    Point B;

    LineSegment(String line) {
        String[] words = line.split("\\s+");
        String[] leftPoint = words[0].split(",");
        String[] rightPoint = words[2].split(",");
        this.A = new Point(Integer.valueOf(leftPoint[0]), Integer.valueOf(leftPoint[1]));
        this.B = new Point(Integer.valueOf(rightPoint[0]), Integer.valueOf(rightPoint[1]));
    }
}

public class Day5 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<LineSegment> input = new LinkedList<>();

        while(scan.hasNextLine()) {
            input.add(new LineSegment(scan.nextLine()));
        }

        System.out.println("Answer Part 1: " + overlap(input));
    }

    public static int overlap(List<LineSegment> input) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for(int i=0;i<input.size();i++){
            LineSegment l = input.get(i);
            if(l.A.x == l.B.x) {
                for(int j=Math.min(l.A.y, l.B.y); j<=Math.max(l.A.y, l.B.y);j++){
                    map.put(l.A.x, map.getOrDefault(l.A.x, new HashMap<>()));
                    Map<Integer, Integer> m = map.get(l.A.x);
                    m.put(j, m.getOrDefault(j, 0) + 1);
                }
            } else if (l.A.y == l.B.y) {
                for(int j=Math.min(l.A.x, l.B.x); j<=Math.max(l.A.x, l.B.x);j++){
                    map.put(j, map.getOrDefault(j, new HashMap<>()));
                    Map<Integer, Integer> m = map.get(j);
                    m.put(l.A.y, m.getOrDefault(l.A.y, 0) + 1);
                }
            } else if (Math.abs(l.A.x-l.B.x) == Math.abs(l.A.y-l.B.y)) {
                if(l.A.x <= l.B.x && l.A.y <= l.B.y) {
                    int x = l.A.x;
                    int y = l.A.y;
                    while(x<=l.B.x && y<=l.B.y) {
                        map.put(x, map.getOrDefault(x, new HashMap<>()));
                        Map<Integer, Integer> m = map.get(x);
                        m.put(y, m.getOrDefault(y, 0) + 1);
                        x++;
                        y++;
                    }
                } else if (l.A.x <= l.B.x && l.A.y >= l.B.y) {
                    int x = l.A.x;
                    int y = l.A.y;
                    while(x<=l.B.x && y>=l.B.y) {
                        map.put(x, map.getOrDefault(x, new HashMap<>()));
                        Map<Integer, Integer> m = map.get(x);
                        m.put(y, m.getOrDefault(y, 0) + 1);
                        x++;
                        y--;
                    }
                } else if (l.A.x >= l.B.x && l.A.y <= l.B.y) {
                    int x = l.A.x;
                    int y = l.A.y;
                    while(x>= l.B.x && y<= l.B.y) {
                        map.put(x, map.getOrDefault(x, new HashMap<>()));
                        Map<Integer, Integer> m = map.get(x);
                        m.put(y, m.getOrDefault(y, 0) + 1);
                        x--;
                        y++;
                    }
                } else {
                    int x = l.A.x;
                    int y = l.A.y;
                    while (x >= l.B.x && y >= l.B.y) {
                        map.put(x, map.getOrDefault(x, new HashMap<>()));
                        Map<Integer, Integer> m = map.get(x);
                        m.put(y, m.getOrDefault(y, 0) + 1);
                        x--;
                        y--;
                    }
                }
            }
        }

        int answer = 0;
        for(Integer key : map.keySet()) {
            Map<Integer, Integer> m = map.get(key);
            for(Integer ykey: m.keySet()) {
                if(m.get(ykey) >=2) {
                    answer++;
                }
            }
        }

        return answer;
    }
}
