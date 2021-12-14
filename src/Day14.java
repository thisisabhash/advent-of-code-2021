import java.util.*;

class MyResult implements  Comparator {
    char c;
    int value;

    MyResult(char c, int value) {
        this.c = c;
        this.value = value;
    }

    @Override
    public int compare(Object o1, Object o2) {
        return ((MyResult)o1).value - ((MyResult)o2).value;
    }
}

public class Day14 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = null;

        if (scan.hasNextLine())
            s = scan.nextLine();

        if (scan.hasNextLine())
            scan.nextLine();

        Map<String, String> map = new HashMap<>();
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] words = line.split("\\s+");
            map.put(words[0], words[2]);
        }

        System.out.println("Answer Part 1: " + template(s, map));
    }

    public static long template(String s, Map<String, String> map) {
        StringBuilder sb = new StringBuilder(s);
        for (int x = 0; x < 40; x++) {
            int initialLength = sb.length();
            StringBuilder temp = new StringBuilder(sb);
            for (int i = 0; i < temp.length() - 1; i++) {
                String sub = temp.substring(i, i + 2);
                if (map.containsKey(sub)) {
                    sb.insert(i + 1 + sb.length() - initialLength, map.get(sub));
                }
            }
        }

        long[] res = new long[26];
        for(int i=0;i<sb.length();i++){
            res[sb.charAt(i)-'A']++;
        }
        Arrays.sort(res);

        int index = 0;
        while(res[index]==0 && index<res.length)
            index++;

        return res[res.length-1]-res[index];
    }
}
