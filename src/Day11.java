import java.util.*;

class Pair {
    int x,y;

    Pair(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return x == pair.x && y == pair.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

public class Day11 {
    public static final int movesize = 8;
    public static final int[] movex = {-1, -1, -1, 0, 1, 1, 1, 0};
    public static final int[] movey = {-1, 0, 1, 1, 1, 0, -1, -1};

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<String> l = new LinkedList<>();

        while (scan.hasNextLine())
            l.add(scan.nextLine());

        int[][] map = new int[l.size()][l.get(0).length()];
        for(int i=0;i<l.size();i++){
            for(int j=0;j<l.get(i).length();j++){
                map[i][j] = Character.getNumericValue(l.get(i).charAt(j));
            }
        }

        //System.out.println("Answer Part 1: " + flash1(map, 100));
        System.out.println("Answer Part 2: " + flash2(map));
    }

    public static int flash1(int[][] map, int steps) {
        int ans = 0;
        for(int step=0;step<steps;step++){
            LinkedList<Pair> queue = new LinkedList<>();
            HashSet<Pair> set = new HashSet<>();
            for(int i=0;i<map.length;i++){
                for(int j=0;j<map[0].length;j++){
                    map[i][j]  = (map[i][j] + 1) % 10;
                    // add all octopus to queue which flashes
                    if(map[i][j]==0){
                        ans++;
                        queue.add(new Pair(i,j));
                        set.add(new Pair(i,j));
                    }
                }
            }

            while(!queue.isEmpty()) {
                Pair p = queue.poll();
                // increase energy of all adjacent octopus who are not already flashed
                for(int m=0;m<movesize;m++) {
                    int nextx = p.x + movex[m];
                    int nexty = p.y + movey[m];
                    if(nextx >= 0 && nextx < map.length &&
                            nexty >=0 && nexty < map[0].length &&
                            !set.contains(new Pair(nextx, nexty))) {
                        map[nextx][nexty]  = (map[nextx][nexty] + 1) % 10;
                        // add all octopus to queue which flashes
                        if(map[nextx][nexty]==0){
                            ans++;
                            queue.add(new Pair(nextx, nexty));
                            set.add(new Pair(nextx, nexty));
                        }
                    }
                }
            }
        }

        return ans;
    }

    public static int flash2(int[][] map) {
        int step = 0;
        while(true){
            LinkedList<Pair> queue = new LinkedList<>();
            HashSet<Pair> set = new HashSet<>();
            for(int i=0;i<map.length;i++){
                for(int j=0;j<map[0].length;j++){
                    map[i][j]  = (map[i][j] + 1) % 10;
                    // add all octopus to queue which flashes
                    if(map[i][j]==0){
                        queue.add(new Pair(i,j));
                        set.add(new Pair(i,j));
                    }
                }
            }

            while(!queue.isEmpty()) {
                Pair p = queue.poll();
                // increase energy of all adjacent octopus who are not already flashed
                for(int m=0;m<movesize;m++) {
                    int nextx = p.x + movex[m];
                    int nexty = p.y + movey[m];
                    if(nextx >= 0 && nextx < map.length &&
                            nexty >=0 && nexty < map[0].length &&
                            !set.contains(new Pair(nextx, nexty))) {
                        map[nextx][nexty]  = (map[nextx][nexty] + 1) % 10;
                        // add all octopus to queue which flashes
                        if(map[nextx][nexty]==0){
                            queue.add(new Pair(nextx, nexty));
                            set.add(new Pair(nextx, nexty));
                        }
                    }
                }
            }

            step++;
            if(set.size() == map.length*map[0].length)
                return step;
        }
    }
}
