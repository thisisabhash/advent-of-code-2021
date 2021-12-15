import java.util.*;

public class Day14 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = null;

        if (scan.hasNextLine())
            s = scan.nextLine();

        if (scan.hasNextLine())
            scan.nextLine();

        Map<String, List<String>> map = new HashMap<>();
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] words = line.split("\\s+");
            List<String> l = new LinkedList<>();
            l.add(words[0].charAt(0)+words[2]+"");
            l.add(words[2]+words[0].charAt(1)+"");
            map.put(words[0], l);
        }

        System.out.println("Answer Part 1: " + template2(s, map, 40));
    }

    public static long template1(String s, Map<String, String> map) {
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

    public static long template2(String s, Map<String, List<String>> map, int count) {
        HashMap<String, Long> initialMap = new HashMap<>();
        for(int i=0;i<s.length()-1;i++){
            initialMap.put(s.substring(i, i+2), initialMap.getOrDefault(s.substring(i, i+2), 0L) + 1);
        }

        for(int i=0;i<count;i++){
            HashMap<String, Long> tempMap = new HashMap<>();
            for(String key: initialMap.keySet()){
                for(String value: map.get(key)){
                    tempMap.put(value, tempMap.getOrDefault(value, 0L) + initialMap.get(key));
                }
            }

            initialMap = tempMap;
        }

        HashMap<Character, Long> countMap = new HashMap<>();
        for(String key: initialMap.keySet()) {
            countMap.put(key.charAt(1), countMap.getOrDefault(key.charAt(1), 0L) + initialMap.get(key));
        }
        countMap.put(s.charAt(0), countMap.getOrDefault(s.charAt(0), 0L) + 1);

        Long min = Long.MAX_VALUE;
        Long max = Long.MIN_VALUE;
        for(Character c: countMap.keySet()){
            min = Math.min(min, countMap.get(c));
            max = Math.max(max, countMap.get(c));
        }
        return max-min;
    }
}
