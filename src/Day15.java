import java.util.*;

class Node implements Comparable {
    int risk;
    int x,y;

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    Node(int x, int y, int risk) {
        this.x = x;
        this.y = y;
        this.risk = risk;
    }

    @Override
    public boolean equals(Object obj) {
        return this.x == ((Node)obj).x && this.y == ((Node)obj).y;
    }

    @Override
    public int compareTo(Object o) {
        return this.risk - ((Node)o).risk;
    }
}

public class Day15 {

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

        System.out.println("Answer Part 1: " + risk(generate(map)));
    }

    public static int[][] generate(int[][] map) {
        int[][] ret = new int[map.length*5][map[0].length*5];
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                ret[i][j] = map[i][j];
                for(int p=1;p<5;p++){
                    ret[i][j+p*map[0].length] = (ret[i][j+(p-1)*map[0].length] + 1) % 10;
                    ret[i][j+p*map[0].length] = (ret[i][j+p*map[0].length] == 0) ? 1 : ret[i][j+p*map[0].length];
                }
                for(int p=1;p<5;p++){
                    ret[i+p*map.length][j] = (ret[i+(p-1)*map.length][j] + 1) % 10;
                    ret[i+p*map.length][j] = (ret[i+p*map.length][j] == 0) ? 1 : ret[i+p*map.length][j];
                }
                for(int p=1;p<5;p++){
                    for(int q=1;q<5;q++) {
                        ret[i+p*map.length][j+q*map[0].length] = (ret[i+p*map.length][j+(q-1)*map[0].length] + 1) % 10;
                        ret[i+p*map.length][j+q*map[0].length] =
                                (ret[i+p*map.length][j+q*map[0].length] == 0) ? 1 : ret[i+p*map.length][j+q*map[0].length];
                    }
                }
            }
        }

        return ret;
    }

    public static int risk(int[][] map) {
        Node initialNode = new Node(0,0,0);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        HashMap<Node, Integer> visited = new HashMap<>();
        pq.add(initialNode);
        //visited.put(initialNode, 0);
        while(!pq.isEmpty()) {
            Node n = pq.poll();
            if(n.x == map.length-1 && n.y == map[0].length-1)
                return n.risk;

            if(n.x+1<map.length) {
                Node next = new Node(n.x+1,n.y,n.risk+map[n.x+1][n.y]);
                if(!visited.containsKey(next) || visited.get(next) > next.risk){
                    visited.put(next, next.risk);
                    pq.add(next);
                }
            }

            if(n.y+1<map[0].length) {
                Node next = new Node(n.x,n.y+1,n.risk+map[n.x][n.y+1]);
                if(!visited.containsKey(next) || visited.get(next) > next.risk){
                    visited.put(next, next.risk);
                    pq.add(next);
                }
            }

            if(n.x-1>=0) {
                Node next = new Node(n.x-1,n.y,n.risk+map[n.x-1][n.y]);
                if(!visited.containsKey(next) || visited.get(next) > next.risk) {
                    visited.put(next, next.risk);
                    pq.add(next);
                }
            }

            if(n.y-1>=0) {
                Node next = new Node(n.x,n.y-1,n.risk+map[n.x][n.y-1]);
                if(!visited.containsKey(next) || visited.get(next) > next.risk){
                    visited.put(next, next.risk);
                    pq.add(next);
                }
            }

        }

        return 0;
    }

//    public static int risk(int[][] map){
//        int[][] dp = new int[map.length][map[0].length];
//        dp[0][0] = 0;
//        for(int i=1;i<dp.length;i++){
//            dp[i][0] = dp[i-1][0] + map[i][0];
//        }
//        for(int i=1;i<dp[0].length;i++){
//            dp[0][i] = dp[0][i-1] + map[0][i];
//        }
//        for(int i=1;i<dp.length;i++){
//            for(int j=1;j<dp[0].length;j++){
//                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + map[i][j];
//            }
//        }
//
//        return dp[map.length-1][map[0].length-1];
//    }
}
