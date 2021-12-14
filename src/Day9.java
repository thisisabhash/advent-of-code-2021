import java.util.*;

public class Day9 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<String> l = new LinkedList<>();

        while (scan.hasNextLine())
            l.add(scan.nextLine());

        int[][] map = new int[l.size()][l.get(0).length()];
        for(int i=0;i<l.size();i++){
            for(int j=0;j<l.get(i).length();j++){
                map[i][j] = l.get(i).charAt(j) - '0';
            }
        }

        System.out.println("Answer Part 1: " + risk(map));
        System.out.println("Answer Part 2: " + basin(map));
    }

    public static int risk(int[][] map){
        int riskTotal = 0;
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length;j++){
                int x = 0, y = 0;
                if(i-1>=0) {
                    x++;
                    if(map[i-1][j]>map[i][j])
                        y++;
                }
                if(j-1>=0) {
                    x++;
                    if(map[i][j-1]>map[i][j])
                        y++;
                }
                if(i+1<map.length) {
                    x++;
                    if(map[i+1][j]>map[i][j])
                        y++;
                }
                if(j+1<map[i].length) {
                    x++;
                    if(map[i][j+1]>map[i][j])
                        y++;
                }

                if(x==y)
                    riskTotal += map[i][j]+1;
            }
        }
        return riskTotal;
    }

    public static long basin(int[][] map){
        boolean[][] visited = new boolean[map.length][map[0].length];
        List<Long> l = new LinkedList<>();
        for(int i=0;i<map.length;i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(visited[i][j] == false) {
                    l.add(calculate(map, i, j, visited));
                }
            }
        }

        Collections.sort(l, Comparator.reverseOrder());
        return l.get(0)*l.get(1)*l.get(2);
    }

    public static long calculate(int[][] map,int i, int j, boolean[][] visited){
        if(!visited[i][j]) {
            visited[i][j] = true;

            if (map[i][j] == 9)
                return 0;

            int ret = 0;
            if (i - 1 >= 0) {
                ret += calculate(map, i - 1, j, visited);
            }

            if (j - 1 >= 0) {
                ret += calculate(map, i, j - 1, visited);
            }

            if (i + 1 < map.length) {
                ret += calculate(map, i + 1, j, visited);
            }

            if (j + 1 < map[i].length) {
                ret += calculate(map, i, j + 1, visited);
            }

            return ret+1;
        }

        return 0;
    }
}
